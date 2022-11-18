package vn.com.viettel.email.utils.email;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import vn.com.viettel.email.utils.config.EmailConfigValue;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


/**
 * Lib Support send Email
 *
 * @param_phone
 */
@Service
public class EmailService {

    private static final Logger LOGGER = Logger.getLogger(EmailService.class);
    private EmailConfigValue emailConfig;

    public EmailService(EmailConfigValue emailConfig) {
        this.emailConfig = emailConfig;
    }

    public EmailService() {
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", emailConfig.getTransportProtocol());
        props.put("mail.smtp.timeout", emailConfig.getTimeout());
        props.put("mail.smtp.socketFactory.port", emailConfig.getSmtpPort());
        props.put("mail.smtp.socketFactory.class", emailConfig.getSocketFactoryClass());
        props.put("mail.smtp.host", emailConfig.getSmtpHost());
        props.put("mail.smtp.user", emailConfig.getSmtpUser());
        props.put("mail.smtp.password", emailConfig.getPw());
        props.put("mail.smtp.auth", true);
        return props;
    }

    //Return true is success
    public boolean send() {
        String[] emailsTo = emailConfig.getToRecipients();
        String[] emailsCC = emailConfig.getCcRecipients();
        File[] attachments = emailConfig.getAttachments();

        Assert.notEmpty(emailsTo, "Recipient's email cannot be be null");

        try {

            Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getSmtpUser(), emailConfig.getPw());
                }
            });
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(emailConfig.getContent(), "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            MimeMessage message = new MimeMessage(session);

            message.setContent(multipart);
            message.setSubject(emailConfig.getSubject());
            message.setFrom(new InternetAddress(emailConfig.getSmtpUser()));

            for (String recipient : emailsTo) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }

            if (!ObjectUtils.isEmpty(emailsCC)) {
                for (String recipient : emailsCC) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
                }
            }

            if (!ObjectUtils.isEmpty(attachments)) {
                for (File file : attachments) {
                    MimeBodyPart filePart = new MimeBodyPart();
                    filePart.attachFile(file);
                    multipart.addBodyPart(filePart);
                }
            }

            Transport transport = session.getTransport(emailConfig.getTransportProtocol());
            transport.connect(emailConfig.getSmtpHost(), emailConfig.getSmtpUser(), emailConfig.getPw());

            Transport.send(message);

            return true;
        } catch (Exception e) {
            LOGGER.info("EmailService", e);
        }

        return false;
    }

}
