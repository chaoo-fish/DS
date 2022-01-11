package p2.线性结构;

import java.util.HashMap;
//括号匹配
public class MatchBracket {
    public static void main(String[] args) {
        solution01();
        solution02();
    }

    private static void solution02() {
//        String str = "{()[[()]]<{()>}()";
        String str = "{()[[()]]<>{}()<>}()";

        HashMap<Character,Character> map = new HashMap<>();
        map.put('[',']');
        map.put('<','>');
        map.put('(',')');
        map.put('{','}');
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i); // 遍历每一个括号
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char top = stack.peek();
                // 查看当前栈顶的数据是不是存在map中的键(左类型括号) 同时 满足 当前的 c 是当前栈顶括号在map中的值
                 if (map.containsKey(top) && c == map.get(top)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        System.out.println(stack.isEmpty());
    }

    private static void solution01() {
        String str = "{()[[()]]<>{}()<>}()";
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char top = stack.peek();
                // {},[],()  左边的括号减去右边的括号  有的等于  -1  有的等于  -2
                if (top - c == -1 || top - c == -2) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        System.out.println(stack.isEmpty());
    }
}