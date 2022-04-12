package newcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class Problem61 {
    public static int n;
    public static int m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();

        int[] prevWays = new int[m + 1];
        Arrays.fill(prevWays, 1);
        for (int i = 2; i <= n; i++) {
            int[] ways = new int[m + 1];
            for (int j = 0; j < Math.min(m + 1, i); j++) {
                ways[j] = prevWays[j];
            }
            for (int j = i; j <= m; j++) {
                ways[j] = prevWays[j] + ways[j - i];
            }
            prevWays = ways;
        }
        System.out.println(prevWays[m]);
    }
}
