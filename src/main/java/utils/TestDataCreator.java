package utils;

import com.github.javafaker.Faker;

public class TestDataCreator {

    private Faker faker = new Faker();

    public String getRandomDogName() {

        return faker.dog().name();

    }

    public String getDogCategoryName() {
        return faker.dog().breed();
    }

    public int getRandomDogAge() {

        return Integer.parseInt(faker.dog().age());

    }


}
