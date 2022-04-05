package newcoder.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem30 {
    public static Map<Character, Character> changeMap = new HashMap<>();

    public static void main(String[] args) {
        changeMap.put('0', '0');
        changeMap.put('1', '8');
        changeMap.put('2', '4');
        changeMap.put('3', 'C');
        changeMap.put('4', '2');
        changeMap.put('5', 'A');
        changeMap.put('6', '6');
        changeMap.put('7', 'E');
        changeMap.put('8', '1');
        changeMap.put('9', '9');
        changeMap.put('A', '5');
        changeMap.put('B', 'D');
        changeMap.put('C', '3');
        changeMap.put('D', 'B');
        changeMap.put('E', '7');
        changeMap.put('F', 'F');
        Scanner in = new Scanner(System.in);
        String str1 = in.next();
        String str2 = in.next();
        // step1
        String merge = str1 + str2;
        // step2
        char[] chars = merge.toCharArray();
        boolean isEven = chars.length % 2 == 0;
        for (int i = 0; i < chars.length; i += 2) {
            for (int j = 0; j < (isEven ? chars.length - i - 3 : chars.length - i - 2); j += 2) {
                if (chars[j] > chars[j + 2]) {
                    swap(chars, j, j + 2);
                }
            }
        }
        for (int i = 0; i < chars.length; i += 2) {
            for (int j = 1; j < (isEven ? chars.length - i - 2 : chars.length - i - 3); j += 2) {
                if (chars[j] > chars[j + 2]) {
                    swap(chars, j, j + 2);
                }
            }
        }
        // step3
        for (int i = 0; i < chars.length; i++) {
            if (isHex(chars[i])) {
                if (Character.isLowerCase(chars[i])) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                chars[i] = changeMap.get(chars[i]);
            }
        }
        System.out.println(new String(chars));
    }

    public static void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static boolean isHex(char c) {
        return (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F') || (c >= '0' && c <= '9');
    }
}
