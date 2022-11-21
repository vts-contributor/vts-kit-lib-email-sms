package vn.com.viettel.email.utils.config;

import lombok.Data;

import java.io.File;

@Data
public class EmailConfigValue {
    private String smtpHostMailVT = "125.235.240.36";
    private String smtpHost ;
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

}
