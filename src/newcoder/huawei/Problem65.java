package newcoder.huawei;

import java.util.Scanner;

public class Problem65 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        if (s2.length() < s1.length()) {
            String t = s1;
            s1 = s2;
            s2 = t;
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        int p1 = 0;
        int p2 = 0;
        int m1;
        int m2;
        int maxLength = 0;
        int maxBegin = 0;

        while (p1 < cs1.length) {
            m1 = p1;
            while (p2 < cs2.length) {
                m2 = p2;
                int length = 0;
                while (m1 < cs1.length && m2 < cs2.length && cs1[m1] == cs2[m2]) {
                    m1++;
                    m2++;
                    length++;
                }
                if (length > maxLength) {
                    maxLength = length;
                    maxBegin = p1;
                }
                m1 = p1;
                p2++;
            }
            p1++;
            p2 = 0;
        }
        System.out.println(s1.substring(maxBegin, maxBegin + maxLength));
    }
}
