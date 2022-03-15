package testCases;

import helpers.PetHelper;
import io.restassured.response.Response;
import models.response.CreatePetResponse;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tech.grasshopper.allure.Author;
import tech.grasshopper.allure.Authors;
import tech.grasshopper.allure.Categories;
import tech.grasshopper.allure.Category;

@Categories({@Category("Pet Store")})
@Authors({@Author("Burcu Akkaya")})
public class UpdatePet extends PetHelper {

    private long id;
    String name = testDataCreator.getRandomDogName();
    String categoryName = testDataCreator.getDogCategoryName();
    String photoUrl = testDataCreator.getFileName();
    String[] status = testDataCreator.getStatus();

    @BeforeMethod
    public void getId() {

        Response response = createNewPetResponse(0, name, categoryName, photoUrl, status[0]);

        response.then().assertThat().statusCode(200);

        id = response.jsonPath().getLong("id");
        System.out.println(id);


    }

    @Test
    public void updatePet() throws ParseException, SerializeException {

        Response response = updatePetResponse(id, name, status[1]);

        response.then().assertThat().statusCode(200);

        writeResponseAsPretty(response);

        String resp = convertStringResponse(response);

        CreatePetResponse petResponse = extractResponse(resp);

        Assert.assertEquals(id, petResponse.getId());
        Assert.assertEquals(name, petResponse.getName());
        Assert.assertEquals(status[1], petResponse.getStatus());

    }
}
