package helpers;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Constants;

public class BaseTest {

    @BeforeSuite
    public void setUp() {

        RestAssured.baseURI = Constants.baseURL;
        RestAssured.basePath = Constants.basePath;
    }

    @AfterSuite
    public void tearDown() {

    }
}
