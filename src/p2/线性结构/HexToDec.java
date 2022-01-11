package p2.线性结构;

// 十六进制转十进制
public class HexToDec {
    public static void main(String[] args) {
        String hex = "9FBF1";
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < hex.length(); i++) {
            stack.push(hex.charAt(i));
        }
        int sum = 0;
        int mi = 0;
        while (!stack.isEmpty()) {
            // 9 F B F 1
            char c = stack.pop();
            sum += getNumber(c) * Math.pow(16, mi);
            mi++;
        }
        System.out.println(sum);
    }

    private static int getNumber(char c) {
        if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'f')) {
            throw new IllegalArgumentException("wrong char");
        }
        if (c >= '0' && c <= '9') {
            return c - '0'; // ascal码值的差值
        } else {
            return c - 'A' + 10;
        }
    }
}
