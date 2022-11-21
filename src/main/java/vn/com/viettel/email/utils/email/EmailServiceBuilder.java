package vn.com.viettel.email.utils.email;

import org.springframework.util.Assert;
import vn.com.viettel.email.utils.config.EmailConfigValue;

import java.io.File;
//import com.viettel.vtm.client.request.auth.BasicEmail;


/**
 * Lib Support send Email
 *
 * @param_phone
 */
public class EmailServiceBuilder {

    private EmailConfigValue emailConfig;

    public EmailServiceBuilder() {
        emailConfig = new EmailConfigValue();
    }

    public EmailServiceBuilder authentication(String userName, String pw) {
        emailConfig.setSmtpUser(userName);
        emailConfig.setPw(pw);
        return this;
    }

    public EmailServiceBuilder smtpHost(String smtpHost) {
        emailConfig.setSmtpHost(smtpHost);
        return this;
    }

    public EmailServiceBuilder smtpPort(String smtpPort) {
        emailConfig.setSmtpPort(smtpPort);
        return this;
    }

    public EmailServiceBuilder toRecipients(String[] toRecipients) {
        emailConfig.setToRecipients(toRecipients);
        return this;
    }

    public EmailServiceBuilder ccRecipients(String[] ccRecipients) {
        emailConfig.setCcRecipients(ccRecipients);
        return this;
    }

    public EmailServiceBuilder subject(String subject) {
        emailConfig.setSubject(subject);
        return this;
    }

    public EmailServiceBuilder attachments(File[] attachments) {
        emailConfig.setAttachments(attachments);
        return this;
    }

    public EmailServiceBuilder content(String content) {
        emailConfig.setContent(content);
        return this;
    }

    public EmailService build() {
        Assert.hasLength(emailConfig.getSmtpUser(), "Smtp User cannot be be null");
        Assert.hasLength(emailConfig.getPw(), "Smtp User Password cannot be be null");

        if (emailConfig.getSmtpHost() == null) {
            if (emailConfig.getSmtpUser().endsWith("@gmail.com")) {
                emailConfig.setSmtpHost(emailConfig.getSmtpHostGmail());
            } else if (emailConfig.getSmtpUser().endsWith("@viettel.com.vn")) {
                emailConfig.setSmtpHost(emailConfig.getSmtpHostMailVT());
            }
        }


        return new EmailService(emailConfig);
    }

}
