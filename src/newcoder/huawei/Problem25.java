package newcoder.huawei;

import java.util.*;

public class Problem25 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int iCount = in.nextInt();
        String[] is = new String[iCount];
        for (int i = 0; i < iCount; i++) {
            is[i] = String.valueOf(in.nextInt());
        }
        int rCount = in.nextInt();
        int[] rs = new int[rCount];
        for (int i = 0; i < rCount; i++) {
            rs[i] = in.nextInt();
        }

        String[] rsInString = Arrays.stream(rs).distinct().sorted().mapToObj(String::valueOf).toArray(String[]::new);
        List<RiMatchIndexPair> riIisMap = new ArrayList<>();
        for (String ri : rsInString) {
            List<Integer> matchIsIndexes = new ArrayList<>();
            for (int index = 0; index < is.length; index++) {
                if (is[index].contains(ri)) {
                    matchIsIndexes.add(index);
                }
            }
            if (!matchIsIndexes.isEmpty()) {
                riIisMap.add(new RiMatchIndexPair(ri, matchIsIndexes));
            }
        }
        riIisMap.sort(Comparator.comparingInt(pair -> Integer.parseInt(pair.ri)));
        int count = 0;
        for (RiMatchIndexPair pair : riIisMap) {
            count += 2 + 2 * pair.matchIsIndexes.size();
        }
        System.out.print(count + " ");
        riIisMap.forEach(pair -> {
            System.out.print(pair.ri + " ");
            System.out.print(pair.matchIsIndexes.size() + " ");
            for (int matchIsIndex : pair.matchIsIndexes) {
                System.out.print(matchIsIndex + " " + is[matchIsIndex] + " ");
            }
        });
        System.out.println();
    }

    public static class RiMatchIndexPair {
        public String ri;
        public List<Integer> matchIsIndexes;

        public RiMatchIndexPair(String ri, List<Integer> matchIsIndexes) {
            this.ri = ri;
            this.matchIsIndexes = matchIsIndexes;
        }
    }
}
