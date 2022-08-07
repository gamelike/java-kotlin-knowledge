package leetcode.competition;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gjd
 */
public class SingleContent305 {
  public int arithmeticTriplets(int[] nums, int diff) {
    int length = nums.length;
    int res = 0;
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        if (nums[i] + diff == nums[j]) {
          for (int k = j + 1; k < length; k++) {
            if (nums[j] + diff == nums[k]) {
              res++;
            }
          }
        }
      }
    }
    return res;
  }

  public int reachableNodes(int n, int[][] edges, int[] restricted) {
    List<Integer>[] adj = new List[n];
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; i++) {
      adj[edges[i][0]].add(edges[i][1]);
      adj[edges[i][1]].add(edges[i][0]);
    }
    boolean[] vis = new boolean[n];
    for (int num : restricted) {
      vis[num] = true;
    }

    Deque<Integer> deque = new LinkedList<>();
    //init
    deque.offerLast(0);
    vis[0] = true;

    int ans = 1;
    while (!deque.isEmpty()) {
      int cur = deque.pollFirst();
      //找到当前点
      for (int next : adj[cur]) {
        if (!vis[next]) {
          deque.offerLast(next);
          ans++;
          vis[cur] = true;
        }
      }
    }
    return ans;
  }

  public boolean validPartition(int[] nums) {
    //1. 状态定义  boolean dp 可以则为true
    //2 状态方程 dp[n]
    // nums[i] ==nums[i-1]     dp[i] = dp[i-2] || dp[i]
    //   nums[i-1]==nums[i-2]  dp[i] = dp[i-3] || dp[i]
    //nums[i] - nums[i-1] = nums[i-1]-nums[i-2] = 1  dp[i] = dp[i] || dp[i-3]

    //3. 初始化 dp
    boolean[] dp = new boolean[nums.length];
    if (nums.length == 2) {
      return nums[0] == nums[1];
    } else if (nums.length < 2) {
      return false;
    }
    if (nums[0] == nums[1]) {
      dp[1] = true;
    }
    if (nums[0] == nums[1] && nums[1] == nums[2]) {
      dp[2] = true;
    }
    if (nums[2] - nums[1] == 1 && nums[1] - nums[0] == 1) {
      dp[2] = true;
    }

    //4. 遍历顺序从前往后遍历
    for (int i = 3; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        dp[i] = dp[i - 2] || dp[i];
        if (nums[i - 1] == nums[i - 2]) {
          dp[i] = dp[i] || dp[i - 3];
        }
      } else if (nums[i] - nums[i - 1] == 1 && nums[i - 1] - nums[i - 2] == 1) {
        dp[i] = dp[i] || dp[i - 3];
      }
    }
    //5. 返回形式
    return dp[nums.length - 1];
  }

  public int longestIdealString(String s, int k) {
    return 1;
  }
}
