package laba_2;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



public class BubbleSortTest {

  private static int[] array;

  @BeforeClass
  public static void setUp() {
    array = ArrayGenerator.generateRandomArray();

  }

  @Test
  public void testBubbleSort() {
    array = BubbleSort.sort(array);
  }

  @AfterClass
  public static void tearDown() {
    // Проверка, что массив отсортирован правильно
    for (int i = 0; i < array.length - 1; i++) {
      assertTrue("Сортировка выполнена неправильно", array[i] < array[i + 1]);
    }

  }
}