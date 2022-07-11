package leetcode.day;

import java.util.*;

/**
 * 拓扑排序经典问题，外星人字典序
 *
 * @author violet.
 */
public class Day20220531 {

    static final int VISITING = 1, VISITED = 2;
    // 图的邻接表
    Map<Character, List<Character>> edges = new HashMap<>();
    // states
    Map<Character, Integer> states = new HashMap<>();
    // valid 是否成为环
    boolean valid = true;
    // result
    char[] order;

    int index;

    /**
     * @param words record the word order.
     * @return order result.
     */
    public String alienOrder(String[] words){
        int length = words.length;
        // init edges
        for (String word : words) {
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                edges.putIfAbsent(c, new ArrayList<>());
            }
        }
        for (int i = 1; i < length && valid; i++) {
            addEdge(words[i - 1], words[i]);
        }

        order = new char[edges.size()];
        index = edges.size() - 1;
        // 以首字母为起点, 深度优先遍历
        Set<Character> letters = edges.keySet();
        for (Character letter : letters) {
            // 如果该字母已经被访问过, 则跳过
            if (states.containsKey(letter)) {
                continue;
            }
            dfs(letter);
        }

        return valid ? new String(order) : "";
    }

    /**
     * @param before 上一个字符串
     * @param after 下一个字符串
     */
    public void addEdge(String before, String after) {
        int len1 = before.length(), len2 = after.length();
        int minLen = Math.min(len1, len2);
        // 循环指针
        int index = 0;
        while(index < minLen){
            char c1 = before.charAt(index), c2 = after.charAt(index);
            if (c1 != c2) {
                // 字典中的上一个中的最后一个字符和下一个中的字母不相同, 则添加边
                edges.get(c1).add(c2);
                break;
            }
            index++;
        }
        // 证明字典非法，前一个字符串中的字母比后一个字符串中的字母多
        if (index == minLen && len1 > len2) {
            valid = false;
        }
    }

    public void dfs(char letter) {
        // 更改状态
        states.put(letter, VISITING);
        // 获取该字符可以访问的字符
        List<Character> adjacent = edges.get(letter);
        // 遍历可以抵达的字符
        for (Character c : adjacent) {
            // 如果该字符已经被访问过, 则跳过
            if (!states.containsKey(c)) {
                dfs(c);
                if (!valid) {
                    return;
                }
            } else if (states.get(c) == VISITING) {
                // 由于是深度优先遍历, 如果发现有环, 则不是有效的
                valid = false;
                return;
            }
        }
        // 更改状态为已访问
        states.put(letter, VISITED);
        // 将字母放入结果数组，dfs的时候是从后往前的
        order[index] = letter;
        // 指针前置
        index--;
    }
}
