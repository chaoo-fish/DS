package p2.线性结构;
// 回文判断
public class JudgingPalindrome {
    public static void main(String[] args) {
        solution01();
        solution01();
        solution02();
    }

    private static boolean solution02() {
        String text = "上海自来水来自海上";
        int i = 0;
        int j = text.length() -1;
        while (true) {
            if (text.charAt(i) == text.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
            if (j <= i) {
                return true;
            }
        }
    }

    private static void solution01() {
        String text = "上海自来水来自海上";
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.length() % 2 == 1 && i == text.length() /2) {
                continue;
            }
            char c = text.charAt(i);
            if (stack.isEmpty()) {
                stack.push(text.charAt(i));
            } else {
                if (c != stack.peek()) {
                    stack.push(text.charAt(i));
                } else {
                    stack.pop();
                }
            }
        }
        System.out.println(stack.isEmpty());
        
    }
}
