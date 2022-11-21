# library support sms for spring boot

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
WS_URL_HTTP= http://10.60.106.216:8009/bulkapi
WS_URL_HTTPS= https://10.60.106.216:8998/bulkapi?wsdl
WS_XMLNS= http://impl.bulkSms.ws/
smsbrand_user= gpdnmt
smsbrand_pw= DNMT@258
smsbrand_cpcode= GPDN_MT
smsbrand_service_id= VTMAP
```

### Using

* Next, Define `SmsBrandNameService` instance.
```java
@Autowired
private SmsBrandNameService smsBrandNameService;
```

* Finaly, we just need to declare and use
```java
ResultBO resultBo = new SMSServiceBuilder()
                .authentication("user", "pw")
                .cpCode("cpCode")
                .serviceId("serviceId")
                .msisdn("0987654321")//receiver phone number
                .content("sms content")//sms content
                .build().send();
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