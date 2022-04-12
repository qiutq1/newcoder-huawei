package newcoder.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem68 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int sortFunction = in.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(in.next(), in.nextInt()));
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int compare = students.get(j).compareTo(students.get(j + 1));
                if (sortFunction == 1 ? compare > 0 : compare < 0) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        for (Student student : students) {
            System.out.println(student.name + " " + student.score);
        }
    }

    private static class Student implements Comparable<Student> {
        public String name;
        public int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            return score - o.score;
        }
    }
}
