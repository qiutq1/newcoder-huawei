package newcoder.huawei;

import java.util.Scanner;

public class Problem63 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int n = in.nextInt();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < Math.min(n, chars.length); i++) {
            if (isCg(chars[i])) {
                count++;
            }
        }
        int maxCount = count;
        int maxBegin = 0;
        for (int i = 1; i < chars.length - n + 1; i++) {
            if (isCg(chars[i - 1])) {
                count--;
            }
            if (isCg(chars[i + n - 1])) {
                count++;
            }
            if (count > maxCount) {
                maxCount = count;
                maxBegin = i;
            }
        }

        for (int i = maxBegin; i < Math.min(maxBegin + n, chars.length); i++) {
            System.out.print(chars[i]);
        }
        System.out.println();
    }

    public static boolean isCg(char c) {
        return c == 'C' || c == 'G';
    }
}
