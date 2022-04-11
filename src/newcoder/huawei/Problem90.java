package newcoder.huawei;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Problem90 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputLine = in.nextLine();
        if (!Pattern.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}", inputLine)) {
            System.out.println("NO");
            return;
        }
        int count = 0;
        for (char c : inputLine.toCharArray()) {
            if (c == '.') {
                count++;
            }
        }
        if (count != 3) {
            System.out.println("NO");
            return;
        }
        String[] split = inputLine.split("\\.");
        if (split.length != 4) {
            System.out.println("NO");
            return;
        }
        for (int i = 0; i < 4; i++) {
            try {
                String part = split[i];
                if (part.indexOf('0') == 0 && part.length() > 1) {
                    System.out.println("NO");
                    return;
                }
                int parseInt = Integer.parseInt(part);
                if (parseInt < 0 || parseInt > 255) {
                    System.out.println("NO");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
