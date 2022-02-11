package p5.排序算法;

import static java.util.Arrays.binarySearch;

public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {-2, -1, 0, 3, 5, 9, 10, 22};
        int key = 3;
        int index = interpolationSearch(arr, 0, arr.length - 1, key);
        System.out.println("index:" + index);
        System.out.println(count);
        count = 0;
        index = binarySearch(arr, 0, arr.length - 1, key);
        System.out.println("index:" + index);
        System.out.println(count);
    }

    private static int binarySearch(int[] arr, int min, int max, int key) {
        count++;
        if (min > max) {
            return -1;
        }
        int mid = (min + max) / 2;
        if (arr[mid] == key) {
            return mid;
        } else if (key < arr[mid]) {
            return binarySearch(arr, min, mid - 1, key);
        } else {
            return binarySearch(arr, mid + 1, max, key);
        }

    }

    private static int count = 0;

    private static int interpolationSearch(int[] arr, int low, int high, int key) {
        count++;
        if (low > high) {
            return -1;
        }
        int mid = low + (int) (1.0 * (key - arr[low]) / (arr[high] - arr[low]) * (high - low));
        if (mid < low || mid > high) {
            return -1;
        }
        if (arr[mid] == key) {
            return mid;
        } else if (key < arr[mid]) {
            return interpolationSearch(arr, low, mid - 1, key);
        } else {
            return interpolationSearch(arr, mid + 1, high, key);
        }
    }
}
