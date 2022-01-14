package p2.线性结构;

// 后缀表达式计算器    逆波兰表达式
public class SuffixCalculator {
    public static void main(String[] args) {
        String infixExpression = "(10+20/2*3)/2+8";
        String suffixExpression = InfixToSuffix.infixToSuffix(infixExpression);
        int result = evaluateSuffix(suffixExpression);
        System.out.println(result);
    }

    private static int evaluateSuffix(String expresssion) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        String[] tokens = expresssion.split(" ");
        for (String token : tokens) {
            if (token.length() == 0) {
                continue;
            }
            if (isNumber(token)) {
                stack.push(new Integer(token));
            } else {
                processAnOperator(stack,token);
            }
        }
        return stack.pop();
    }

    private static void processAnOperator(ArrayStack<Integer> stack, String token) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        if (token.equals("+")) {
            stack.push(num2 + num1);
        } else if (token.equals("-")) {
            stack.push(num2 - num1);

        } else if (token.equals("*")) {
            stack.push(num2 * num1);

        } else {
            stack.push(num2 / num1);

        }
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+");
    }


}
