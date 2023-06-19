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

            String xmlRequest = property.getProperty("xmlRequest");
            main.request(BPM301P8003,xmlRequest,"ASUGF-BPM301P.8003");
            main.request(BPM301P8004,xmlRequest,"ASUGF-BPM301P.8004");
            main.request(BPM302P8003,xmlRequest,"ASUGF-BPM302P.8003");
            main.request(BPM302P8004,xmlRequest,"ASUGF-BPM302P.8004");
            main.request(BPM311P8003,xmlRequest,"ASUGF-BPM311P.8003");
            main.request(BPM311P8004,xmlRequest,"ASUGF-BPM311P.8004");
            main.request(BPM312P8003,xmlRequest,"ASUGF-BPM312P.8003");
            main.request(BPM312P8004,xmlRequest,"ASUGF-BPM312P.8004");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void request(String url,String xmlRequest,String hostname){
        SoapRequestSender sender = new  SoapRequestSender(url);
        long startTime = System.nanoTime();
        String xmlResponse = sender.sendSoapRequest(xmlRequest);
        long endTime = System.nanoTime();
        //log.info(xmlResponse);
        log.info(hostname+" "+((endTime - startTime) / 1000000) + " ms " );
    }
}