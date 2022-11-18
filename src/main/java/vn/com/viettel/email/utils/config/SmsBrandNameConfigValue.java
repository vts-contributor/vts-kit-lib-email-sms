package vn.com.viettel.email.utils.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class SmsBrandNameConfigValue {
    private String url = "http://10.60.106.216:8009/bulkapi";
    private String xmlns = "http://impl.bulkSms.ws/";
    private String userName;
    private String pw;
    private String cpCode;
    private String serviceId;
    private String msisdn;
    private String content;
    private String contentType = "0"; //0: khong dau, 1: co dau
    private String commandCode = "bulksms";

}
