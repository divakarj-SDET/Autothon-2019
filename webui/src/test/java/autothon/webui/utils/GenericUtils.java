package autothon.webui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;


public class GenericUtils {
	static Logger log = Logger.getLogger(GenericUtils.class);
	
    private static GenericUtils instance;
    private static final Object lock = new Object();
    private String strPassWord;
    
   



	private String strUiURL;
    private String strApiURL;
    private String strUser;
    private String strAdmin;
    private String strCurrentDirectory = System.getProperty("user.dir");

    /**
     * This method is used to initiated the properties file
     * @return
     * @throws IOException
     */
    public static GenericUtils getInstance() throws IOException {
        if (instance == null) {
            synchronized (lock) {
                instance = new GenericUtils();
                instance.readPropFile();
            }
        }
        return instance;
    }



    /**
     * This method is used to read the properties file and set the values
     * @throws IOException
     */
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
                strUiURL = prop.getProperty("ui.url");
                strApiURL = prop.getProperty("api.url");
                strUser = prop.getProperty("user.username");
                strAdmin = prop.getProperty("user.username");
                strPassWord = prop.getProperty("password");
                
                
            }catch(Exception propertyException){
                Reporting.FailTest("All values should be fetched from properties",propertyException.getMessage());
            }

        }

    }

    public String getStrPassWord() {
		return strPassWord;
	}



	public String getStrUiURL() {
		return strUiURL;
	}



	public String getStrApiURL() {
		return strApiURL;
	}



	public String getStrUser() {
		return strUser;
	}



	public String getStrAdmin() {
		return strAdmin;
	}
   
    
}
