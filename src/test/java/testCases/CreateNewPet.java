package testCases;

import helpers.PetHelper;
import io.restassured.response.Response;
import models.response.CreatePetResponse;
import org.apache.juneau.parser.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;
import tech.grasshopper.allure.*;


@Categories({@Category("Pet Store")})
@Authors({@Author("Burcu Akkaya")})
public class CreateNewPet extends PetHelper {

    int id = testDataCreator.getId(0, 1000);
    String name = testDataCreator.getRandomDogName();
    String categoryName = testDataCreator.getDogCategoryName();
    String photoUrl = testDataCreator.getFileName();
    String[] status = testDataCreator.getStatus();

    @Test
    public void createNewPet() throws ParseException {
        Response response = createNewPetResponse(id, name, categoryName, photoUrl, status[0]);

        response.then().assertThat().statusCode(200);

        writeResponseAsPretty(response);

        String resp = convertStringResponse(response);

        CreatePetResponse petResponse = extractResponse(resp);

        Assert.assertEquals(id, petResponse.getId());
        Assert.assertEquals(name, petResponse.getName());
        Assert.assertEquals(categoryName, petResponse.getCategory().getName());
        Assert.assertEquals(photoUrl, petResponse.getPhotoUrls().get(0));
        Assert.assertEquals(status[0], petResponse.getStatus());
        Assert.assertEquals(id, petResponse.getTags().get(0).getId());
        Assert.assertEquals(name, petResponse.getTags().get(0).getName());


    }


}
