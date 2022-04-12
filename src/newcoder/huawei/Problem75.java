package newcoder.huawei;

import java.util.Scanner;

public class Problem75 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] s1 = in.next().toCharArray();
        char[] s2 = in.next().toCharArray();
        int n1 = s1.length;
        int n2 = s2.length;
        int[][] lengths = new int[n1 + 1][n2 + 1];
        for (int r = 0; r < n1; r++) {
            lengths[r][0] = 0;
        }
        for (int c = 0; c < n2; c++) {
            lengths[0][c] = 0;
        }
        for (int r = 1; r <= n1; r++) {
            for (int c = 1; c <= n2; c++) {
                if (s1[r - 1] == s2[c - 1]) {
                    lengths[r][c] = Math.max(lengths[r - 1][c - 1] + 1, Math.max(lengths[r - 1][c], lengths[r][c - 1]));
                } else {
                    lengths[r][c] = Math.max(lengths[r - 1][c - 1], Math.max(lengths[r - 1][c], lengths[r][c - 1]));
                }
            }
        }
        System.out.println(lengths[n1][n2]);
    }
}
