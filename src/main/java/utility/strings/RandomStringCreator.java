package utility.strings;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class RandomStringCreator {
    public RandomStringCreator() {
    }
    public static String createRandomString(){
        return randomAlphabetic(6);
    }
}
