package laba_2;

import java.util.Random;

public class RandomNumberGenerator {
	 public static int generateRandomNumber() {
	        Random random = new Random();
	        return random.nextInt(71) + 10; // Генерируем случайное число от 10 до 80
	    }
}
