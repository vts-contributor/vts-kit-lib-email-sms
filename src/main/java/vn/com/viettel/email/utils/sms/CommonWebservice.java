package vn.com.viettel.email.utils.sms;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class CommonWebservice {
    public CommonWebservice() {
    }

    public static String marshal(Object object) throws Exception {
        if (object == null) {
            return "";
        } else {
            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{object.getClass()});
            Marshaller unmarshaller = jaxbContext.createMarshaller();
            unmarshaller.marshal(object, sw);
            return sw.toString().replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>", "");
        }
    }

    public static Object unmarshal(String responseBody, Class aClass) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{aClass});
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(new StringReader(responseBody));
    }
}