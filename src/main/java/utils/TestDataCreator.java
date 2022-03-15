package utils;

import com.github.javafaker.Faker;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

        return new String[]{"available", "pending", "sold"};
    }


    public String generateKey(int keyLength) throws NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLength);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();

    }

}
