package p2.线性结构;

public class InfixCalculator {
    public static void main(String[] args) {
        String expression = "(10+20/2*3)/2+8";
        try {
            int result = evaluateExpression(expression);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Wrong expression :" + expression);
        }
    }

    private static int evaluateExpression(String expression) {
        //需要两个辅助栈
        ArrayStack<Character> operatorStack = new ArrayStack<>();
        ArrayStack<Integer> numberStack = new ArrayStack<>();

        //格式化表达式
        expression = insertBlanks(expression);
        String[] tokens = expression.split(" ");
        for (String token : tokens) {   //token == tokens[i]
            //过滤空串
            if (token.length() == 0) {
                continue;
                //遍历到 + - 号
            } else if (token.equals("+") || token.equals("-")) {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-' || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    //如果之前是别的+ - * / 则需要弹栈 并计算
                    processAnOperator(numberStack, operatorStack);
                }
                //如果操作符栈为空 或者 不为空但栈顶为(
                operatorStack.push(token.charAt(0));

                //遍历到 * / 号
            } else if (token.equals("*") || token.equals("/")) {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    //如果之前是别的* / 则需要弹栈 并计算
                    processAnOperator(numberStack, operatorStack);
                }
                //如果操作符栈为空 或者 不为空但栈顶为(
                operatorStack.push(token.charAt(0));

                //遍历到 (
            } else if (token.equals("(")) {
                operatorStack.push(token.charAt(0));

                //遍历到 )
            } else if (token.equals(")")) {
                //只要操作符栈的栈顶不是左括号( 就挨个弹栈计算即可
                while (operatorStack.peek() != '(') {
                    processAnOperator(numberStack, operatorStack);
                }
                //最后 清掉左括号
                operatorStack.pop();

                //遍历到数字
            } else {
                numberStack.push(new Integer(token));
            }
        }

        //处理最后面的操作符
        while (!operatorStack.isEmpty()) {
            processAnOperator(numberStack, operatorStack);
        }
        return numberStack.pop();
    }

    //操作符栈弹栈一个元素 数字栈弹栈两个数字 进行计算 并将新的结果进栈到数字栈
    private static void processAnOperator(ArrayStack<Integer> numberStack, ArrayStack<Character> operatorStack) {
        char op = operatorStack.pop();
        int num1 = numberStack.pop();
        int num2 = numberStack.pop();
        //num2 op num1
        if (op == '+') {
            numberStack.push(num2 + num1);
        } else if (op == '-') {
            numberStack.push(num2 - num1);
        } else if (op == '*') {
            numberStack.push(num2 * num1);
        } else {
            numberStack.push(num2 / num1);
        }
    }

    //对原表达式进行格式化处理 给所有的非数字字符两边添加空格
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
