package newcoder.huawei;

import java.util.Scanner;

public class Problem52 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n1 = s1.length();
        int n2 = s2.length();
        int[] prevDistances = new int[n2 + 1];
        for (int j = 0; j <= n2; j++) {
            prevDistances[j] = j;
        }
        for (int i = 1; i <= n1; i++) {
            int[] distances = new int[n2 + 1];
            distances[0] = i;
            for (int j = 1; j <= n2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    distances[j] = Math.min(prevDistances[j - 1], Math.min(prevDistances[j], distances[j - 1]) + 1);
                } else {
                    distances[j] = Math.min(prevDistances[j - 1] + 1, Math.min(prevDistances[j], distances[j - 1]) + 1);
                }
            }
            prevDistances = distances;
        }
        System.out.println(prevDistances[n2]);
    }
}
