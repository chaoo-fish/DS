package p4.分治回溯;

public class RecursionDemo03 {
    public static void main(String[] args) {
        int ret = f(50);
        System.out.println(ret);
    }

    private static int f(int x) {
        if (x == 1 || x == 2) {
            return 1;
        }
        return f(x - 1) + f(x - 2);
    }
}