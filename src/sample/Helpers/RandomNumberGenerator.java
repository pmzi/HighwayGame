package sample.Helpers;

import java.util.Random;

/**
 * Created by pmzi on 7/9/2018.
 */
public class RandomNumberGenerator {

    public static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

}
