package newcoder.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem39 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(isSame(in.next(), in.next(), in.next()));
    }

    public static byte isSame(String mask, String ip1, String ip2) {
        try {
            int[] maskB = toBinaryString(mask);
            int[] ip1B = toBinaryString(ip1);
            int[] ip2B = toBinaryString(ip2);
            int[] ip1Result = new int[4];
            int[] ip2Result = new int[4];
            if (!isValidMask(mask)) {
                return 1;
            }
            for (int i = 0; i < 4; i++) {
                ip1Result[i] = (maskB[i] & ip1B[i]);
                ip2Result[i] = (maskB[i] & ip2B[i]);
            }
            for (int i = 0; i < 4; i++) {
                if (ip1Result[i] != ip2Result[i]) {
                    return 2;
                }
            }
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    private static boolean isValidMask(String mask) {
        String[] parts = mask.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        List<Character> bytes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (char c : addCharInFront(Integer.toBinaryString(Integer.parseInt(parts[i])), '0', 8).toCharArray()) {
                bytes.add(c);
            }
        }
        boolean isOne = true;
        for (Character c : bytes) {
            if (isOne && c == '0') {
                isOne = false;
                continue;
            }
            if (!isOne && c == '1') {
                return false;
            }
        }
        return true;
    }

    private static int[] toBinaryString(String ip) throws Exception {
        int[] result = new int[4];
        String[] ipParts = ip.split("\\.");
        if (ipParts.length != 4) {
            throw new Exception();
        }
        for (int i = 0; i < ipParts.length; i++) {
            result[i] = Integer.parseInt(ipParts[i]);
            if (result[i] < 0 || result[i] >= 256) {
                throw new Exception();
            }
        }
        return result;
    }

    public static String addCharInFront(String string, char addChar, int length) {
        if (string.length() >= length) {
            return string;
        }
        StringBuilder resultBuilder = new StringBuilder(string);
        for (int i = 0; i < length - string.length(); i++) {
            resultBuilder.insert(0, addChar);
        }
        return resultBuilder.toString();
    }
}
