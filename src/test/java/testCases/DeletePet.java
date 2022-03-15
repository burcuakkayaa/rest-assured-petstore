package testCases;

import helpers.PetHelper;
import io.restassured.response.Response;
import org.apache.juneau.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tech.grasshopper.allure.Author;
import tech.grasshopper.allure.Authors;
import tech.grasshopper.allure.Categories;
import tech.grasshopper.allure.Category;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Categories({@Category("Pet Store")})
@Authors({@Author("Burcu Akkaya")})
public class DeletePet extends PetHelper {


    private long id;

    @BeforeMethod
    public void getId() {

        String name = testDataCreator.getRandomDogName();
        String categoryName = testDataCreator.getDogCategoryName();
        String photoUrl = testDataCreator.getFileName();
        String[] status = testDataCreator.getStatus();

        Response response = createNewPetResponse(0, name, categoryName, photoUrl, status[0]);

        response.then().assertThat().statusCode(200);

        id = response.jsonPath().getLong("id");
        System.out.println(id);


    }

    @Test
    public void deleteAvailablePet() throws NoSuchAlgorithmException {

        Response response = deletePetWithId(id);

        writeResponseAsPretty(response);

        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.jsonPath().getInt("code"),200);
        Assert.assertEquals(response.jsonPath().getString("type"),"unknown");
        Assert.assertEquals(Long.parseLong(response.jsonPath().getString("message")),id);

    }


    @Test
    public void invalidStatusValueError() throws NoSuchAlgorithmException {

        Response response = deletePetWithId(1111111111);

        writeResponseAsPretty(response);

        response.then().assertThat().statusCode(404);

    }
}
