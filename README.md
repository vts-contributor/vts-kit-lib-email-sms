# Library support send email and sms for spring boot
<b>Feature List</b>
* [Send Email](#send-email)
* [Send SMS](#send-sms)

## Send Email
* Just add dependency to existing spring boot project
```xml
<dependency>
    <groupId>com.atviettelsolutions</groupId>
    <artifactId>vts-kit-lib-email-sms</artifactId>
    <version>1.0.0</version>
</dependency>
```

- With default case: if use Viettel email or Gmail, You just need to add the codes like below:
```java
boolean result = new EmailServiceBuilder()
        .authentication("USER_NAME","PASSWORD")//Sender's email
        .subject("Test send Email")
        .content("Test send email")
        .toRecipients(new String[]{"duc.doanquang@gmail.com"})
        .ccRecipients(new String[]{"duc.doanquang@gmail.com"})//optional
        .attachments(new File[]{new File("D:\\file.txt")})//optional: attach files
        .build()
        .send();
```
- With custom case: You can config an other SMTP host and SMTP port you want:
```java
boolean result = new EmailServiceBuilder()
                .authentication("USER_NAME","PASSWORD")//Sender's email
                .subject("Test send Email")
                .content("Test send email")
                .smtpHost("SMTP_HOST")//set custom smtp host
                .smtpPort("SMTP_PORT")//set custom smtp port
                .toRecipients(new String[]{"duc.doanquang@gmail.com"})
                .ccRecipients(new String[]{"duc.doanquang@gmail.com"})
                .attachments(new File[]{new File("D:\\file.txt")})//optional: attach files
                .build()
                .send();
```

Returns true on success and false otherwise on failure


## Send SMS

### Using

* You just need to add the codes like below:
```java
ResultBO resultBo = new SMSServiceBuilder()
                .authentication("USER_NAME", "PASSWORD")//authen BULK SMS
                .cpCode("YOUR_CP_CODE")
                .serviceId("YOUR_SERVICE_ID")
                .msisdn("TO_PHONE_NUMBER")//receiver phone number
                .content("SMS_CONTENT")//sms content
                .build()
                .send();
```

ResultBO return message and result of performance results

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
