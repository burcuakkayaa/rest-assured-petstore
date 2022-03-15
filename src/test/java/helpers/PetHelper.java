package helpers;

import implementation.Category;
import implementation.Tag;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.NoArgsConstructor;
import models.request.CreatePetRequest;
import models.response.CreatePetResponse;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.testng.annotations.*;
import utils.Constants;
import utils.TestDataCreator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class PetHelper {


    protected TestDataCreator testDataCreator = new TestDataCreator();


    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;


    public void writeResponseAsPretty(Response response) {

        System.out.println(response.then().extract().body().asPrettyString());
    }

    public String convertStringResponse(Response response) {

        return  response.then().extract().body().asString();
    }

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = Constants.baseURL;
        RestAssured.basePath = Constants.basePath;


        requestSpecification = given()
                .contentType(ContentType.JSON)
                .log()
                .all();

        responseSpecification = RestAssured.expect()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }

    public Response createNewPetResponse(int id, String name, String categoryName, String photoUrl, String status) {

        Category category = new Category(id, categoryName);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(photoUrl);

        List<Tag> tag = new ArrayList<>();
        tag.add(new Tag(id, name));

        CreatePetRequest petRequestBody = new CreatePetRequest.Builder()
                .withId(id)
                .withCategory(category)
                .withName(name)
                .withPhotoUrls(photoUrls)
                .withTags(tag)
                .withStatus(status)
                .build();

        Response response = given().spec(requestSpecification).filter(new AllureRestAssured())
                .body(petRequestBody)
                .when().post(Constants.Endpoints.endpoint)
                .then()
                .extract().response();

        return response;

    }


    public CreatePetResponse extractResponse(String response) throws ParseException {

        JsonParser jsonParser = JsonParser.create().ignoreUnknownBeanProperties().build();

        CreatePetResponse petResponse = jsonParser.parse(response, CreatePetResponse.class);


        return petResponse;

    }


    public Response findByStatusResponse(String status) {

        Response response = given().spec(requestSpecification).filter(new AllureRestAssured())
                .queryParam("status",status)
                .when().get(Constants.Endpoints.findByEndpoint)
                .then()
                .extract().response();

        return response;
    }


    public Response deletePetWithId(long id) throws NoSuchAlgorithmException {

        Response response = given().spec(requestSpecification).filter(new AllureRestAssured())
                .header("api_key", testDataCreator.generateKey(128))
                .when().delete(Constants.Endpoints.endpoint + "/" + id)
                .then()
                .extract().response();

        return response;

    }


    public Response updatePetResponse(long id, String name, String status) throws SerializeException {

        CreatePetRequest createPetRequest = new CreatePetRequest.Builder()
                .withId(id)
                .withName(name)
                .withStatus(status)
                .build();

        JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
        String json = jsonSerializer.serialize(createPetRequest);

        Response response = given().spec(requestSpecification).filter(new AllureRestAssured())
                .body(json)
                .when().put(Constants.Endpoints.endpoint)
                .then()
                .extract().response();

        return response;

    }
}
