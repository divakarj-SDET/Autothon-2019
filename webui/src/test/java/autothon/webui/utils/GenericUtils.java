package autothon.webui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;


public class GenericUtils {
	static Logger log = Logger.getLogger(GenericUtils.class);
	
    private static GenericUtils instance;
    private static final Object lock = new Object();
    private String strBrowserName;
    private String strURL;
    private String strUserName;
    private String strTimeOuts;
    private String strPassWord;

    private String strCurrentDirectory = System.getProperty("user.dir");

    public static GenericUtils getInstance() throws IOException {
        if (instance == null) {
            synchronized (lock) {
                instance = new GenericUtils();
                instance.readPropFile();
            }
        }
        return instance;
    }



    private void readPropFile() throws IOException {
        Properties prop = new Properties();
        String strConfigPropFile = strCurrentDirectory+"\\config.properties";
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(strConfigPropFile));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            Reporting.FailTest("Properties file should be found","Configuraiton propeprties file cannot be found");
            try {
                strURL = prop.getProperty("url");
                strBrowserName = prop.getProperty("browser");
                strUserName = prop.getProperty("username");
                strPassWord = prop.getProperty("password");
            }catch(Exception propertyException){
                Reporting.FailTest("All values should be fetched from properties",propertyException.getMessage());
            }

        }

    }

    public String getStrBrowserName() {
        return strBrowserName;
    }

    public String getStrURL() {
        return strURL;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public String getStrTimeOuts() {
        return strTimeOuts;
    }

    public String getStrPassWord() {
        return strPassWord;
    }
    
    
}
