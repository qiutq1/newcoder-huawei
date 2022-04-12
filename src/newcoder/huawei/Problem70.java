package newcoder.huawei;

import java.util.Scanner;
import java.util.Stack;

public class Problem70 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrices = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrices[i][0] = in.nextInt();
            matrices[i][1] = in.nextInt();
        }
        String orderString = in.next();
        char[] orderChars = orderString.toCharArray();
        int count = 0;
        Stack<int[]> orderIndexStack = new Stack<>();
        for (char orderChar : orderChars) {
            if (orderChar == ')') {
                int[] matrix2 = orderIndexStack.pop();
                int[] matrix1 = orderIndexStack.pop();
                orderIndexStack.push(new int[]{matrix1[0], matrix2[1]});
                count += matrix1[0] * matrix1[1] * matrix2[1];
            } else if (orderChar >= 'A' && orderChar <= 'Z') {
                orderIndexStack.push(matrices[orderChar - 'A']);
            }
        }
        System.out.println(count);
    }
}
