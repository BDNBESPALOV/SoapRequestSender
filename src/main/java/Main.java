 import org.slf4j.Logger;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.util.Properties;

 public class Main {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Main main = new Main();
        try {
            Properties property = new Properties();
            FileInputStream fisMain  = new FileInputStream("Service.properties");
            property.load(fisMain);
            String BPM301P8003 = property.getProperty("BPM301P.8003");
            String BPM302P8003 = property.getProperty("BPM302P.8003");
            String BPM311P8003 = property.getProperty("BPM311P.8003");
            String BPM312P8003 = property.getProperty("BPM312P.8003");
            String BPM301P8004 = property.getProperty("BPM301P.8004");
            String BPM302P8004 = property.getProperty("BPM302P.8004");
            String BPM311P8004 = property.getProperty("BPM311P.8004");
            String BPM312P8004 = property.getProperty("BPM312P.8004");
            String BPM303P8003 = property.getProperty("BPM303P.8003");
            String BPM303P8004 = property.getProperty("BPM303P.8004");
            String BPM313P8003 = property.getProperty("BPM313P.8003");
            String BPM313P8004 = property.getProperty("BPM313P.8004");
            String xmlResponse_log = property.getProperty("xmlResponse.log");
            String xmlRequest = property.getProperty("xmlRequest");

            main.request(BPM301P8003,xmlRequest,"ASUGF-BPM301P.8003 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM301P8004,xmlRequest,"ASUGF-BPM301P.8004 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM302P8003,xmlRequest,"ASUGF-BPM302P.8003 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM302P8004,xmlRequest,"ASUGF-BPM302P.8004 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM311P8003,xmlRequest,"ASUGF-BPM311P.8003 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM311P8004,xmlRequest,"ASUGF-BPM311P.8004 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM312P8003,xmlRequest,"ASUGF-BPM312P.8003 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM312P8004,xmlRequest,"ASUGF-BPM312P.8004 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM303P8003,xmlRequest,"ASUGF-BPM303P.8003 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM303P8004,xmlRequest,"ASUGF-BPM303P.8004 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM313P8003,xmlRequest,"ASUGF-BPM313P.8003 time=", Boolean.valueOf(xmlResponse_log));
            main.request(BPM313P8004,xmlRequest,"ASUGF-BPM313P.8004 time=", Boolean.valueOf(xmlResponse_log));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void request(String url,String xmlRequest,String hostname, Boolean xmlResponse_log){
        SoapRequestSender sender = new  SoapRequestSender(url);
        long startTime = System.nanoTime();
        String xmlResponse = sender.sendSoapRequest(xmlRequest);
        long endTime = System.nanoTime();
        if (xmlResponse_log)
            log.info(xmlResponse);
        String str = xmlResponse;
        if(str.contains("2017-08-02T15:00:19+03:00")){
            log.info("Successfully");
            log.info("Response time for "+hostname+((endTime - startTime) / 1000000) + " ms " );
        }
        else {
            log.info("Error");
        }
    }
}
