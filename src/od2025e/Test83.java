package od2025e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目
 * 为了庆祝中国共产党成立 100 周年，某公园将举行多场文艺表演，很多演出都是同时进行。一个人只能同时观看一场演出，且不能迟到早退。由于演出分布在不同的演出场地，所以连续观看的演出最少有 15 分钟的时间间隔。小明是一个狂热的文艺迷，想观看尽可能多的演出。现给出演出时间表，请帮小明计算他最多能观看几场演出
 * 输入描述
 * 第一行为一个数 N，表示演出场数
 * 1 ≤ N ≤ 1000 接下来 N 行，每行有被空格分割的整数，第一个整数 T 表示演出的开始时间，第二个整数 L 表示演出的持续时间
 * T 和 L 的单位为分钟 0 ≤ T ≤ 1440 0 < L ≤ 180 
 * 输出描述
 * 输出最多能观看的演出场数
 * 用例
 * 用例一：
 * 输入：
 * 2
 * 720 120
 * 840 120
 * 输出：
 * 1
 * 用例二：
 * 输入：
 * 2
 * 0 60
 * 90 60
 * 输出：
 * 2
 */
public class Test83 {
    public static void main(String[] args) {
        // String input = "720 120\n" + "840 120";
        String input = "0 60\n" + "90 60";
        String[] showInputs = input.split("\n");
        List<Integer[]> shows = new ArrayList<>();
        for (String showInput : showInputs) {
            shows.add(Arrays.stream(showInput.split(" ")).map(Integer::parseInt).toArray(Integer[]::new));
        }
        shows.sort((show1, show2) -> show1[0] + show1[1] - show2[0] - show2[1]);

        int count = 1;
        int lastEndTime = shows.get(0)[0] + shows.get(0)[1];
        for (int i = 1; i < shows.size(); i++) {
            if (shows.get(i)[0] >= lastEndTime + 15) {
                count++;
                lastEndTime = shows.get(i)[0] + shows.get(i)[1];
            }
        }
        System.out.println(count);
    }
}
