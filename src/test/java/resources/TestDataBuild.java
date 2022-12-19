package resources;

import com.github.javafaker.Faker;
import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    Faker faker = new Faker();
        public String deletePlacePayload(String id) {
        DeletePlace dp = new DeletePlace();
        dp.setPlace_id(id);

        return "{\r\n    \"place_id\":\""+id+"\"\r\n}";
         }


        public AddPlace addPlacePayload() {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(faker.address().streetAddress());
        p.setLanguage("French-IN");
        p.setPhone_number(faker.phoneNumber().phoneNumber());
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(faker.address().streetSuffix());
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }
    public AddPlace addPlacePayload(String name, String language, String address) {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }
}