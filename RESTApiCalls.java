package autothon.restapi.requests;

import static autothon.restapi.requests.GlobalData.LOGGER;
import static autothon.restapi.requests.GlobalData.NewOrderID;
import static autothon.restapi.requests.GlobalData.prop;
import java.util.HashMap;
import java.util.logging.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import autothon.restapi.requests.GlobalData;




/**
 * Below Class handles the different API request based on request types.
 * 
 * */



public class RESTApiCalls{

	public Response response=null;
	public JSONObject requestParams=null;
	public JsonPath jsonPathEvaluator=null;
	public JSONPayLoadParser jsonPayLoadParser=new JSONPayLoadParser();
	public CommonUtils cmutils= new CommonUtils();
	public RestAPICallInitiator restAPICallInitiator= new RestAPICallInitiator();

	public Response restAPIRequestInitiator(HashMap<String,String> RequestData){

		RestAssured.baseURI=prop.getProperty("baseURL");


		if(RequestData.get("RequestType").contains("New Order")){

			response = cmutils.getJSONPayLoadNewOrder();//gets the JSON Payload from file
			jsonPathEvaluator = response.jsonPath();
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				LOGGER.log(Level.INFO, "New Order ID is: "+NewOrderID);
			}
			else{

				LOGGER.log(Level.INFO, "New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Future Order")){
			response = cmutils.getJSONPayLoadFutureOrder();//gets the JSON Payload from file
			jsonPathEvaluator = response.jsonPath();
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				LOGGER.log(Level.INFO, "New Order ID is: "+NewOrderID);
			}
			else{

				LOGGER.log(Level.INFO,"New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Cancel Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("Cancel");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPUTRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("cancelOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Take Away")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("Takeaway");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPUTRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("takeawayOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Complete Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("Complete");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPUTRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("completeOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Fetch Order")){
			RequestSpecification request = RestAssured.given();
			response= restAPICallInitiator.sendGETRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("InValid Payload")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("InValidPayload");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		}

		else if(RequestData.get("RequestType").contains("InValid Origin")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("InValidOrderOrigin");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		}

		else if(RequestData.get("RequestType").contains("tripcostpayloadnotin9to5")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("tripcostpayloadnotin9to5");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		}

		else if(RequestData.get("RequestType").contains("tripcostpayloadin9to5")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("tripcostpayloadin9to5");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		}

		else if(RequestData.get("RequestType").contains("backdateorderpaylod")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("backdateorderpaylod");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		}
		
		
		else if(RequestData.get("RequestType").contains("drivingdistancecountpayload")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("drivingdistancecountpayload");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		}

		else{

			LOGGER.log(Level.INFO,"Request Data is not correct.: "+ response.getStatusCode());
			return null;
		}

		return response;

	}/*--- END OF MSG*/


	public boolean verifyResponseCode(Response response, int expectedStatusCode){


		LOGGER.log(Level.INFO,"Verifying status code came in response.");
		if(response.getStatusCode()==expectedStatusCode){
			LOGGER.log(Level.INFO,"Status code is matched.");
			return true;
		}
		else{
			LOGGER.log(Level.INFO,"Status code is not matched.");
			return false;
		}

	}

	public boolean verifyOrderIDInResponse(Response response, String ExpectedOrderID){
		JsonPath jsonPathEvaluator = response.jsonPath();
		LOGGER.log(Level.INFO,"Order ID from Response " + jsonPathEvaluator.get("id"));
		LOGGER.log(Level.INFO,"Expected Order ID: "+ ExpectedOrderID);
		LOGGER.log(Level.INFO,"Response Data " + jsonPathEvaluator.prettyPrint());

		if(ExpectedOrderID.equalsIgnoreCase((String) jsonPathEvaluator.get("id"))){


			LOGGER.log(Level.INFO,"Order ID in the response is same.");
			return true;

		}
		else{

			LOGGER.log(Level.INFO,"Order ID in the response is different: "+jsonPathEvaluator.get("id")+"from"+" Expected Order ID: "+ExpectedOrderID);
			return false;
		}
	}

	public boolean verifyMessageInResponse(Response response, String expectedmsg){
		JsonPath jsonPathEvaluator = response.jsonPath();
		//

		if(expectedmsg.equalsIgnoreCase((String) jsonPathEvaluator.get("message"))){


			LOGGER.log(Level.INFO,jsonPathEvaluator.get("message").toString());
			return true;

		}
		else{

			LOGGER.log(Level.INFO,"Expected Message in the response is different: "+jsonPathEvaluator.get("message")+"from"+" Expected Message: "+expectedmsg);
			return false;
		}
	}

	public String getFareCost(String jsonResponse, String attribute) {

		JSONObject mainObject = (JSONObject) JSONValue.parse(jsonResponse);
		Object mainFieldValue = mainObject.get("fare");
		String fare = mainFieldValue.toString();
		JSONObject subFieldJsonObject = (JSONObject) JSONValue.parse(fare);
		Object subFieldObject = subFieldJsonObject.get(attribute);
		String responseFieldValue = subFieldObject.toString();
		return responseFieldValue;
	} 

	public int getDistanceValueCount(String jsonResponse, String attribute) {
		
		JSONObject mainObject = (JSONObject) JSONValue.parse(jsonResponse);
		JSONArray mainFieldValue = (JSONArray)mainObject.get("drivingDistancesInMeters");
		int distanceCount= mainFieldValue.size();
		return distanceCount;
	} 

}
