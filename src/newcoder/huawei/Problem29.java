package newcoder.huawei;

import java.util.Scanner;

public class Problem29 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(encrypt(in.nextLine()));
        System.out.println(decrypt(in.nextLine()));
    }

    public static String encrypt(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                chars[i] = (char) ((c - 96) % 26 + 97 - 32);
            } else if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) ((c - 64) % 26 + 65 + 32);
            } else {
                chars[i] = (char) ((c - 47) % 10 + 48);
            }
        }
        return new String(chars);
    }

    public static String decrypt(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                chars[i] = (char) ((c - 97 - 1 + 26) % 26 + 97 - 32);
            } else if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) ((c - 65 - 1 + 26) % 26 + 65 + 32);
            } else {
                chars[i] = (char) ((c - 48 + 9) % 10 + 48);
            }
        }
        return new String(chars);
    }
}
