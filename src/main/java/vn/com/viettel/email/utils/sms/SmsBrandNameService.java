package vn.com.viettel.email.utils.sms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.viettel.email.utils.config.SmsBrandNameConfigValue;
import vn.com.viettel.email.utils.sms.bo.ResultBO;
import vn.com.viettel.email.utils.sms.bo.WsCpMt;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class SmsBrandNameService {
    private static final Logger LOGGER = Logger.getLogger(SmsBrandNameService.class);

    private SmsBrandNameConfigValue config;

    public SmsBrandNameService(SmsBrandNameConfigValue config) {
        this.config = config;
    }

    /**
     * gui tin nhan thuong hieu
     *
     * @return
     */
    public ResultBO send() {
        ResultBO result = null;
        String msisdn = config.getMsisdn();
        String content = config.getContent();
        try {
            WsCpMt mt = new WsCpMt();
            mt.setUser(config.getUserName());
            mt.setPassword(config.getPw());
            mt.setCpCode(config.getCpCode());
            mt.setRequestID("1");
            mt.setReceiverID(msisdn);
            mt.setUserID(msisdn);
            mt.setCommandCode(config.getCommandCode());
            mt.setContent(content);
            mt.setServiceID(config.getServiceId());
            mt.setContentType(config.getContentType());//0: khong dau, 1: co dau

            result = sendMT(mt);

        } catch (Exception e) {
            LOGGER.error("sendSMS", e);
        }

        return result;
    }

    /**
     * Gui tin nhan den nguoi dung
     *
     * @param smsInfo
     * @return
     */
    private ResultBO sendMT(WsCpMt smsInfo) {
        ResultBO resultBO = new ResultBO();

        StringBuilder xmlInput = new StringBuilder();
        try {

            String request = CommonWebservice.marshal(smsInfo).replaceAll("wsCpMt", "impl:wsCpMt");
            String responseString;
            String outputString = "";
            URL url = new URL(config.getUrl());

            URLConnection connection = url.openConnection();

            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            xmlInput.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"");
            xmlInput.append(" xmlns:impl=\"");
            xmlInput.append(config.getXmlns());
            xmlInput.append("\">");
            xmlInput.append("<soapenv:Header/>");
            xmlInput.append("<soapenv:Body>");
            xmlInput.append(request);
            xmlInput.append("</soapenv:Body>");
            xmlInput.append("</soapenv:Envelope>");

            String strXmlInput = xmlInput.toString();

            byte[] buffer = strXmlInput.getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "\"\"";

            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();

            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }

            int indexReturn = outputString.indexOf("return");
            int indexe = outputString.lastIndexOf("return");
            if (indexReturn > 0) {
                String strReturn = outputString.substring(indexReturn - 1, indexe + "return" .length() + 1);
                if (strReturn != null) {
                    resultBO = (ResultBO) CommonWebservice.unmarshal(strReturn, ResultBO.class);
                }
            }
        } catch (ConnectException e) {
            resultBO.setResult(BulkSmsErrorCode.CONNECTION_REFUSED_500.getCode());
            resultBO.setMessage(e.getMessage());

            LOGGER.error("sendMT", e);
        } catch (SocketTimeoutException e) {
            resultBO.setResult(BulkSmsErrorCode.CONNECTION_TIMEOUT_605.getCode());
            resultBO.setMessage(BulkSmsErrorCode.CONNECTION_TIMEOUT_605.getMessage());
            LOGGER.error("sendMT", e);
        } catch (Exception e) {
            resultBO.setResult(BulkSmsErrorCode.ERROR_999.getCode());
            resultBO.setMessage(e.getMessage());
            LOGGER.error("sendMT", e);
        }

        return resultBO;
    }

}
