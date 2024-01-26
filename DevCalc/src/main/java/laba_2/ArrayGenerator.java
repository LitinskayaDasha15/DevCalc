package laba_2;

public class ArrayGenerator {
	public static int[] generateRandomArray() {
		int[] array = new int[7];
		for (int i = 0; i < array.length; i++) {
			array[i] = RandomNumberGenerator.generateRandomNumber();
		}

		return array;
	}
}
