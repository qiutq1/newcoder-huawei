package newcoder.huawei;

import java.util.Scanner;

public class Problem89 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] words = new String[4];
        for (int i = 0; i < 4; i++) {
            words[i] = in.next();
        }
        int[] cards = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                cards[i] = getValue(words[i]);
            } catch (MyE e) {
                System.out.println("ERROR");
                return;
            }
        }
        for (int n1 = 0; n1 < 4; n1++) {
            for (int n2 = 0; n2 < 4; n2++) {
                if (n1 == n2) {
                    continue;
                }
                for (int n3 = 0; n3 < 4; n3++) {
                    if (n1 == n3 || n2 == n3) {
                        continue;
                    }
                    for (int n4 = 0; n4 < 4; n4++) {
                        if (n1 == n4 || n2 == n4 | n3 == n4) {
                            continue;
                        }
                        char[] can = can(cards[n1], cards[n2], cards[n3], cards[n4]);
                        if (can != null) {
                            System.out.println(words[n1] + can[0] + words[n2] + can[1] + words[n3] + can[2] + words[n4]);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("NONE");
    }

    public static char[] can(int... cards) {
        if (cards.length == 2) {
            if (cards[0] + cards[1] == 24) {
                return new char[]{'+'};
            }
            if (cards[0] - cards[1] == 24) {
                return new char[]{'-'};
            }
            if (cards[0] * cards[1] == 24) {
                return new char[]{'*'};
            }
            if (cards[0] / cards[1] == 24) {
                return new char[]{'/'};
            }
            return null;
        }
        if (cards.length == 3) {
            char[] can = can(cards[0] + cards[1], cards[2]);
            if (can != null) {
                return new char[]{'+', can[0]};
            }
            can = can(cards[0] - cards[1], cards[2]);
            if (can != null) {
                return new char[]{'-', can[0]};
            }
            can = can(cards[0] * cards[1], cards[2]);
            if (can != null) {
                return new char[]{'*', can[0]};
            }
            can = can(cards[0] / cards[1], cards[2]);
            if (can != null) {
                return new char[]{'/', can[0]};
            }
            return null;
        }
        if (cards.length == 4) {
            char[] can = can(cards[0] + cards[1], cards[2], cards[3]);
            if (can != null) {
                return new char[]{'+', can[0], can[1]};
            }
            can = can(cards[0] - cards[1], cards[2], cards[3]);
            if (can != null) {
                return new char[]{'-', can[0], can[1]};
            }
            can = can(cards[0] * cards[1], cards[2], cards[3]);
            if (can != null) {
                return new char[]{'*', can[0], can[1]};
            }
            can = can(cards[0] / cards[1], cards[2], cards[3]);
            if (can != null) {
                return new char[]{'/', can[0], can[1]};
            }
            return null;
        }
        throw new RuntimeException("wocao");
    }

    public static int getValue(String card) throws MyE {
        if (card.equals("joker") || card.equals("JOKER")) {
            throw new MyE();
        }
        if (card.length() == 1) {
            char c = card.charAt(0);
            if (c >= '0' && c <= '9') {
                return c - '0';
            }
            switch (c) {
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 1;
                default:
                    throw new MyE();
            }
        }
        if (card.equals("10")) {
            return 10;
        }
        throw new MyE();
    }

    public static class MyE extends Exception {
    }
}
