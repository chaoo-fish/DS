package p4.分治回溯;

// 汉诺塔
//已知既定规律的问题
//未知既定规律的问题
public class Hanoi {
    public static void main(String[] args) {
        String x = "X";
        String y = "Y";
        String z = "Z";
        hanoi(3,x,y,z);
    }
    private static void hanoi(int n, String begin, String mid, String end) {
        if (n == 1) {
            System.out.println(begin + "->" + end);
        } else {
            hanoi(n - 1,begin, end, mid);
            System.out.println(begin + "->" + end);
            hanoi(n - 1,mid, begin, end);
        }
    }
}














