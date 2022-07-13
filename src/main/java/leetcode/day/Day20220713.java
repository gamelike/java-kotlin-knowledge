package leetcode.day;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day20220713 {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            if (asteroid < 0) {
                if(!deque.isEmpty() && deque.peek() < 0) {
                    deque.push(asteroid);
                    continue;
                }
                while (!deque.isEmpty() && deque.peek() > 0 && Math.abs(asteroid) > deque.peek()) {
                    deque.pop();
                }
                if(deque.isEmpty()) {
                    deque.push(asteroid);
                } else {
                    if(deque.peek() < 0) {
                        deque.push(asteroid);
                    }else if (deque.peek() == Math.abs(asteroid)) {
                        deque.pop();
                    }
                }
            }else {
                deque.push(asteroid);
            }
        }
        int[] res = new int[deque.size()];
        for (int length = res.length - 1; length >= 0; length--) {
            res[length] = deque.pop();
        }
        return res;
    }
}
