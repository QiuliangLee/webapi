package leetcoce;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Lucas
 * @create 2022-08-13 16:04
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), t = sc.nextInt();
        int[] nums = new int[n];
        int res = 0, time = 5;
        Arrays.sort(nums);
        if (t * n <= nums[0]) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            if (nums[i] < time) {
                res++;
            } else {
                time += t;
            }
        }
        System.out.println(res);
    }
}

