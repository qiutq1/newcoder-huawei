package newcoder.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Problem50 {
    // TODO 经典的用栈解决四则运算问题
    public static final Map<Character, Integer> priority = new HashMap<>();

    static {
        priority.put('(', 4);
        priority.put('*', 3);
        priority.put('/', 3);
        priority.put('+', 2);
        priority.put('-', 2);
        priority.put(')', 1);
        priority.put('!', 0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String exp = in.next();
        exp = exp.replace('[', '(');
        exp = exp.replace(']', ')');
        exp = exp.replace('{', '(');
        exp = exp.replace('}', ')');
        Stack<Integer> nums = new Stack<>();
        Stack<Character> operands = new Stack<>();
        operands.push('!');
        String numStr = "";
        char prev = '!';
        for (char c : exp.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numStr += c;
                prev = c;
                continue;
            }
            if ((prev == '(' || prev == '!') && c == '-') {
                numStr = '-' + numStr;
                continue;
            }
            prev = c;
            if (!numStr.isEmpty()) {
                nums.push(Integer.parseInt(numStr));
            }
            numStr = "";

            char peek = operands.peek();
            if (peek == '(') {
                operands.push(c);
                continue;
            }
            if (priority.get(peek) < priority.get(c)) {
                operands.push(c);
            } else {
                if (c != ')') {
                    while (priority.get(peek) >= priority.get(c) && peek != '(') {
                        peek = operands.pop();
                        int num2 = nums.pop();
                        int num1 = nums.pop();
                        nums.push(calc(num1, num2, peek));
                        peek = operands.peek();
                    }
                    operands.push(c);
                } else {
                    while (priority.get(peek) >= priority.get(c) && peek != '(') {
                        peek = operands.pop();
                        int num2 = nums.pop();
                        int num1 = nums.pop();
                        nums.push(calc(num1, num2, peek));
                        peek = operands.peek();
                    }
                    operands.pop();
                }
            }
        }
        if (!numStr.isEmpty()) {
            nums.push(Integer.parseInt(numStr));
        }
        char peek = operands.peek();
        while (peek != '!') {
            peek = operands.pop();
            int num2 = nums.pop();
            int num1 = nums.pop();
            nums.push(calc(num1, num2, peek));
            peek = operands.peek();
        }
        System.out.println(nums.pop());
    }

    public static int calc(int num1, int num2, char operand) {
        switch (operand) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new RuntimeException();
        }
    }
}
