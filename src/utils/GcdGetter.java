package utils;

public class GcdGetter {
    public static int getGcd(int... nums) {
        if (nums.length == 2) {
            int a = nums[0];
            int b = nums[1];
            while (true) {
                a = a % b;
                if (a == 0) return b;
                b = b % a;
                if (b == 0) return a;
            }
        } else if (nums.length > 2) {
            int gcd = getGcd(nums[0], nums[1]);
            int[] newNumbers = new int[nums.length - 1];
            newNumbers[0] = gcd;
            System.arraycopy(nums, 2, newNumbers, 1, nums.length - 2);
            return getGcd(newNumbers);
        } else {
            throw new RuntimeException();
        }
    }
}
