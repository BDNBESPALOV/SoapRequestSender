

import javax.xml.soap.*;
import java.io.*;
import java.net.*;

public class SoapRequestSender {
    private String url;

    public SoapRequestSender(String url) {
        this.url = url;
    }

    public String sendSoapRequest(String xmlRequest) {
        try {
            // Create a connection to the SOAP web service
            URL endpoint = new URL(url);
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnectionFactory.createConnection();

            // Create a SOAP message from the XML request
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xmlRequest.getBytes()));

            // Send the SOAP message and get the response
            SOAPMessage response = connection.call(soapMessage, endpoint);

            // Extract the response from the SOAP message and return it as a string
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            response.writeTo(output);
            String xmlResponse = new String(output.toByteArray());
            return xmlResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
