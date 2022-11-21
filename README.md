# library support email for spring boot

## Usage
* Just add dependency to existing spring boot project
```xml
<dependency>
    <groupId>vn.com.viettel.core</groupId>
    <artifactId>vts-kit-lib-email</artifactId>
    <version>1.0-RELEASE</version>
</dependency>
```

* Then, add the following properties to the application-*.properties file.
```properties
mail.smtp.host=125.235.240.36
mail.transport.protocol=smtps
mail.smtp.timeout=30000
mail.smtp.socketFactory.port=465    # Default 465 (for SSL and 587 for TSL)
mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
mail.smtp.auth=true                 # SMTP authentication needs to be activated


mail.smtp.user=                     # User's email address
mail.smtp.password=                 # User's email password
```

* Next, Define `EmailSender` instance.
```java
@Autowired
private EmailSender emailSender;
```

- With default case: you just need to add the codes like below
```java
boolean result = new EmailServiceBuilder()
        .authentication("USER_NAME","PASSWORD")
        .subject("Test send Email")
        .content("Test send email")
        .toRecipients(new String[]{"duc.doanquang@gmail.com"})
        .ccRecipients(new String[]{"ducdq1@viettel.com.vn"})
        .attachments(new File[]{new File("D:\\eula.1028.txt")})//optional: attach files
        .build()
        .send();
```
- With custom case: You just need to add the SMTP host and SMTP port you want
```java
boolean result = new EmailServiceBuilder()
                .authentication("USER_NAME","PASSWORD")
                .subject("Test send Email")
                .content("Test send email")
                .smtpHost("SMTP_HOST")//set custom smtp host
                .smtpPort("SMTP_PORT")//set custom smtp port
                .toRecipients(new String[]{"duc.doanquang@gmail.com"})
                .ccRecipients(new String[]{"ducdq1@viettel.com.vn"})
                .attachments(new File[]{new File("D:\\eula.1028.txt")})//optional: attach files
                .build()
                .send();
```

Returns true on success and false otherwise on failure

## Contribute

### Setting up the development environment

- **IDE:** Eclipse, Intellij IDEA
- **JDK:** >= JDK 8
- **Maven:** >= 3.6.0
- **Build:**

```
mvn clean install
```

### Contribute Guidelines

If you have any ideas, just open an issue and tell us what you think.

If you'd like to contribute, please refer [Contributors Guide](CONTRIBUTING.md).

## License

This code is under the [MIT License](https://opensource.org/licenses/MIT).

See the [LICENSE](LICENSE) file for required notices and attributions.