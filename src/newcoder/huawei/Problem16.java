package newcoder.huawei;

import java.util.*;

public class Problem16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = in.nextInt();
        List<Merchandise> merchandises = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Merchandise merchandise = new Merchandise();
            merchandise.v = in.nextInt();
            merchandise.w = in.nextInt();
            merchandise.vw = merchandise.v * merchandise.w;
            merchandise.parent = in.nextInt();
            merchandises.add(merchandise);
        }
        for (Merchandise merchandise : merchandises) {
            if (merchandise.parent != 0) {
                merchandises.get(merchandise.parent - 1).children.add(merchandise);
            }
        }
        List<MerchandiseEquivalent> merchandiseEquivalents = new ArrayList<>();
        for (Merchandise merchandise : merchandises) {
            if (merchandise.parent == 0) {
                merchandiseEquivalents.add(merchandise);
                merchandiseEquivalents.addAll(merchandise.getEquivalentMerchandises());
            }
        }

        int[] vArray = merchandiseEquivalents.stream().map(merchandise -> merchandise.v).mapToInt(Integer::intValue).toArray();
        int step = getGcd(vArray);
        Map<Integer, Integer> previous = new HashMap<>();
        for (int i = 0; i <= n; i += step) {
            previous.put(i, 0);
        }
        Map<Integer, Integer> current = new HashMap<>();
        for (int i = 1; i <= merchandiseEquivalents.size(); i++) {
            MerchandiseEquivalent merchandise = merchandiseEquivalents.get(i - 1);
            current = new HashMap<>();
            for (int j = 0; j <= n; j += step) {
                if (merchandise.v > j) {
                    current.put(j, previous.get(j));
                } else {
                    current.put(j, Math.max(merchandise.vw + previous.get(j - merchandise.v), previous.get(j)));
                }
            }
            previous = current;
        }
        System.out.println(current.get(n));
    }

    public static int getGcd(int[] nums) {
        if (nums.length == 2) {
            int a = nums[0];
            int b = nums[1];
            while (true) {
                a = a % b;
                if (a == 0) return b;
                b = b % a;
                if (b == 0) return a;
            }
        } else if (nums.length > 2) {
            int gcd = getGcd(new int[]{nums[0], nums[1]});
            int[] newNumbers = new int[nums.length - 1];
            newNumbers[0] = gcd;
            System.arraycopy(nums, 2, newNumbers, 1, nums.length - 2);
            return getGcd(newNumbers);
        } else {
            throw new RuntimeException();
        }
    }

    public static class Merchandise extends MerchandiseEquivalent {
        public final List<Merchandise> children = new ArrayList<>();
        public int w;
        public int parent;

        public Collection<? extends MerchandiseEquivalent> getEquivalentMerchandises() {
            List<MerchandiseEquivalent> equivalentMerchandises = new ArrayList<>();
            if (children.isEmpty()) {
                return equivalentMerchandises;
            }
            if (children.size() == 1) {
                MerchandiseEquivalent equivalent = new MerchandiseEquivalent();
                equivalent.v = v + children.get(0).v;
                equivalent.vw = v * w + children.get(0).v * children.get(0).w;
                equivalentMerchandises.add(equivalent);
            } else {
                MerchandiseEquivalent equivalent1 = new MerchandiseEquivalent();
                equivalent1.v = v + children.get(0).v;
                equivalent1.vw = v * w + children.get(0).v * children.get(0).w;
                MerchandiseEquivalent equivalent2 = new MerchandiseEquivalent();
                equivalent2.v = v + children.get(1).v;
                equivalent2.vw = v * w + children.get(1).v * children.get(1).w;
                MerchandiseEquivalent equivalent3 = new MerchandiseEquivalent();
                equivalent3.v = v + children.get(0).v + children.get(1).v;
                equivalent3.vw = v * w + children.get(0).v * children.get(0).w + children.get(1).v * children.get(1).w;
                equivalentMerchandises.add(equivalent1);
                equivalentMerchandises.add(equivalent2);
                equivalentMerchandises.add(equivalent3);
            }
            return equivalentMerchandises;
        }
    }

    public static class MerchandiseEquivalent {
        public int v;
        public int vw;
    }
}
