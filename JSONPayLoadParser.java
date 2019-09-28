package autothon.restapi.requests;

import static autothon.restapi.requests.GlobalData.LOGGER;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONPayLoadParser {


	public JSONObject readJSONPayloadFromFile(String requestType)
	{
		//JSON parser object to read the JSON file.
		JSONParser jsonParser = new JSONParser();

		try
		{
			if(requestType.contains("Cancel"))
			{
				FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/cancelpayload.json"));


				//Read JSON file
				Object obj = jsonParser.parse(reader);

				JSONObject payloadlist = (JSONObject) obj;
				//LOGGER.log(Level.INFO, "payloadlist: "+payloadlist);
				return payloadlist;            
			}

			else  if(requestType.contains("Complete"))
			{
				FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/completepayload.json"));


				//Read JSON file
				Object obj = jsonParser.parse(reader);

				JSONObject payloadlist = (JSONObject) obj;
				//LOGGER.log(Level.INFO, "payloadlist: "+payloadlist);

				return payloadlist;            
			}  

			else  if(requestType.contains("Takeaway"))
			{
				FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/takeawaypayload.json"));


				//Read JSON file
				Object obj = jsonParser.parse(reader);

				JSONObject payloadlist = (JSONObject) obj;
				return payloadlist;            
			}  

			else if(requestType.contains("NewOrder")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/placeorderpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

				/*Iterator<?> iterator = storelocation.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}*/
				return placeOrderJSON;

			}

			else if(requestType.contains("FutureOrder")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/futureorderpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				Date date = new Date();  
				JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

				/*Iterator<?> iterator = storelocation.iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
				}*/
				return placeOrderJSON;

			}


			else if(requestType.contains("InValidPayload")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/invalidneworderpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}

			else if(requestType.contains("InValidOrderOrigin")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/invalidorderdestination.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}
			
			//tripcostpayloadnotin9to5.json
			else if(requestType.contains("tripcostpayloadnotin9to5")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/tripcostpayloadnotin9to5.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}
			
			else if(requestType.contains("tripcostpayloadin9to5")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/tripcostpayloadin9to5.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}
			
			
			else if(requestType.contains("backdateorderpaylod")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/backdateorderpaylod.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}
			
			else if(requestType.contains("drivingdistancecountpayload")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/drivingdistancecountpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}
			
			
			else{
				return null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}
}
