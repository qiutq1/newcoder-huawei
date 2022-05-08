package newcoder.huawei;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem18 {
    private static final Pattern IP_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
    private static final List<Integer> VALID_MASK_INTS = Arrays.asList(0, 128, 192, 224, 240, 248, 252, 254, 255);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int invalidCount = 0;
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int dCount = 0;
        int eCount = 0;
        int privateCount = 0;
        while (in.hasNextLine()) {
            String inputLine = in.nextLine();
            String[] split = inputLine.split("~");
            String ipInput = split[0];
            String maskInput = split[1];
            char type = ipType(ipInput);
            if (type == 'F') {
                // 非法的ip
                invalidCount++;
                continue;
            }
            if (type == 'G') {
                // 不属于A-E任何一类，也不是非法，如0.*.*.*，127.*.*.*
                continue;
            }
            if (!isMaskValid(maskInput)) {
                invalidCount++;
                continue;
            }
            switch (type) {
                case 'A': {
                    aCount++;
                    break;
                }
                case 'B': {
                    bCount++;
                    break;
                }
                case 'C': {
                    cCount++;
                    break;
                }
                case 'D': {
                    dCount++;
                    break;
                }
                case 'E': {
                    eCount++;
                    break;
                }
            }
            if (isPrivate(ipInput, type)) {
                privateCount++;
            }
        }
        System.out.println(aCount + " " + bCount + " " + cCount + " " + dCount + " " + eCount + " " + invalidCount + " " + privateCount);
    }

    private static boolean isIpValid(String ipInput) {
        Matcher matcher = IP_PATTERN.matcher(ipInput);
        if (!matcher.find()) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            String ipSplit = matcher.group(i + 1);
            if (!isValidIpSplit(ipSplit)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidIpSplit(String split) {
        char[] chars = split.toCharArray();
        if (chars.length == 1) {
            return chars[0] >= '0' && chars[0] <= '9';
        }
        if (chars[0] < '1' || chars[0] > '9') {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c < '0' || c > '9') {
                return false;
            }
        }
        int value = Integer.parseInt(split);
        return value >= 0 && value <= 255;
    }

    private static boolean isMaskValid(String maskInput) {
        if (!isIpValid(maskInput)) {
            return false;
        }
        if ("0.0.0.0".equals(maskInput) || "255.255.255.255".equals(maskInput)) {
            return false;
        }
        String[] maskSplit = maskInput.split("\\.");
        int[] maskInts = {Integer.parseInt(maskSplit[0]), Integer.parseInt(maskSplit[1]), Integer.parseInt(maskSplit[2]), Integer.parseInt(maskSplit[3])};
        for (int i = 0; i < 4; i++) {
            if (maskInts[i] != 255) {
                // 前面都是255，后面都是0，自身是0, 128, 192, 224, 240, 248, 252, 254, 255
                for (int j = 0; j < i; j++) {
                    if (maskInts[j] != 255) {
                        return false;
                    }
                }
                for (int j = i + 1; j < 4; j++) {
                    if (maskInts[j] != 0) {
                        return false;
                    }
                }
                return VALID_MASK_INTS.contains(maskInts[i]);
            }
        }
        throw new RuntimeException("wocao");
    }

    private static char ipType(String ipInput) {
        if (!isIpValid(ipInput)) {
            return 'F';
        }
        String[] ipSplit = ipInput.split("\\.");
        int firstSplit = Integer.parseInt(ipSplit[0]);
        if (firstSplit == 0 || firstSplit == 127) {
            return 'G';
        }
        if (firstSplit >= 1 && firstSplit <= 126) {
            return 'A';
        }
        if (firstSplit >= 128 && firstSplit <= 191) {
            return 'B';
        }
        if (firstSplit >= 192 && firstSplit <= 223) {
            return 'C';
        }
        if (firstSplit >= 224 && firstSplit <= 239) {
            return 'D';
        }
        if (firstSplit >= 240 && firstSplit <= 255) {
            return 'E';
        }
        return 0;
    }

    private static boolean isPrivate(String ipInput, char type) {
        String[] ipSplit = ipInput.split("\\.");
        int part1 = Integer.parseInt(ipSplit[0]);
        int part2 = Integer.parseInt(ipSplit[1]);
        if (type == 'A') {
            return part1 == 10;
        }
        if (type == 'B') {
            return part1 == 172 && part2 >= 16 && part2 <= 31;
        }
        if (type == 'C') {
            return part1 == 192 && part2 == 168;
        }
        return false;
    }
}
