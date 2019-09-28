package autothon.restapi.requests;

import static autothon.restapi.requests.GlobalData.LOGGER;
import static autothon.restapi.requests.GlobalData.prop;

import java.util.logging.Level;

import org.json.simple.JSONObject;

import autothon.restapi.requests.JSONPayLoadParser;
import autothon.restapi.requests.RestAPICallInitiator;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



/**
 * Below Class is CommonUtils class which take cares JSONPayload for New Order and Future Order.
 * 
 * */




public class CommonUtils {
	Response response=null;
	JSONObject requestParams=null;
	JSONPayLoadParser jsonPayLoadParser=new JSONPayLoadParser();
	

	public Response getJSONPayLoadNewOrder() {
		RestAPICallInitiator restAPICallInitiator= new RestAPICallInitiator();
		RequestSpecification request = RestAssured.given();
		requestParams = jsonPayLoadParser.readJSONPayloadFromFile("NewOrder");
		request.body(requestParams.toJSONString());
		//LOGGER.log(Level.INFO, "JSON payload: "+ requestParams.toJSONString());
		response=restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		return response;

	}

	public Response getJSONPayLoadFutureOrder() {
		RestAPICallInitiator restAPICallInitiator= new RestAPICallInitiator();
		RequestSpecification request = RestAssured.given();
		requestParams = jsonPayLoadParser.readJSONPayloadFromFile("FutureOrder");
		request.body(requestParams.toJSONString());
		//LOGGER.log(Level.INFO, "JSON payload: "+ requestParams.toJSONString());
		response=restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		return response;

	}

}
