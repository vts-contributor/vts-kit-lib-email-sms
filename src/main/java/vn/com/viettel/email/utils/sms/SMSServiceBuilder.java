package vn.com.viettel.email.utils.sms;

import org.springframework.util.Assert;
import vn.com.viettel.email.utils.config.EmailConfigValue;
import vn.com.viettel.email.utils.config.SmsBrandNameConfigValue;
import vn.com.viettel.email.utils.email.EmailService;

import java.io.File;
//import com.viettel.vtm.client.request.auth.BasicEmail;


/**
 * Lib Support send Email
 *
 * @param_phone
 */
public class SMSServiceBuilder {

    private SmsBrandNameConfigValue smsBrandNameConfigValue;

    public SMSServiceBuilder() {
        smsBrandNameConfigValue = new SmsBrandNameConfigValue();
    }

    public SMSServiceBuilder url(String url){
        smsBrandNameConfigValue.setUrl(url);
        return this;
    }

    public SMSServiceBuilder xmlns(String xmlns){
        smsBrandNameConfigValue.setXmlns(xmlns);
        return this;
    }

    public SMSServiceBuilder authentication(String userName, String pw) {
        smsBrandNameConfigValue.setUserName(userName);
        smsBrandNameConfigValue.setPw(pw);
        return this;
    }

    public SMSServiceBuilder cpCode(String cpCode){
        smsBrandNameConfigValue.setCpCode(cpCode);
        return this;
    }

    public SMSServiceBuilder serviceId(String serviceId){
        smsBrandNameConfigValue.setServiceId(serviceId);
        return this;
    }

    public SMSServiceBuilder msisdn(String msisdn){
        smsBrandNameConfigValue.setMsisdn(msisdn);
        return this;
    }

    public SMSServiceBuilder content(String content){
        smsBrandNameConfigValue.setContent(content);
        return this;
    }

    public SMSServiceBuilder contentType(String contentType){
        smsBrandNameConfigValue.setContentType(contentType);
        return this;
    }

    public SMSServiceBuilder commandCode(String commandCode){
        smsBrandNameConfigValue.setCommandCode(commandCode);
        return this;
    }

    public SmsBrandNameService build() {
        Assert.hasLength(smsBrandNameConfigValue.getUserName(), " User cannot be be null");
        Assert.hasLength(smsBrandNameConfigValue.getPw(), "Password cannot be be null");
        Assert.hasLength(smsBrandNameConfigValue.getCpCode(), "CP Code cannot be be null");
        Assert.hasLength(smsBrandNameConfigValue.getServiceId(), "Service Id cannot be be null");
        Assert.hasLength(smsBrandNameConfigValue.getMsisdn(), "Msisdn cannot be be null");

        return new SmsBrandNameService(smsBrandNameConfigValue);
    }

}
