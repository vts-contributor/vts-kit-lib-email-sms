package vn.com.viettel.email.utils.sms.bo;

import lombok.Data;

import javax.xml.bind.annotation.*;

 @Data
@XmlRootElement(
        name = "wsCpMt"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "wsCpMt",
        propOrder = {"user", "password", "cpCode", "requestID", "userID", "receiverID", "serviceID", "commandCode", "content", "contentType"}
)
public class WsCpMt {
    @XmlElement(
            name = "User"
    )
    protected String user;
    @XmlElement(
            name = "Password"
    )
    protected String password;
    @XmlElement(
            name = "CPCode"
    )
    protected String cpCode;
    @XmlElement(
            name = "RequestID"
    )
    protected String requestID;
    @XmlElement(
            name = "UserID"
    )
    protected String userID;
    @XmlElement(
            name = "ReceiverID"
    )
    protected String receiverID;
    @XmlElement(
            name = "ServiceID"
    )
    protected String serviceID;
    @XmlElement(
            name = "CommandCode"
    )
    protected String commandCode;
    @XmlElement(
            name = "Content"
    )
    protected String content;
    @XmlElement(
            name = "ContentType"
    )
    protected String contentType;
}
