package ph.作业;

import java.util.Arrays;

public class AAAAA {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 1, 2};
//        maopao(arrs);
//        charu(arr);
        xuanze(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void xuanze(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    swap(i,j,arr);
                }
            }

        }
    }

    private static void charu(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int e = arr[i];
            /*int j = 0;
            for (j = i; j > 0 && arr[j - 1] > e; j--) {
                arr[j] = arr[j - 1];
//                swap(j, j - 1, arr);
            }
            arr[j] = e;*/

            int j = i;
            while (j > 0 && arr[j - 1] > e) {
                swap(j, j - 1, arr);
                j--;
            }
        }
    }

    public static void maopao(int[] arr) {
        // 大数上浮
        for (int i = 0; i < arr.length - 1; i++) { // 少做一步
            for (int j = 0; j < arr.length - i - 1; j++) { // 因为是 j + 1
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                }
            }
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
