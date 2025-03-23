package od2025e;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目
 * 给定参数 n，从 1 到 n 会有 n 个整数：1,2,3,…,n, 这 n 个数字共有 n! 种排列。按大小顺序升序列出所有排列的情况，并一一标记，当 n = 3 时,所有排列如下:“123” “132” “213” “231” “312” “321” 给定 n 和 k，返回第 k 个排列
 * 输入描述
 * 输入两行，第一行为 n，第二行为 k，
 * 给定 n 的范围是[1, 9], 给定 k 的范围是[1, n!]
 * 输出描述
 * 输出排在第 k 位置的数字
 * 用例
 * 用例一：
 * 输入：
 * 3
 * 3
 * 输出：
 * 213
 * 用例二：
 * 输入：
 * 2
 * 2
 * 输出：
 * 21
 */
public class Test80 {
    public static void main(String[] args) {
        int n = 5;
        int k = 120;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        String result = result(list, k - 1);

        System.out.println(result);
    }

    private static String result(List<Integer> list, int k) {
        if (list.size() == 1) {
            return String.valueOf(list.get(0));
        }
        int index = k / factorial(list.size() - 1);
        Integer d = list.get(index);
        List<Integer> newList = new ArrayList<>(list);
        newList.remove(index);
        int newK = k % factorial(list.size() - 1);
        return d + result(newList, newK);
    }

    private static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }
}
