package newcoder.huawei;

import java.util.Scanner;

public class Problem67 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] inputs = {in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()};
        for (int n1 = 0; n1 < 4; n1++) {
            for (int n2 = 0; n2 < 4; n2++) {
                if (n2 == n1) {
                    continue;
                }
                for (int n3 = 0; n3 < 4; n3++) {
                    if (n3 == n1 || n3 == n2) {
                        continue;
                    }
                    for (int n4 = 0; n4 < 4; n4++) {
                        if (n4 == n1 || n4 == n2 || n4 == n3) {
                            continue;
                        }
                        if (can(inputs[n1], inputs[n2], inputs[n3], inputs[n4])) {
                            System.out.println(true);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(false);
    }

    public static boolean can(float... ns) {
        if (ns.length == 2) {
            return is(ns[0] + ns[1]) || is(ns[0] - ns[1]) || is(ns[0] * ns[1]) || is(ns[0] / ns[1]);
        } else if (ns.length == 3) {
            return can(ns[0] + ns[1], ns[2]) || can(ns[0] - ns[1], ns[2]) || can(ns[0] * ns[1], ns[2]) || can(ns[0] / ns[1], ns[2]);
        } else if (ns.length == 4) {
            return can(ns[0] + ns[1], ns[2], ns[3]) || can(ns[0] - ns[1], ns[2], ns[3]) || can(ns[0] * ns[1], ns[2], ns[3]) || can(ns[0] / ns[1], ns[2], ns[3]);
        } else {
            throw new RuntimeException();
        }
    }

    public static boolean is(float n) {
        return n >= 23.999 && n <= 24.001;
    }
}
