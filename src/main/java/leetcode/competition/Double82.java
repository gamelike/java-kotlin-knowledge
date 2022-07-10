package leetcode.competition;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双周赛 82
 */
public class Double82 {


    public boolean evaluateTree(TreeNode root) {
        return dfs(root);
    }

    public boolean dfs(TreeNode root) {
        if (root.left == null || root.right == null) {
            return root.val == 1;
        }

        if (root.val == 3) {
            return dfs(root.left) && dfs(root.right);
        }

        if (root.val == 2) {
            return dfs(root.left) || dfs(root.right);
        }

        return root.val == 1;
    }

    /**
     * 模拟上车
     */
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int c = 0, j = 0;
        for (int bus : buses) {
            for (c = capacity; c > 0 && j < passengers.length && passengers[j] <= bus;j++) {
                c--;
            }
        }
        j--;
        var ans = c > 0 ? buses[buses.length - 1] : passengers[j];
        while(j >= 0 && passengers[j--] == ans) ans--;
        return ans;
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        Queue<Long> deque = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) (o2 - o1);
            }
        });
        for (int i = 0; i < n; i++) {
            deque.add((long) Math.abs(nums1[i] - nums2[i]));
        }
        int sum = k1 + k2;
        while (sum > 0) {
            long poll = deque.poll();
            if (poll == 0) break;
            poll--;
            deque.offer(poll);
            sum--;
        }
        if ((sum & 1) != 0) return 0;
        long res = 0;
        for(var i : deque) {
            sum += i * i;
        }
        return res;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
