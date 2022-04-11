package newcoder.huawei;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem88 {
    public static final List<String> order = Arrays.asList("3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "joker", "JOKER");

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] shoupais = line.split("-");
        String[] shoupai1 = shoupais[0].split(" ");
        CardType type1 = judgeType(shoupai1);
        String[] shoupai2 = shoupais[1].split(" ");
        CardType type2 = judgeType(shoupai2);
        if (type1 == CardType.DUIWANG) {
            printShoupai(shoupai1);
            return;
        }
        if (type1 == CardType.ZHADAN) {
            if (type2 == CardType.DUIWANG) {
                printShoupai(shoupai2);
            } else if (type2 == CardType.ZHADAN) {
                compareSame(shoupai1, shoupai2);
            } else {
                printShoupai(shoupai1);
            }
            return;
        }
        if (type2 == CardType.DUIWANG || type2 == CardType.ZHADAN) {
            printShoupai(shoupai2);
            return;
        }
        if (type1 != type2) {
            System.out.println("ERROR");
            return;
        }
        if (type1 == CardType.SHUNZI) {
            compareShunzi(shoupai1, shoupai2);
        } else {
            compareSame(shoupai1, shoupai2);
        }
    }

    public static void compareSame(String[] shoupai1, String[] shoupai2) {
        if (comparePai(shoupai1[0], shoupai2[0])) {
            printShoupai(shoupai1);
        } else {
            printShoupai(shoupai2);
        }
    }

    public static void compareShunzi(String[] shoupai1, String[] shoupai2) {
        if (shoupai1.length != shoupai2.length) {
            System.out.println("ERROR");
        }
        if (comparePai(shoupai1[0], shoupai2[0])) {
            printShoupai(shoupai1);
        } else {
            printShoupai(shoupai2);
        }
    }

    public static boolean comparePai(String a, String b) {
        return order.indexOf(a) > order.indexOf(b);
    }

    public static CardType judgeType(String[] cards) {
        if (cards.length == 2 && "joker".equals(cards[0]) && "JOKER".equals(cards[1])) {
            return CardType.DUIWANG;
        }
        if (cards.length == 1) {
            return CardType.GEZI;
        }
        if (cards.length == 2) {
            return CardType.DUIZI;
        }
        if (cards.length == 3) {
            return CardType.SANGE;
        }
        if (cards.length == 4) {
            return CardType.ZHADAN;
        }
        return CardType.SHUNZI;
    }

    public static void printShoupai(String[] shoupai) {
        for (String s : shoupai) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public enum CardType {
        GEZI, DUIZI, SHUNZI, SANGE, ZHADAN, DUIWANG
    }
}
