package newcoder.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem66 {
    public static final String[][] doubleCommands = new String[][]{{"reset", "board"}, {"board", "add"}, {"board", "delete"}, {"reboot", "backplane"}, {"backplane", "abort"}};
    public static final Map<String, String> doubleCommandsOutput = new HashMap<>();

    static {
        doubleCommandsOutput.put("reset board", "board fault");
        doubleCommandsOutput.put("board add", "where to add");
        doubleCommandsOutput.put("board delete", "no board at all");
        doubleCommandsOutput.put("reboot backplane", "impossible");
        doubleCommandsOutput.put("backplane abort", "install first");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] split = line.split(" ");
            if (split.length == 1) {
                singleCommand(split[0]);
            } else if (split.length == 2) {
                doubleCommand(split[0], split[1]);
            } else {
                System.out.println("unknown command");
            }
        }
    }

    private static void singleCommand(String cmd) {
        if ("reset".indexOf(cmd) == 0) {
            System.out.println("reset what");
        } else {
            System.out.println("unknown command");
        }
    }

    private static void doubleCommand(String cmd1, String cmd2) {
        String found = "";
        for (String[] doubleCommand : doubleCommands) {
            if (doubleCommand[0].indexOf(cmd1) == 0 && doubleCommand[1].indexOf(cmd2) == 0) {
                if (!"".equals(found)) {
                    System.out.println("unknown command");
                    return;
                } else {
                    found = doubleCommandsOutput.get(doubleCommand[0] + " " + doubleCommand[1]);
                }
            }
        }
        if ("".equals(found)) {
            System.out.println("unknown command");
        } else {
            System.out.println(found);
        }
    }
}
