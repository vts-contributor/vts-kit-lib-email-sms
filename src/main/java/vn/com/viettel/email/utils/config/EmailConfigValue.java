package vn.com.viettel.email.utils.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Data
public class EmailConfigValue {
    private String smtpHost = "125.235.240.36";
    private String smtpHostGmail = "smtp.gmail.com";
    private String transportProtocol = "smtps";
    private int timeout = 30000;
    private String smtpPort = "465";
    private String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";

    @Value("${mail.smtp.user:null}")
    private String smtpUser;

    @Value("${mail.smtp.password:null}")
    private String pw;

    private String[] toRecipients;
    private String[] ccRecipients;
    private File[] attachments;
    private String content;
    private String subject;

}
