package od2025e;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目
 * 有一个大小是N*M的战场地图，被墙壁 '#' 分隔成大小不同的区域，上下左右四个方向相邻的空地 '.' 属于同一个区域，只有空地上可能存在敌人'E”，请求出地图上总共有多少区域里的敌人数小于K
 * 输入描述
 * 第一行输入为N,M,K；
 * N表示地图的行数，M表示地图的列数， K表示目标敌人数量 N，M<=100 之后为一个NxM大小的字符数组
 * 输出描述
 * 敌人数小于K的区域数量
 * 用例
 * 用例一：
 * 输入：
 * 3 5 2
 * ..#EE
 * E.#E.
 * ###..
 * 输出：
 * 1
 */
public class Test85 {
    public static void main(String[] args) {
        int n = 3;
        int m = 5;
        int k = 2;
        String input = "..#EE\n" + "E.#E.\n" + "###..";
        List<List<Spot>> spots = new ArrayList<>();
        int newAreaIndex = 0;
        String[] split = input.split("\n");
        for (int row = 0; row < split.length; row++) {
            char[] charArray = split[row].toCharArray();
            List<Spot> rowSpots = new ArrayList<>();
            spots.add(rowSpots);
            for (int column = 0; column < charArray.length; column++) {
                char value = charArray[column];
                Spot spot = new Spot();
                rowSpots.add(spot);
                spot.value = value;
                if (value == '#') {
                    spot.area = Integer.MAX_VALUE;
                    continue;
                }
                if (row == 0 && column == 0) {
                    spot.area = newAreaIndex;
                    newAreaIndex++;
                    continue;
                }
                if (row == 0) {
                    if (rowSpots.get(column - 1).value != '#') {
                        spot.area = rowSpots.get(column - 1).area;
                    } else {
                        spot.area = newAreaIndex;
                        newAreaIndex++;
                    }
                    continue;
                }
                if (column == 0) {
                    if (rowSpots.get(row - 1).value != '#') {
                        spot.area = rowSpots.get(row - 1).area;
                    } else {
                        spot.area = newAreaIndex;
                        newAreaIndex++;
                    }
                    continue;
                }
                if (spots.get(row - 1).get(column).value != '#') {
                    spot.area = spots.get(row - 1).get(column).area;
                }
                if (rowSpots.get(column - 1).value != '#') {
                    for (int i = column - 1; i >= 0; i--) {
                        if (rowSpots.get(i).value != '#') {
                            rowSpots.get(i).area = spot.area;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(spots);
    }

    private static class Spot {
        private int area;
        private char value;

        @Override
        public String toString() {
            return area + "";
        }
    }
}
