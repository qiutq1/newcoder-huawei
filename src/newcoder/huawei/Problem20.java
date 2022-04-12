package newcoder.huawei;

import java.util.Scanner;

public class Problem20 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (!checkLength(line)) {
                System.out.println("NG");
                continue;
            }
            if (!checkType(line)) {
                System.out.println("NG");
                continue;
            }
            if (!checkCommon(line)) {
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
    }

    public static boolean checkLength(String line) {
        return line.length() > 8;
    }

    public static boolean checkType(String line) {
        boolean[] contains = new boolean[4];
        for (char c : line.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                contains[0] = true;
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                contains[1] = true;
                continue;
            }
            if (c >= '0' && c <= '9') {
                contains[2] = true;
                continue;
            }
            contains[3] = true;
        }
        int count = 0;
        for (boolean contain : contains) {
            if (contain) {
                count++;
            }
        }
        return count >= 3;
    }

    public static boolean checkCommon(String line) {
        char[] chars = line.toCharArray();
        int commonLength = 0;
        int i = 0;
        while (i < chars.length - 3) {
            int j = i + 3;
            while (j < chars.length) {
                if (chars[i] == chars[j]) {
                    i++;
                    j++;
                    commonLength++;
                } else {
                    if (commonLength >= 3) {
                        return false;
                    }
                    i -= commonLength;
                    j -= commonLength;
                    j++;
                    commonLength = 0;
                }
            }
            i++;
        }
        return true;
    }
}
