package leetcode.competition;

import org.junit.Test;

import java.util.*;

public class Single301 {

    public int fillCups(int[] amount) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : amount) {
            queue.offer(i);
        }
        int index = 0;
        while (true) {
            int i1 = queue.poll();
            int i2 = queue.poll();
            i1--;
            i2--;
            index++;
            if (queue.peek() == 0) {
                return index + i1;
            }
            queue.offer(i1);
            queue.offer(i2);
        }
    }
    @Test
    public void t() {
        System.out.println(fillCups(new int[]{5, 4, 4}));
    }

    public boolean canChange(String start, String target) {
        if (start.length() != target.length()) return false;
        if (!start.replace("_","").equals(target.replace("_",""))) return false;
        Queue<Integer> left = new LinkedList<>();
        Queue<Integer> right = new LinkedList<>();
        for (int i = 0; i < start.length(); i++) {
            if (target.charAt(i) == 'L') left.offer(i);
            if (start.charAt(i) == 'L') {
                Integer poll = left.poll();
                if (poll == null || poll > i) return false;
            }
        }
        for (int length = start.length() - 1; length >= 0; length--) {
            if (target.charAt(length)=='R') right.offer(length);
            if (start.charAt(length) == 'R') {
                Integer poll = right.poll();
                if (poll == null || length > poll) return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(canChange("_L__R__R_", "L______RR"));
    }

}
