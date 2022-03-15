package testCases;


import helpers.PetHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tech.grasshopper.allure.Author;
import tech.grasshopper.allure.Authors;
import tech.grasshopper.allure.Categories;
import tech.grasshopper.allure.Category;

import java.util.ArrayList;
import java.util.List;

@Categories({@Category("Pet Store")})
@Authors({@Author("Burcu Akkaya")})
public class FindPetByStatus extends PetHelper {

    @Test
    public void findAvailablePet() {

        Response response = findByStatusResponse("available");

        response.then()
                .assertThat()
                .statusCode(200);

        writeResponseAsPretty(response);

        System.out.println(response.jsonPath().getList("id").toString());

    }


}
