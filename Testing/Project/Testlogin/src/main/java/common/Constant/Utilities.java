package common.Constant;

import java.util.Random;

public class Utilities {
    public static String generateRandomEmail() {
        Random rand = new Random();
        return "testuser" + rand.nextInt(100000) + "@example.com";
    }

    public static String generateRandomPassword() {
        return "Password" + new Random().nextInt(1000) + "!";
    }
}
