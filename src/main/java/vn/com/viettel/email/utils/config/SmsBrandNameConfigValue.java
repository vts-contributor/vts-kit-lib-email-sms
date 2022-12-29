package vn.com.viettel.email.utils.config;

import lombok.Data;

@Data
public class SmsBrandNameConfigValue {
    private String url = null;
    private String xmlns = "http://impl.bulkSms.ws/";
    private String userName;
    private String pw;
    private String cpCode;//BULK SMS cap
    private String serviceId;//BULK SMS cap
    private String msisdn;// SDT nguoi nhan
    private String content;//Noi dung sms
    private String contentType = "0"; //0: khong dau, 1: co dau
    private String commandCode = "bulksms";// mac dinh cua bulksms

}
