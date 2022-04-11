package newcoder.huawei;

import java.util.*;

public class Problem77 {
    public static List<String> outputs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Queue<Integer> wait = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            wait.offer(in.nextInt());
        }
        way(new Stack<>(), wait, new ArrayList<>());
        outputs.sort(String::compareTo);
        for (String output : outputs) {
            System.out.println(output);
        }
    }

    public static void way(Stack<Integer> stack, Queue<Integer> wait, List<Integer> order) {
        if (stack.isEmpty()) {
            if (wait.isEmpty()) {
                printOrder(order);
            } else {
                List<Integer> newOrder = new ArrayList<>(order);
                Stack<Integer> newStack = (Stack<Integer>) stack.clone();
                Queue<Integer> newWait = new LinkedList<>(wait);
                newStack.push(newWait.poll());
                way(newStack, newWait, newOrder);
            }
        } else if (wait.isEmpty()) {
            while (!stack.isEmpty()) {
                order.add(stack.pop());
            }
            printOrder(order);
        } else {
            List<Integer> newOrder = new ArrayList<>(order);
            Stack<Integer> newStack = (Stack<Integer>) stack.clone();
            Queue<Integer> newWait = new LinkedList<>(wait);
            newOrder.add(newStack.pop());
            way(newStack, newWait, newOrder);

            newOrder = new ArrayList<>(order);
            newStack = (Stack<Integer>) stack.clone();
            newWait = new LinkedList<>(wait);
            newStack.push(newWait.poll());
            way(newStack, newWait, newOrder);
        }
    }

    public static void printOrder(List<Integer> order) {
        String output = "";
        for (int i = 0; i < order.size() - 1; i++) {
            output += order.get(i) + " ";
        }
        output += order.get(order.size() - 1);
        outputs.add(output);
    }
}
