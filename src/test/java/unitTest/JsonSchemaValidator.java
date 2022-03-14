package unitTest;

import implementation.Category;
import implementation.Tag;
import models.request.CreatePetRequest;
import models.response.CreatePetResponse;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonSchemaValidator {



    private String jsonVal;

    public JsonSchemaValidator() throws IOException {
        jsonVal = new String(Files.readAllBytes(Paths.get("src//test//resources//createPetbody.json")));
    }

    @Test
    public void createPetBodyTest() throws SerializeException {

        Category category = new Category(5, "rex");

        String[] photoUrls = new String[]{"photoname"};

        Tag[] tag = new Tag[]{new Tag(2, "dog")};


        CreatePetRequest createPetRequest = new CreatePetRequest.Builder()
                .withId(0)
                .withCategory(category)
                .withName("burcu")
                .withPhotoUrls(photoUrls)
                .withTags(tag)
                .withStatus("available")
                .build();


        JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
        String json = jsonSerializer.serialize(createPetRequest);


        System.out.println(json);


    }

    @Test
    public void createPetBodyResponse() throws ParseException {


        JsonParser jsonParser = JsonParser.create().ignoreUnknownBeanProperties().build();


        CreatePetResponse petResponse = jsonParser.parse(jsonVal, CreatePetResponse.class);

        System.out.println(petResponse.toString());

        Assert.assertEquals(petResponse.getCategory().getId(),5);
    }
}
