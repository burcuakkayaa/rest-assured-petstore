package testCases;

import helpers.PetHelper;
import io.restassured.response.Response;
import models.response.CreatePetResponse;
import org.apache.juneau.parser.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;
import tech.grasshopper.allure.*;
import utils.TestDataCreator;

@Categories({ @Category("Pet Store") })
@Authors({ @Author("Burcu Akkaya") })
public class CreateNewPet extends PetHelper {



    TestDataCreator testDataCreator = new TestDataCreator();

    int id = testDataCreator.getId(0,1000);
    String name = testDataCreator.getRandomDogName();
    String categoryName = testDataCreator.getDogCategoryName();
    String photoUrl = testDataCreator.getFileName();
    String[] status = testDataCreator.getStatus();

    @Test
    public void createNewPet() throws ParseException {
        Response response = createNewPetResponse(id,name,categoryName,photoUrl,status[0]);

        System.out.println(response.then().extract().body().asPrettyString());

        String resp =  response.then().extract().body().asString();

        CreatePetResponse petResponse = extractResponse(resp);

        Assert.assertEquals(id,petResponse.getId());
        Assert.assertEquals(name,petResponse.getName());
        Assert.assertEquals(categoryName,petResponse.getCategory().getName());
        Assert.assertEquals(photoUrl,petResponse.getPhotoUrls().get(0));
        Assert.assertEquals(status[0],petResponse.getStatus());
        Assert.assertEquals(id,petResponse.getTags().get(0).getId());
        Assert.assertEquals(name,petResponse.getTags().get(0).getName());

    }




}
