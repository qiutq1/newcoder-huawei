package newcoder.huawei;

import java.util.Scanner;

public class Problem69 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        int[][] matrix1 = new int[x][y];
        int[][] matrix2 = new int[y][z];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix1[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < z; j++) {
                matrix2[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                int result = 0;
                for (int k = 0; k < y; k++) {
                    result += matrix1[i][k] * matrix2[k][j];
                }
                System.out.print(result + " ");
            }
            System.out.println();
        }
    }
}
