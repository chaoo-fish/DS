package p2.线性结构;
// 十进制转十六进制
public class DecToHex {
    public static void main(String[] args) {
        int num = 654321;
        ArrayStack<String> stack = new ArrayStack<>();
        while (num != 0) {
            int a = num % 16;
            if (a < 10) {
                stack.push(a + "");
            } else {
                // 10-A 11-B 12-C 13-D 14-E 15-F
                //   65   66   67   68   69   70
                stack.push((char)(a + 55) + "");
            }
            num /= 16;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }
}
