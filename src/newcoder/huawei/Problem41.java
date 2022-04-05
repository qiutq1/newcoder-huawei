package newcoder.huawei;

import java.util.*;

public class Problem41 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ws = new int[n];
        for (int i = 0; i < n; i++) {
            ws[i] = in.nextInt();
        }
        int[] ns = new int[n];
        for (int i = 0; i < n; i++) {
            ns[i] = in.nextInt();
        }
        Set<Integer> prevWs = new HashSet<>();
        prevWs.add(0);
        for (int i = 0; i < n; i++) {
            Set<Integer> weights = new HashSet<>();
            int wi = ws[i];
            int ni = ns[i];
            for (int prevW : prevWs) {
                for (int count = 0; count <= ni; count++) {
                    weights.add(prevW + wi * count);
                }
            }
            prevWs = weights;
        }
        System.out.println(prevWs.size());
    }
}
