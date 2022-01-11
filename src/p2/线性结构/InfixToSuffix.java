package p2.线性结构;

import java.util.PriorityQueue;

// 中缀表达式转后缀表达式
public class InfixToSuffix {
    public static void main(String[] args) {
        String expression = "(10+20/2*3)/2+8";
        expression = infixToSuffix(expression);
        System.out.println(expression);
    }

    public static String infixToSuffix(String expression) {
        // 操作符的栈
        ArrayStack<String> opStack = new ArrayStack<>();
        // 后缀表达式的线性表
        ArrayList<Object> suffixList = new ArrayList<>();

        //格式化表达式
        expression = insertBlanks(expression);
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            // 过滤空串
            if (token.length() == 0) {
                continue;
            }
            // 判断操作符 + - * /
            if (idOperator(token)) {
                /*
                    什么时候进栈?
                    1.如果栈为空
                    2.如果栈顶是 (
                    3.如果栈顶是操作符,且优先级比当前token小

                    什么时候需要将栈顶操作符出栈
                    1.栈顶操作符的优先级 >= 当前token
                 */
                while (true) {
                    if (opStack.isEmpty() || opStack.peek().equals("(") || priority(opStack.peek()) < priority(token)) {
                        opStack.push(token);
                        break;
                    }
                    suffixList.add(opStack.pop());
                }
            } else if (token.equals("(")) {
                opStack.push(token);
            } else if (token.equals(")")) {
                while (!opStack.peek().equals("(")) {
                    suffixList.add(opStack.pop());
                }
                opStack.pop();
            } else if (isNumber(token)) {
                suffixList.add(token);
            } else {
                throw new IllegalArgumentException("wrong char" + expression);
            }
        }
        while (!opStack.isEmpty()) {
            suffixList.add(opStack.pop());
        }
        // 将数字元素和操作符元素进行拼接
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < suffixList.size(); i++) {
            sb.append(suffixList.get(i));
            sb.append(' ');
        }

        return sb.toString();
    }

    private static int priority(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 0;
        }
        if (token.equals("*") || token.equals("/")) {
            return 1;
        }
        return -1;
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+");
    }

    private static boolean idOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }


    // 对原表达式进行格式化处理 给所有的非数字字符两边添加空格
    private static String insertBlanks(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
                sb.append(' ');
                sb.append(c);
                sb.append(' ');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
