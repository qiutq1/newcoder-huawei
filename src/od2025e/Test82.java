package od2025e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目
 * 2XXX年，人类通过对火星的大气进行宜居改造分析，使得火星已在理论上具备人类宜居的条件；由于技术原因，无法一次性将火星大气全部改造，只能通过局部处理形式；假设将火星待改造的区域为row * column的网格，每个网格有3个值，宜居区、可改造区、死亡区，使用YES、NO、NA代替，YES表示该网格已经完成大气改造，NO表示该网格未进行改造，后期可进行改造，NA表示死亡区，不作为判断是否改造完的宜居，无法穿过；初始化下，该区域可能存在多个宜居区，并目每个宜居区能同时在每个大阳日单位向上下左右四个方向的相邻格子进行扩散，自动将4个方向相邻的真空区改造成宜居区；请计算这个待改造区域的网格中，可改造区是否能全部成宜居区，如果可以，则返回改造的大阳日天教，不可以则返回-1
 * 输入描述
 * 输入row * column个网格数据，每个网格值枚举值如下: YES，NO，NA；
 * 样例:
 * YES YES NO NO NO NO NA NO YES 
 * 输出描述
 * 可改造区是否能全部变成宜居区，如果可以，则返回改造的太阳日天数，不可以则返回-1
 * 备注
 * grid[i][j]只有3种情况，YES、NO、NA
 * row == grid.length column == grid[i].length 1 ≤ row, column ≤ 8
 * 用例
 * 用例一：
 * 输入：
 * YES YES NO
 * NO NO NO
 * YES NO NO
 * 输出：
 * 2
 * 用例二：
 * 输入：
 * YES NO NO NO
 * NO NO NO NO
 * NO NO NO NO
 * NO NO NO NO
 * 输出：
 * 6
 * 用例三：
 * 输入：
 * NO NA
 * 输出：
 * -1
 * 用例四：
 * 输入：
 * YES NO NO YES
 * NO NO YES NO
 * NO YES NA NA
 * YES NO NA NO
 * 输出：
 * -1
 * 说明 右下角的区域，被周边三个死亡区挡住，无法实现改造
 */
public class Test82 {
    public static void main(String[] args) {
        // String input = "YES YES NO\n" + "NO NO NO\n" + "YES NO NO";
        // String input = "YES NO NO NO\n" + "NO NO NO NO\n" + "NO NO NO NO\n" + "NO NO NO NO";
        // String input = "NO NA";
        String input = "YES NO NO YES\n" + "NO NO YES NO\n" + "NO YES NA NA\n" + "YES NO NA NO";
        List<List<String>> states = new ArrayList<>();
        for (String row : input.split("\n")) {
            List<String> rowStates = new ArrayList<>();
            states.add(rowStates);
            rowStates.addAll(Arrays.asList(row.split(" ")));
        }
        if (!input.contains("YES")) {
            System.out.println(-1);
            return;
        }
        if (!input.contains("NO")) {
            System.out.println(0);
            return;
        }

        boolean changed = true;
        int count = 0;
        while (changed) {
            changed = false;
            List<List<String>> originStates = cloneStates(states);

            for (int row = 0; row < states.size(); row++) {
                for (int column = 0; column < states.get(row).size(); column++) {
                    if (originStates.get(row).get(column).equals("YES")) {
                        changed |= gaizao(originStates, states, row, column);
                    }
                }
            }
            if (changed) {
                count++;
            }
        }
        if (isFinish(states)) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }

    private static List<List<String>> cloneStates(List<List<String>> states) {
        List<List<String>> clonedStates = new ArrayList<>();
        for (List<String> row : states) {
            List<String> clonedRow = new ArrayList<>();
            clonedStates.add(clonedRow);
            clonedRow.addAll(row);
        }
        return clonedStates;
    }

    private static boolean gaizao(List<List<String>> originStates, List<List<String>> states, int row, int column) {
        boolean changed = false;
        if (row > 0) {
            if (originStates.get(row - 1).get(column).equals("NO")) {
                states.get(row - 1).set(column, "YES");
                changed = true;
            }
        }
        if (row < states.size() - 1) {
            if (originStates.get(row + 1).get(column).equals("NO")) {
                states.get(row + 1).set(column, "YES");
                changed = true;
            }
        }
        if (column > 0) {
            if (originStates.get(row).get(column - 1).equals("NO")) {
                states.get(row).set(column - 1, "YES");
                changed = true;
            }
        }
        if (column < states.size() - 1) {
            if (originStates.get(row).get(column + 1).equals("NO")) {
                states.get(row).set(column + 1, "YES");
                changed = true;
            }
        }
        return changed;
    }

    private static boolean isFinish(List<List<String>> states) {
        for (List<String> row : states) {
            for (String column : row) {
                if (column.equals("NO")) {
                    return false;
                }
            }
        }
        return true;
    }
}
