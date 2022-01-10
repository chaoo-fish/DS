package ph.作业;

import p2.线性结构.ArrayStack;

public class InfixCalculator {
    /*
    作业 中缀表达式
     */

    public static void main(String[] args) {
        // 首先给定表达式  (3+1)*5-6/2  = 17
        String expression = "(3+1)*5-6/2 + 3"; // 20

        int result = sovleMath(expression);
        System.out.println(result);
    }

    private static int sovleMath(String expression) {
        ArrayStack<Character> operatorStack = new ArrayStack<>();
        ArrayStack<Integer> numStack = new ArrayStack<>();

        // 表达式格式化 -> split()
        expression = insertBlanks(expression);
        // [ , (, 3, +, 1, ), , *, 5, -, 6, /, 2]
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.length() == 0) {
                continue;

                /*
                1.  +  -
                2.  *  /
                3.  (
                4.  )
                5.  num
                 */

                // 1. + -
            } else if (token.equals("+") || token.equals("-")) {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-' || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    solvePartMaths(operatorStack, numStack);
                }
                // 栈为空  或  前一个为 ( )
                operatorStack.push(token.charAt(0));

                // 2. *  /
            } else if (token.equals("*") || token.equals("/")) {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    solvePartMaths(operatorStack, numStack);
                }
                // 栈为空  或  前一个为 ( )
                operatorStack.push(token.charAt(0));

                // 3. (
            } else if (token.equals("(")) {
                operatorStack.push(token.charAt(0));

                // 4. )
            } else if (token.equals(")")) {
                while (operatorStack.peek() != '(') {
                    solvePartMaths(operatorStack, numStack);
                }
                // 弹出 (
                operatorStack.pop();

                // 5. num
            } else {
                numStack.push(new Integer(token));
            }
        }
        while (!operatorStack.isEmpty()) {
            solvePartMaths(operatorStack, numStack);
        }

        return numStack.pop();
    }

    private static void solvePartMaths(ArrayStack<Character> operatorStack, ArrayStack<Integer> numStack) {
        Character c = operatorStack.pop();
        Integer num1 = numStack.pop();
        Integer num2 = numStack.pop();
        // num2 c num1
        if (c == '+') {
            numStack.push(num2 + num1);
        } else if (c == '-') {
            numStack.push(num2 - num1);
        } else if (c == '*') {
            numStack.push(num2 * num1);
        } else {
            numStack.push(num2 / num1);
        }
    }

    private static String insertBlanks(String expression) {
        // StringBuilder 节省空间
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
                sb.append(" ");
                sb.append(c);
                sb.append(" ");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
