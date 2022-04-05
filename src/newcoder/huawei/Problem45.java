package newcoder.huawei;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem45 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(maxBeauty(in.next()));
        }
    }

    public static int maxBeauty(String s) {
        Map<Character, CharCount> charMap = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            CharCount charCount = charMap.getOrDefault(c, new CharCount(c, 0));
            charCount.count++;
            charMap.put(c, charCount);
        }
        List<CharCount> sortedChars = charMap.values().stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < sortedChars.size(); i++) {
            sortedChars.get(i).count = 26 - i;
        }
        Map<Character, Integer> charCountMap = sortedChars.stream().collect(Collectors.toMap(charCount -> charCount.c, charCount -> charCount.count));
        int beauty = 0;
        for (char c : chars) {
            beauty += charCountMap.get(c);
        }
        return beauty;
    }

    public static class CharCount implements Comparable<CharCount> {
        public char c;
        public int count;

        public CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public int compareTo(CharCount o) {
            return o.count - count;
        }
    }
}
