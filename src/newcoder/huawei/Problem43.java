package newcoder.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem43 {
    // TODO 深度优先搜索
    public static final byte[][] dirs = new byte[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static byte n;
    private static byte m;
    private static byte[][] maze;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextByte();
        m = in.nextByte();
        maze = new byte[n][m];
        for (byte i = 0; i < n; i++) {
            for (byte j = 0; j < m; j++) {
                maze[i][j] = in.nextByte();
            }
        }
        List<Position> path = new ArrayList<>();
        getPath(new Position((byte) 0, (byte) 0), path);
        path.forEach(System.out::println);
    }

    public static void getPath(Position from, List<Position> path) {
        path.add(from);
        maze[from.x][from.y] = 1;
        if (from.x == n - 1 && from.y == m - 1) {
            return;
        }
        for (byte[] dir : dirs) {
            byte newX = (byte) (from.x + dir[0]);
            byte newY = (byte) (from.y + dir[1]);
            if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                continue;
            }
            Position neighbour = new Position(newX, newY);
            if (maze[newX][newY] == 0) {
                try {
                    getPath(neighbour, path);
                    return;
                } catch (MyE ignored) {
                }
            }
        }
        path.remove(path.size() - 1);
        throw new MyE();
    }

    public static class Position {
        public byte x;
        public byte y;

        public Position(byte x, byte y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static class MyE extends RuntimeException {
    }
}
