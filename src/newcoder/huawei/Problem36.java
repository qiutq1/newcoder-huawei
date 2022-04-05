package newcoder.huawei;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem36 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        encrypt(in.next(), in.next());
    }

    public static void encrypt(String key, String s) {
        ArrayList<Character> natureAlphabet = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            natureAlphabet.add((char) i);
        }
        ArrayList<Character> characters = new ArrayList<>();
        for (char c : key.toCharArray()) {
            if (!characters.contains(c)) {
                characters.add(c);
                natureAlphabet.remove(Character.valueOf(c));
            }
        }
        characters.addAll(natureAlphabet);

        for (char c : s.toLowerCase().toCharArray()) {
            char newChar = c >= 'a' && c <= 'z' ? characters.get(c - 'a') : c;
            System.out.print(newChar);
        }
        System.out.println();
    }
}
