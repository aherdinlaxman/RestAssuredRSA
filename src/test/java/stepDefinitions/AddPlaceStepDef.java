package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static constants.Parameters.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlaceStepDef extends Utils {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
     static Response response;
    TestDataBuild testdata = new TestDataBuild();
    static String placeId;
    @Given("delete place payload")
    public void delete_place_payload() throws IOException {

         requestSpecification =given().spec(requestSpecification())
                .body(testdata.deletePlacePayload(placeId));
    }
    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        requestSpecification =given().spec(requestSpecification())
            .body(testdata.addPlacePayload( name,  language,  address));
    }
    @When("User calls {string} with {string} http request")
    public void user_calls_api_with_http_request(String resource,String httpmethod) {
        //constructor will be called as per value we pass in feature file and respective resource will be passed to request
        ApiResources ar = ApiResources.valueOf( resource);
        System.out.println(ar.getResource());
        responseSpecification =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
                if(httpmethod.equalsIgnoreCase("POST"))
        response = requestSpecification.when().post(ar.getResource());
                else if(httpmethod.equalsIgnoreCase("GET"))
        response = requestSpecification.when().get(ar.getResource());
               else if(httpmethod.equalsIgnoreCase("DELETE"))
        response = requestSpecification.when().delete(ar.getResource());
               else if(httpmethod.equalsIgnoreCase("PUT"))
        response = requestSpecification.when().put(ar.getResource());

    }
    @Then("Api call got success with status code {int}")
    public void api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }

    @And("validate the response {string} as {string}")
    public void validate_the_response_something_as_something(String key, String expectedValue)  {

        assertEquals(getJsonpath(key,response),expectedValue);
    }
    @And("validate the placeId created maps to {string} using {string} and {string} method")
    public void validate_the_place_id_created_maps_to_using(String Expected_name, String request, String method) throws IOException {
        placeId = getJsonResponseAsString(PLACE_ID, response);
        requestSpecification =given().spec(requestSpecification()).queryParam(PLACE_ID, placeId);
        user_calls_api_with_http_request(request,method);
        String actualName = getJsonpath(NAME, response);
        assertEquals(Expected_name,actualName);

    }

}
