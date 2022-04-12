package newcoder.huawei;

import java.util.Scanner;

public class Problem26 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length - i; j++) {
                if (!isAlpha(chars[j])) {
                    continue;
                }
                for (int k = j + 1; k < chars.length - i; k++) {
                    if (!isAlpha(chars[k])) {
                        continue;
                    }
                    if (!compare(chars[j], chars[k])) {
                        swap(chars, j, k);
                    }
                    j = k - 1;
                    break;
                }
            }
        }
        System.out.println(new String(chars));
    }

    public static void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static boolean compare(char a, char b) {
        a = Character.toLowerCase(a);
        b = Character.toLowerCase(b);
        assert a >= 'a' && a <= 'z';
        assert b >= 'a' && b <= 'z';
        return a <= b;
    }

    public static boolean isAlpha(char a) {
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
    }
}
