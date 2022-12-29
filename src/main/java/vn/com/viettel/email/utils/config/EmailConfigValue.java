package vn.com.viettel.email.utils.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.util.Properties;

@Data
public class EmailConfigValue {
    private final Logger logger = LoggerFactory.getLogger(EmailConfigValue.class);

    private String smtpHostMailVT;
    private String smtpHost;
    private String smtpHostGmail = "smtp.gmail.com";
    private String transportProtocol = "smtps";
    private int timeout = 30000;
    private String smtpPort = "465";
    private String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
    private String smtpUser;
    private String pw;
    private String[] toRecipients;
    private String[] ccRecipients;
    private File[] attachments;
    private String content;
    private String subject;

    public String getSmtpHostMailVT() {
        try {
            Resource resource = new ClassPathResource("/sms_application.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            smtpHostMailVT = props.getProperty("smtpHostMailVT");
        } catch (Exception e) {
            logger.error("Invalid Smtp Host", e);
        }

        return smtpHostMailVT;
    }

}
