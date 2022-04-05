package newcoder.huawei;

import java.util.LinkedList;
import java.util.Scanner;

public class Problem48 {
    public static LinkedList<Node> list = new LinkedList<>();
    public static Node root = new Node(0, null);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        root = new Node(in.nextInt(), null);
        for (int i = 0; i < n - 1; i++) {
            Node currentNode = new Node(in.nextInt(), null);
            Node prevNode = find(in.nextInt());
            assert prevNode != null;
            currentNode.next = prevNode.next;
            prevNode.next = currentNode;
        }
        int remove = in.nextInt();
        if (root.v == remove) {
            root = root.next;
        } else {
            Node node = root;
            do {
                if (node.next.v == remove) {
                    node.next = node.next.next;
                    break;
                }
                node = node.next;
            } while (true);
        }
        while (root != null) {
            System.out.print(root.v + " ");
            root = root.next;
        }
        System.out.println();
    }

    public static Node find(int v) {
        Node node = root;
        do {
            if (node.v == v) {
                return node;
            }
            node = node.next;
        } while (node != null);
        return null;
    }

    public static class Node {
        int v;
        Node next;

        public Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    '}';
        }
    }
}
