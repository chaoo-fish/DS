package p4.分治回溯;

public class RecursionDemo01 {
    public static long  i = 0;
    public static void main(String[] args) {
        show();
    }
    //Exception in thread "main" java.lang.StackOverflowError
    private static void show() {
        i++;
        System.out.println(i);
        show();
    }
}