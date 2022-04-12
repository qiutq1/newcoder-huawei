package newcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class Problem24 {
    public static int n;
    public static int[] hs;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        hs = new int[n];
        for (int i = 0; i < n; i++) {
            hs[i] = in.nextInt();
        }
        int[] maxIncr = maxIncr();
        int[] maxDecr = maxDecr();
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            maxCount = Math.max(maxCount, maxIncr[i] + maxDecr[i] - 1);
        }
        System.out.println(n - maxCount);
    }

    public static int[] maxIncr() {
        int[] maxIncrs = new int[n];
        Arrays.fill(maxIncrs, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (hs[i] > hs[j]) {
                    maxIncrs[i] = Math.max(maxIncrs[i], maxIncrs[j] + 1);
                }
            }
        }
        return maxIncrs;
    }

    public static int[] maxDecr() {
        int[] maxDecrs = new int[n];
        Arrays.fill(maxDecrs, 1);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (hs[i] > hs[j]) {
                    maxDecrs[i] = Math.max(maxDecrs[i], maxDecrs[j] + 1);
                }
            }
        }
        return maxDecrs;
    }
}
