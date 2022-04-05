package newcoder.huawei;

public class Problem32 {
    public static void main(String[] args) {
        String input = "abaaab";
        char[] chars = input.toCharArray();
        int n = chars.length;
        int max = 1;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                int left = i;
                int right = j;
                while (left <= right) {
                    if (chars[left] == chars[right]) {
                        left++;
                        right--;
                    } else {
                        break;
                    }
                }
                if (left >= right) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        System.out.println(max);
    }
}
