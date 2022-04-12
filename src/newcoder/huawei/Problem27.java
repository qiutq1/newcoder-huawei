package newcoder.huawei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Problem27 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            wordList.add(in.next());
        }
        String target = in.next();
        List<String> siblings = new ArrayList<>();
        for (String word : wordList) {
            if (isSibling(word, target)) {
                siblings.add(word);
            }
        }
        siblings.sort(String::compareTo);
        System.out.println(siblings.size());
        int index = in.nextInt();
        if (index <= siblings.size()) {
            System.out.println(siblings.get(index - 1));
        }
    }

    public static boolean isSibling(String word, String target) {
        if (word.length() != target.length() || word.equals(target)) {
            return false;
        }
        List<Character> wordCs = new ArrayList<>();
        for (char c : word.toCharArray()) {
            wordCs.add(c);
        }
        List<Character> targetCs = new ArrayList<>();
        for (char c : target.toCharArray()) {
            targetCs.add(c);
        }
        Iterator<Character> wordIterator = wordCs.iterator();
        while (wordIterator.hasNext()) {
            char wordC = wordIterator.next();
            int findIndex = targetCs.indexOf(wordC);
            if (findIndex >= 0) {
                targetCs.remove(findIndex);
                wordIterator.remove();
            }
        }
        return wordCs.isEmpty() && targetCs.isEmpty();
    }
}
