package vn.com.viettel.email.utils.sms.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
        name = "return"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "resultBO",
        propOrder = {"message", "result"}
)
public class ResultBO {
    protected String message;
    protected Long result;

    public ResultBO() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public Long getResult() {
        return this.result;
    }

    public void setResult(Long value) {
        this.result = value;
    }

    public String toString() {
        return "ResultBO [message=" + this.message + ", result=" + this.result + "]";
    }
}