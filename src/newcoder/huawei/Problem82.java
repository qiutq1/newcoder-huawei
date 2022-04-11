package newcoder.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem82 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> moms;
        while (in.hasNext()) {
            moms = new ArrayList<>();
            String line = in.nextLine();
            String[] split = line.split("/");
            print((double) Integer.parseInt(split[0]) / Integer.parseInt(split[1]), moms);
            for (int i = 0; i < moms.size() - 1; i++) {
                System.out.print("1/" + moms.get(i) + "+");
            }
            System.out.println("1/" + moms.get(moms.size() - 1));
        }
    }

    public static void print(double v, List<Integer> moms) {
        double mom = 2;
        while (v < 1 / mom - 1e-8) {
            mom++;
        }
        moms.add((int) mom);
        if (!isEqual(v, 1 / mom)) {
            print(v - 1 / mom, moms);
        }
    }

    public static boolean isEqual(double v1, double v2) {
        return v1 - v2 > -1e-5 && v1 - v2 < 1e-5;
    }
}
