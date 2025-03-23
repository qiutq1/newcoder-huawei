package od2025e;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目
 * 某组织举行会议，来了多个代表团同时到达，接待处只有一辆汽车，可以同时接待多个代表团，为了提高车辆利用率，请帮接待员计算可以坐满车的接待方案，输出方案数量。约束:一个团只能上一辆车，并且代表团人数 (代表团数量小于30，每个代表团人数小于30)小于汽车容量(汽车容量小于100) 需要将车辆坐满
 * 输入描述
 * 第一行 代表团人数，英文逗号隔开，代表团数量小于30，每个代表团人数小于30
 * 第二行 汽车载客量，汽车容量小于100
 * 输出描述
 * 坐满汽车的方案数量，如果无解输出0
 * 用例
 * 用例一：
 * 输入：
 * 5,4,2,3,2,4,9
 * 10
 * 输出：
 * 4
 * 说明 解释 以下几种方式都可以坐满车，所以，优先接待输出为4
 * [2,3,5]
 * [2,4,4]
 * [2,3,5]
 * [2,4,4]
 */
public class Test81 {
    public static void main(String[] args) {
        String input = "5,4,2,3,2,4,9,5,4,2,3,2,4,9,5,4,2,3,2,4,9,5,4,2,3,2,4,9,4,9";
        int volumn = 10;
        List<Integer> groups = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int result = 0;
        for (int i = 0; i < 1 << groups.size(); i++) {
            int peopleSize = 0;
            StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(i));
            for (int j = binaryString.length(); j < groups.size(); j++) {
                binaryString.insert(0, "0");
            }
            char[] charArray = binaryString.toString().toCharArray();
            for (int j = 0; j < groups.size(); j++) {
                char c = charArray[j];
                if (c == '1') {
                    peopleSize += groups.get(j);
                }
            }
            if (peopleSize == volumn) {
                result++;
            }
        }
        System.out.println(result);
    }
}
