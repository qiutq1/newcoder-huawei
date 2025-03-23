package od2025e;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目
 * 有N个正整数组成的一个序列。给定整数sum，求长度最长的连续子序列，使他们的和等于sum，返回此子序列的长度，如果没有满足要求的序列，返回-1
 * 输入描述
 * 第一行输入是：N个正整数组成的一个序列
 * 第二行输入是：给定整数sum
 * 输出描述
 * 最长的连续子序列的长度
 * 备注
 * 输入序列仅由数字和英文逗号构成，数字之间采用英文逗号分隔 序列长度：1 <= N <= 200 输入序列不考虑异常情况
 * 用例
 * 用例一：
 * 输入：
 * 1,2,3,4,2
 * 6
 * 输出：
 * 3
 * 用例二：
 * 输入：
 * 1,2,3,4,2
 * 20
 * 输出：
 * -1
 */
public class Test79 {

    public static void main(String[] args) {
        String input = "5,2,3,4,2";
        int target = 5;
        List<Integer> data =
            Arrays.asList(input.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        int left = 0;
        int right = 0;
        int sum = data.get(0);
        int length = -1;
        while (right < data.size()) {
            if (sum == target) {
                length = Math.max(right - left + 1, length);
                sum = sum - data.get(left);
                left++;
            } else if (sum > target) {
                sum -= data.get(left);
                left++;
                if (sum == target) {
                    length = Math.max(right - left + 1, length);
                    continue;
                }
                if (left == right && right < data.size() - 1) {
                    right++;
                    sum += data.get(right);
                }
            } else {
                if (right < data.size() - 1) {
                    right++;
                    sum += data.get(right);
                } else {
                    break;
                }
            }
        }
        System.out.println(length);
    }
}
