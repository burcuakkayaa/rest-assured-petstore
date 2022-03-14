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

    public int getId(int min, int max) {
        return Integer.parseInt(String.valueOf(faker.number().numberBetween(min, max)));
    }

    public String getRandomCatName() {
        return faker.cat().name();
    }

    public String getCatCategoryName() {
        return faker.cat().breed();
    }

    public String getFileName() {
        return faker.file().fileName();
    }


    public String[] getStatus() {

        return new String[] {"available", "pending", "sold"};
    }

}
