package laba_2;

public class BubbleSort {
  public static int[] sort(int[] array) {
    int n = array.length;
    boolean swapped;

    do {
      swapped = false;

      for (int i = 1; i < n; i++) {
        if (array[i - 1] > array[i]) {
          // Обмен элементов, если они стоят в неправильном порядке
          int temp = array[i - 1];
          array[i - 1] = array[i];
          array[i] = temp;
          swapped = true;
        }
      }

      // Уменьшаем размер области сравнения после каждой итерации
      n--;
    } while (swapped);
    return array;
  }

}