package od2025e;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目：
 * 给定一组数字，表示扑克牌的牌面数字，忽略扑克牌的花色，请按如下规则对这一组扑克牌进行整理：
 * 步骤1. 对扑克牌进行分组，形成组合牌，规则如下：当牌面数字相同张数大于等于4时，组合牌为“炸弹”； 3张相同牌面数字 + 2张相同牌面数字，且3张牌与2张牌不相同时，组合牌为“葫芦”； 3张相同牌面数字，组合牌为“三张”； 2张相同牌面数字，组合牌为“对子”； 剩余没有相同的牌，则为“单张”；
 * 步骤2. 对上述组合牌进行由大到小排列，规则如下：不同类型组合牌之间由大到小排列规则：“炸弹” > “葫芦” > “三张” > “对子” > “单张”； 相同类型组合牌之间，除“葫芦”外，按组合牌全部牌面数字加总由大到小排列； “葫芦”则先按3张相同牌面数字加总由大到小排列，3张相同牌面数字加总相同时，再按另外2张牌面数字加总由大到小排列； 由于“葫芦”>“三张”，因此如果能形成更大的组合牌，也可以将“三张”拆分为2张和1张，其中的2张可以和其它“三张”重新组合成“葫芦”，剩下的1张为“单张”
 * 步骤3. 当存在多个可能组合方案时，按如下规则排序取最大的一个组合方案：依次对组合方案中的组合牌进行大小比较，规则同上； 当组合方案A中的第n个组合牌大于组合方案B中的第n个组合牌时，组合方案A大于组合方案B；
 * 输入描述：
 * 第一行为空格分隔的N个正整数，每个整数取值范围[1,13]，N的取值范围[1,1000]
 * 输出描述：
 * 经重新排列后的扑克牌数字列表，每个数字以空格分隔
 * <p>
 * 用例一：
 * 输入：
 * 1 3 3 3 2 1 5
 * 输出：
 * 3 3 3 1 1 5 2
 * 用例二：
 * 输入：
 * 4 4 2 1 2 1 3 3 3 4
 * 输出：
 * 4 4 4 3 3 2 2 1 1 3
 */
public class Test37 {
    public static void main(String[] args) {
//        String input = "1 3 3 3 2 1 5 3 3 3 3 3";
//        String input = "1 3 3 3 2 1 5";
        String input = "4 4 2 1 2 1 3 3 3 4";
        Map<Integer, Long> valueCounts = Arrays.stream(input.split(" ")).map(Integer::parseInt)
            .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        List<Object> result = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : valueCounts.entrySet()) {
            if (entry.getValue() >= 4) {
                for (long i = 0; i < entry.getValue() / 4; i++) {
                    result.add(new Zhadan(entry.getKey()));
                }
                entry.setValue(entry.getValue() % 4);
            }
        }
        result.sort(Comparator.comparing(o -> ((Zhadan) o)));
        ArrayList<Integer> sanzhangs = new ArrayList<>();
        ArrayList<Integer> duizis = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : valueCounts.entrySet()) {
            if (entry.getValue() == 3) {
                sanzhangs.add(entry.getKey());
            } else if (entry.getValue() == 2) {
                duizis.add(entry.getKey());
            }
        }
        sanzhangs.sort(Comparator.comparing(Integer::intValue).reversed());
        duizis.sort(Comparator.comparing(Integer::intValue).reversed());
        int i = 0;
        while (i < Math.min(sanzhangs.size(), duizis.size())) {
            result.add(new Hulu(sanzhangs.get(i), duizis.get(i)));
            valueCounts.remove(sanzhangs.get(i));
            valueCounts.remove(duizis.get(i));
            i++;
        }

        if (i < sanzhangs.size()) {
            while (i < sanzhangs.size() - 1) {
                result.add(new Hulu(sanzhangs.get(i), sanzhangs.get(i + 1)));
                valueCounts.remove(sanzhangs.get(i));
                valueCounts.put(sanzhangs.get(i + 1), 1L);
                i += 2;
            }
        } else if (i < duizis.size()) {
            duizis.stream().map(Duizi::new).forEach(duizi -> {
                result.add(duizi);
                valueCounts.remove(duizi.value);
            });
        }

        valueCounts.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getKey)
            .sorted(Comparator.comparingInt(Integer::intValue).reversed()).map(Danzhang::new).forEach(result::add);
        StringBuilder output = new StringBuilder();
        for (Object o : result) {
            output.append(o.toString());
            output.append(" ");
        }
        System.out.println(output.substring(0, output.length() - 1));
    }

    private static class Zhadan implements Comparable<Zhadan> {
        private int value;

        public Zhadan(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Zhadan o) {
            return value - o.value;
        }

        @Override
        public String toString() {
            return value + " " + value + " " + value + " " + value;
        }
    }

    private static class Hulu implements Comparable<Hulu> {
        private int value1;
        private int value2;

        public Hulu(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        @Override
        public int compareTo(Hulu o) {
            if (value1 - o.value1 != 0) {
                return value1 - o.value1;
            }
            return value2 - o.value2;
        }

        @Override
        public String toString() {
            return value1 + " " + value1 + " " + value1 + " " + value2 + " " + value2;
        }
    }

    private static class Sanzhang implements Comparable<Sanzhang> {
        private int value;

        public Sanzhang(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Sanzhang o) {
            return value - o.value;
        }

        @Override
        public String toString() {
            return value + " " + value + " " + value;
        }
    }

    private static class Duizi implements Comparable<Duizi> {
        private int value;

        public Duizi(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Duizi o) {
            return value - o.value;
        }

        @Override
        public String toString() {
            return value + " " + value;
        }
    }

    private static class Danzhang implements Comparable<Danzhang> {
        private int value;

        public Danzhang(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Danzhang o) {
            return value - o.value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
