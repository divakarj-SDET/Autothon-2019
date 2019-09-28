package autothon.restapi.requests;

import static autothon.restapi.requests.GlobalData.prop;

import org.json.simple.JSONObject;

import autothon.restapi.requests.CommonUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPICallInitiator {

	public Response response=null;
	public JSONObject requestParams=null;
	public JsonPath jsonPathEvaluator=null;
	public JSONPayLoadParser jsonPayLoadParser=new JSONPayLoadParser();
	public CommonUtils cmutils= new CommonUtils();



	/**
	 * Below Class is Refactor class for different API requests.
	 * 
	 * */


	public Response sendPOSTRequest(RequestSpecification request,String URL){
		//request = RestAssured.given();
		RestAssured.baseURI=prop.getProperty("baseURL");
		response = request.post(URL);
		return response;
	}

	public Response sendPUTRequest(RequestSpecification request,String URL){
		RestAssured.baseURI=prop.getProperty("baseURL");
		response = request.put(URL);
		return response;
	}

	public Response sendGETRequest(RequestSpecification request,String URL){
		RestAssured.baseURI=prop.getProperty("baseURL");
		response = request.get(URL);
		return response;
	}
}
