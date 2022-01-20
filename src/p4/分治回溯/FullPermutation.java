package p4.分治回溯;

import java.util.HashSet;

//全排列
public class FullPermutation {
    public static void main(String[] args) {
        String s = "ABB";
        char[] arr = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        permutation(set, arr, 0, arr.length - 1);
        System.out.println(set);
    }
    private static void permutation(HashSet<String> set, char[] arr, int from, int to) {
        if (from == to) {
            set.add(String.valueOf(arr));    //[A,B,C] => "ABC"
        } else {
            for (int i = from; i <= to; i++) {
                swap(arr, i, from);
                permutation(set, arr, from + 1, to);
                swap(arr, i, from);
            }
        }
    }
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}