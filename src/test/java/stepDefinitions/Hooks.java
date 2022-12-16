package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@deletePlace")
    public void beforeScenario() throws IOException {
        AddPlaceStepDef sd = new AddPlaceStepDef();
        if(AddPlaceStepDef.placeId ==null){
            sd.add_place_payload_with("FakeUser", "Hindi", "fake address");
            sd.user_calls_api_with_http_request("addPlaceAPI", "POST");
            sd.validate_the_place_id_created_maps_to_using("FakeUser", "getPlaceAPI","GET");
        }

    }
}
