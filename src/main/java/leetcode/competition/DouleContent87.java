package leetcode.competition;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gjd3
 */
public class DouleContent87 {
  public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
    //找出 比较来时比较大的 和 返回时候比较小的  计算两者之间的差值
    String[] aliceArriveStrs = arriveAlice.split("-");
    String[] bobarriveStrs = arriveBob.split("-");
    String start = new String();
    if (Integer.parseInt(aliceArriveStrs[0]) > Integer.parseInt(bobarriveStrs[0])) {
      start = arriveAlice;
    } else if (Integer.parseInt(aliceArriveStrs[0]) < Integer.parseInt(bobarriveStrs[0])) {
      start = arriveBob;
    } else {
      if (Integer.parseInt(aliceArriveStrs[1]) > Integer.parseInt(bobarriveStrs[1])) {
        start = arriveAlice;
      } else { //用那个都可以
        start = arriveBob;
      }
    }

    String[] aliceLeaveStrs = leaveAlice.split("-");
    String[] bobLeaveStrs = leaveBob.split("-");
    String end = new String();
    if (Integer.parseInt(aliceLeaveStrs[0]) < Integer.parseInt(bobLeaveStrs[0])) {
      end = leaveAlice;
    } else if (Integer.parseInt(aliceLeaveStrs[0]) > Integer.parseInt(bobLeaveStrs[0])) {
      end = leaveBob;
    } else {
      if (Integer.parseInt(aliceLeaveStrs[1]) < Integer.parseInt(bobLeaveStrs[1])) {
        end = leaveAlice;
      } else { //用那个都可以
        end = leaveBob;
      }
    }
    String[] starts = start.split("-");
    String[] ends = end.split("-");
    int result = 0;
    int[] dayOfMonths = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (Integer.parseInt(starts[0]) == Integer.parseInt(ends[0])) {
      result = Integer.parseInt(ends[1]) - Integer.parseInt(starts[1]) + 1;
    } else if (Integer.parseInt(starts[0]) < Integer.parseInt(ends[0])) {
      result = result + (dayOfMonths[Integer.parseInt(starts[0])] - Integer.parseInt(starts[1])) + 1;//获取当月剩余天数
      for (int i = Integer.parseInt(starts[0]) + 1; i < Integer.parseInt(ends[0]); i++) {
        result = result + dayOfMonths[i];
      }
      result = result + Integer.parseInt(ends[1]);
    } else {
      result = 0;
    }
    return result > 0 ? result : 0;
  }

  @Test
  public void run() {
    countDaysTogether("09-01", "10-19", "06-19", "10-20");
  }

  public int matchPlayersAndTrainers(int[] players, int[] trainers) {
    int trainerIndex = 0;
    int result = 0;
    Arrays.sort(players);
    Arrays.sort(trainers);
    for (int i = 0; i < players.length && trainerIndex < trainers.length; i++) {
      for (int j = trainerIndex; j < trainers.length; j++) {
        if (players[i] <= trainers[j]) {
          result++;
          trainerIndex = j + 1;
          break;
        }
      }
    }
    return result;
  }

  public int[] smallestSubarrays(int[] nums) {
    int veri = 0;
    for (int i = 0; i < nums.length; i++) {
      veri |= nums[i];
    }

    for (int i = 0; i < nums.length; i++) {
      int temp = 0;
      for (int j = i; j < nums.length; j++) {
        temp |= nums[j];
        if (temp == veri) {
          nums[i] = j - i;
          break;
        }
      }
    }
    return nums;
  }

  @Test
  public void run3() {
    smallestSubarrays(new int[]{1, 2});
  }

  public long minimumMoney(int[][] transactions) {
    //每次返回最低收益
    PriorityQueue<Project> priorityQueue = new PriorityQueue<>(new Comparator<Project>() {
      @Override
      public int compare(Project o1, Project o2) {
        if (o1.cost == o2.cost) {
          return o1.cashback - o2.cashback;
        } else {
          return o2.cost - o1.cost;
        }
      }
    });
    for (int[] transaction : transactions) {
      priorityQueue.offer(new Project(transaction[0], transaction[1]));
    }
    long money = 0;
    while (!priorityQueue.isEmpty()) {
      System.out.println(priorityQueue.poll());
    }
    long result = 0;
    while (!priorityQueue.isEmpty()) {
      Project project = priorityQueue.poll();
      if (project.cost > money) {
        //需要补充的钱数
        long temp = project.cost - money;
        result = result + temp;
        money = money + temp;
      }
      money = money - project.cost;
      money = money + project.cashback;
    }
    return result;
  }

  private class Project {
    int cost;
    int cashback;

    public Project(int cost, int cashback) {
      this.cost = cost;
      this.cashback = cashback;
    }

    @Override
    public String toString() {
      return "Project{" +
          "cost=" + cost +
          ", cashback=" + cashback +
          '}';
    }
  }

  @Test
  public void run4() {
    minimumMoney(new int[][]{
        {3, 9}, {0, 4}, {7, 10}, {3, 5}, {0, 9}, {9, 3}, {7, 4}, {0, 0}, {3, 3}, {8, 0}
    });
  }
}
