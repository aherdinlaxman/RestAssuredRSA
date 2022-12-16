package resources;

public enum ApiResources {
    // Enum is a special class which has collection of constants or methods
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String resource;

    ApiResources(String resource) {
        this.resource = resource;
    }
    public String getResource(){

        return resource;
    }
}
