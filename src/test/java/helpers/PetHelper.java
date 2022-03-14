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
import org.apache.juneau.parser.ParseException;
import org.testng.annotations.*;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class PetHelper {


    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;


    @BeforeSuite
    public void setUp() {

        RestAssured.baseURI = Constants.baseURL;
        RestAssured.basePath = Constants.basePath;


        requestSpecification = given()
                .contentType(ContentType.JSON)
                .log()
                .all();

        responseSpecification = RestAssured.expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
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
                .when().post(Constants.endpoint)
                .then()
                .extract().response();

        return response;

    }


    public CreatePetResponse extractResponse(String response) throws ParseException {

        JsonParser jsonParser = JsonParser.create().ignoreUnknownBeanProperties().build();

        CreatePetResponse petResponse = jsonParser.parse(response, CreatePetResponse.class);


        return petResponse;

    }

}
