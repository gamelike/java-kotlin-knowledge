package leetcode.structure;

import java.util.Arrays;
import java.util.Random;

/**
 * Redis 中跳跃表结构  leetcode 1206 <br/>
 * <a href="https://leetcode.cn/problems/design-skiplist/">跳跃表题目</a>
 *
 * @author violet
 */
@SuppressWarnings("unused")
public class Skiplist {

    static final int MAX_LEVEL = 32; // skip list layers
    static final double P_FACTOR = 0.25; // ??
    private final ListNode head; // head node
    private int level; // current level
    private final Random random; // ??

    static class ListNode {
        private final int val;
        private final ListNode[] next;

        public ListNode(int val, int maxLevel) {
            this.val = val;
            this.next = new ListNode[maxLevel];
        }
    }

    public Skiplist() {
        this.head = new ListNode(-1, MAX_LEVEL); // head node;
        this.level = 0; // current level 0
        this.random = new Random();
    }

    public boolean search(int target) {
        ListNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            // find the nearest number
            while (curr.next[i] != null && curr.next[i].val < target) {
                curr = curr.next[i]; // the highest level -> the lowest level
            }
        }
        curr = curr.next[0];
        // valid
        return curr != null && curr.val == target;
    }

    public void add(int num) {
        ListNode[] updateNode = new ListNode[MAX_LEVEL];
        Arrays.fill(updateNode, head);
        ListNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].val < num) {
                curr = curr.next[i];
            }
            updateNode[i] = curr; // mark
        }
        int lv = randomLevel();
        level = Math.max(level, lv);
        ListNode newNode = new ListNode(num, lv);
        // 重排前向节点
        for (int i = 0; i < lv; i++) {
            // update the {i} layers
            newNode.next[i] = updateNode[i].next[i];
            updateNode[i].next[i] = newNode;
        }
    }

    public boolean erase(int num) {
        ListNode[] update = new ListNode[MAX_LEVEL];
        ListNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].val < num) {
                curr = curr.next[i];
            }
            update[i] = curr;
        }
        curr = curr.next[0]; // next node
        if (curr == null || curr.val != num) {
            return false;
        }
        for (int i = 0; i < level; i++) {
            if (update[i].next[i] != null) {
                break;
            }
            update[i].next[i] = curr.next[i]; // update i status.
        }
        while (level > 1 && head.next[level - 1] == null) {
            level--;
        }
        return true;
    }

    /**
     * random level. expect 50% level 1  25 % level 2
     */
    private int randomLevel() {
        int lv = 1;
        while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) {
            lv++;
        }
        return lv;
    }

}
