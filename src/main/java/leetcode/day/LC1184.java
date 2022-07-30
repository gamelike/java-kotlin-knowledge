package leetcode.day;

import org.junit.Test;

/**
 * @author gjd
 */
public class LC1184 {
  public int distanceBetweenBusStops(int[] distance, int start, int destination) {
    int clockWise = 0;
    int counterClockWise = 0;
    int i = start;
    int n = distance.length;
    while (i != destination) {
      i = (i + 1) % n;
      clockWise = clockWise + distance[i];
    }
    i = start;
    while (i != destination) {
      i = (i - 1 + n) % n;
      counterClockWise = counterClockWise + distance[i];

    }
    return Math.min(clockWise, counterClockWise);
  }

  @Test
  public void run() {
    distanceBetweenBusStops(new int[]{1, 2, 3, 4}, 0, 3);
  }
}
