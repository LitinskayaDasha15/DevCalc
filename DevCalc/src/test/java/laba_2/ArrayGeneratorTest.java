package laba_2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayGeneratorTest {

	private static int[] arrayGenerator;

	@BeforeClass
	public static void setUp() {
		arrayGenerator = ArrayGenerator.generateRandomArray();
	}

	@Test
	public void testArraySize() {
		assertEquals("Размер массива не равен 7", 7, arrayGenerator.length);
	}

	@Test
	public void testArrayElementsNotNull() {
		for (int element : arrayGenerator) {
			assertNotNull("Элемент массива равен null", element);
		}
	}
}
