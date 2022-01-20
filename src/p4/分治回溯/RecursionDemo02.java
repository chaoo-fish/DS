package p4.分治回溯;

public class RecursionDemo02 {
    public static void main(String[] args) {
        int ret = f(100);
        System.out.println(ret);
    }

    private static int f(int n) {
        if (n == 1) {
            return 1;
        }
        return f(n - 1) + n;
    }
}