package autothon.restapi.requests;

import static autothon.restapi.requests.GlobalData.LOGGER;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalData {

	public static String NewOrderID=null;
	public static Properties prop = new Properties();
	public final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	public static void readConfigFile() {


		InputStream input = null;

		try {

			input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			LOGGER.log(Level.INFO, "Base URL "+prop.getProperty("baseURL"));
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
