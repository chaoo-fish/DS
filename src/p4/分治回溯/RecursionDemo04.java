package p4.分治回溯;

public class RecursionDemo04 {
    private static int count = 0;
    public static void main(String[] args) {
        //二分查找 折半查找
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int index = binarySearch(arr,0, arr.length - 1, 13);
        System.out.println(index);
        System.out.println(count);
    }
    //在数组arr中 L~R区间内进行二分搜索查找key的角标
    private static int binarySearch(int[] arr, int L, int R, int key) {
        count++;
        if (L > R) {    //元素key不存在
            return -1;
        }
        int M = (L + R) / 2;
        if (arr[M] == key) {
            return M;
        }
        if (arr[M] < key) {
            return binarySearch(arr,M + 1, R, key);
        } else {
            return binarySearch(arr,L, M - 1, key);
        }
    }
}