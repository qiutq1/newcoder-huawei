package newcoder.huawei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem17 {
    private static final Pattern MOVE_STRING_PATTERN = Pattern.compile("^([WASD])(\\d{1,2})$");


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int x = 0;
        int y = 0;
        for (String moveInst : input.split(";")) {
            int[] moveDelta = getMoveDelta(moveInst);
            x += moveDelta[0];
            y += moveDelta[1];
        }
        System.out.println(x + "," + y);
    }

    private static int[] getMoveDelta(String moveInst) {
        if (moveInst == null || moveInst.length() == 0) {
            return new int[]{0, 0};
        }
        Matcher matcher = MOVE_STRING_PATTERN.matcher(moveInst);
        if (!matcher.find()) {
            return new int[]{0, 0};
        }
        String direction = matcher.group(1);
        String distance = matcher.group(2);
        int distanceInt = Integer.parseInt(distance);
        switch (direction) {
            case "W": {
                return new int[]{0, distanceInt};
            }
            case "A": {
                return new int[]{-distanceInt, 0};
            }
            case "S": {
                return new int[]{0, -distanceInt};
            }
            case "D": {
                return new int[]{distanceInt, 0};
            }
            default: {
                throw new RuntimeException("wocao");
            }
        }
    }
}
