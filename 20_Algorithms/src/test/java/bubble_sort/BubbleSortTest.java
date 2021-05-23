package bubble_sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    @Test
    @DisplayName("Проверка метода sort")
    public void sortTest(){
        BubbleSort bubbleSort = new BubbleSort();
        int length = 5;
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++){
            array[i] = random.nextInt();
        }
        int[] array2 = array.clone();

        for (int i = 0; i < array2.length; i++) {
            int min = array2[i];
            int min_i = i;
            for (int j = i+1; j < array2.length; j++) {
                if (array2[j] < min) {
                    min = array2[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = array2[i];
                array2[i] = array2[min_i];
                array2[min_i] = tmp;
            }
        }

        bubbleSort.sort(array);
        assertArrayEquals(array2, array);
    }
}
