package od2025e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网络信号经过传递会逐层衰减，且遇到阻隔物无法直接穿透，在此情况下需要计算某个位置的网络信号值。注意:网络信号可以绕过阻隔物。array[m][n] 的二维数组代表网格地图， array[i][j] = 0代表i行j列是空旷位置; array[i][j] = x(x为正整数)代表i行j列是信号源，信号强度是x; array[i][j] = -1代表i行j列是阻隔物。 信号源只有1个，阻隔物可能有0个或多个 网络信号衰减是上下左右相邻的网格衰减1 现要求输出对应位置的网络信号值。
 * 输入描述
 * 输入为三行，
 * 第一行为 m 、n ，代表输入是一个 m × n 的数组。 第二行是一串 m × n 个用空格分隔的整数。每连续 n 个数代表一行，再往后 n 个代表下一行，以此类推。对应的值代表对应的网格是空旷位置，还是信号源，还是阻隔物。 第三行是 i 、 j，代表需要计算array[i][j]的网络信号值。 注意：此处 i 和 j 均从 0 开始，即第一行 i 为 0。
 * 例如
 * 6 5 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0 1 4 代表如下地图在这里插入图片描述需要输出第1行第4列的网络信号值，如下图，值为2。在这里插入图片描述
 * 输出描述
 * 输出对应位置的网络信号值，如果网络信号未覆盖到，也输出0。
 * 一个网格如果可以途径不同的传播衰减路径传达，取较大的值作为其信号值
 * 用例
 * 用例一：
 * 输入：
 * 6 5
 * 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0
 * 1 4
 * 输出：
 * 2
 * 用例二：
 * 输入：
 * 6 5
 * 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 2 1
 * 输出：
 * 0
 */
public class Test89 {
    public static void main(String[] args) {
        String input = "0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0";
        List<Integer> inputData = Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            List<Integer> row = new ArrayList<>();
            map.add(row);
            for (int j = 0; j < 5; j++) {
                row.add(inputData.get(i * 5 + j));
            }
        }

        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < map.size(); i++) {
                List<Integer> row = map.get(i);
                for (int j = 0; j < row.size(); j++) {
                    int thisPower = row.get(j);
                    if (thisPower <= 0) {
                        continue;
                    }
                    if (i > 0 && map.get(i - 1).get(j) >= 0) {
                        int oldValue = map.get(i - 1).get(j);
                        int newValue = Math.max(oldValue, thisPower - 1);
                        map.get(i-1).set(j, newValue);
                        changed |= oldValue != newValue;
                    }
                    if (i < map.size() - 1 && map.get(i+1).get(j) >= 0) {
                        int oldValue = map.get(i + 1).get(j);
                        int newValue = Math.max(oldValue, thisPower - 1);
                        map.get(i+1).set(j, newValue);
                        changed |= oldValue != newValue;
                    }
                    if (j > 0 && map.get(i).get(j - 1) >= 0) {
                        int oldValue = map.get(i).get(j - 1);
                        int newValue = Math.max(oldValue, thisPower - 1);
                        map.get(i).set(j - 1, newValue);
                        changed |= oldValue != newValue;
                    }
                    if (j < row.size() - 1 && map.get(i).get(j + 1) >= 0) {
                        int oldValue = map.get(i).get(j + 1);
                        int newValue = Math.max(oldValue, thisPower - 1);
                        map.get(i).set(j + 1, newValue);
                        changed |= oldValue != newValue;
                    }
                }
            }
        } while (changed);
        System.out.println(map.get(1).get(4));
    }
}
