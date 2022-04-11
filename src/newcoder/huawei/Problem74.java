package newcoder.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem74 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        line = line.trim();
        List<String> strings = new ArrayList<>();
        String s = "";
        boolean isTogether = false;
        for (char c : line.toCharArray()) {
            if (c == ' ' && !isTogether) {
                strings.add(s);
                s = "";
            } else if (c == '"') {
                isTogether = !isTogether;
            } else {
                s += c;
            }
        }
        strings.add(s);
        System.out.println(strings.size());
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
