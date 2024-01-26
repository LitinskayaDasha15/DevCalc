package laba_2;

import static org.junit.Assert.*;

import org.junit.Test;

import laba_2.RandomNumberGenerator;

public class RandomNumberGeneratorTest {

	@Test
	public void testRandomNumberInRange() {
		// Генерация случайного числа
		int randomValue = RandomNumberGenerator.generateRandomNumber();

		// Проверка, что сгенерированное число находится в диапазоне от 10 до 80
		assertTrue("Сгенерированное число вне диапазона [10, 80]: " + randomValue,
				randomValue >= 10 && randomValue <= 80);
	}

}
