package leetcode.competition;

import org.junit.Test;

import java.util.*;

/**
 * @author violet
 * @since 2022/7/23
 */
@SuppressWarnings("all")
public class Double83 {

    class NumberContainers {

        Map<Integer, PriorityQueue<Integer>> map;
        Map<Integer, Integer> index;

        public NumberContainers() {
            map = new HashMap<>();
            index = new HashMap<>();
        }

        public void change(int index, int number) {
            PriorityQueue<Integer> deque = map.get(number);
            if (deque == null) {
                deque = new PriorityQueue<>(Comparator.comparing(o -> o));
                map.put(number, deque);
            }
            deque.add(index);
            this.index.put(index, number);
        }

        public int find(int number) {
            PriorityQueue<Integer> queue = map.get(number);
            if (queue != null && !queue.isEmpty()) {
                while (!queue.isEmpty() &&index.get(queue.peek()) != null && index.get(queue.peek()) != number) {
                    queue.poll();
                }
                return queue.isEmpty() ? -1 : queue.peek();
            }
            return -1;
        }
    }

    public int shortestSequence(int[] rolls, int k) {
        var count = 0;

        Set<Integer> set = new HashSet<>();
        for (int roll : rolls) {
            set.add(roll);
            if (set.size() == k) {
                count++;
                set = new HashSet<>();
            }
        }

        return count;
    }

    @Test
    public void main() {
        NumberContainers numberContainers = new NumberContainers();
        numberContainers.change(1,10);
        numberContainers.change(2,10);
        numberContainers.change(1,20);
        numberContainers.find(10);
    }

}
