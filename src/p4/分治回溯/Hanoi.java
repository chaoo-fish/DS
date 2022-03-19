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
        // 如果只有一个,begin -> end
        if (n == 1) {
            System.out.println(begin + "->" + end);
        } else {
            // 不论X轴上有几个,都看做2个(最底下那个和最底下到最上面的所有)
            hanoi(n - 1,begin, end, mid); // 这个时候我们就是需要将上面所有移到中间
            System.out.println(begin + "->" + end); // 接着将开始移到结尾
            hanoi(n - 1,mid, begin, end); // 接着再将中间所有移到结尾
        }
    }
}














