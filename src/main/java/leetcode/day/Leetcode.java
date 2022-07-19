package leetcode.day;

import leetcode.structure.dataStructure.Node;

import java.util.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author violet
 */
@SuppressWarnings("all")
public class Leetcode {

    /**
     * 分治思想 leetcode 558 <a href="https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/">四叉树交集</a>
     *
     * @param quadTree1 四叉树 1
     * @param quadTree2 四叉树 2
     * @return {@link Node}
     */
    public Node intersect(Node quadTree1, Node quadTree2) {
        // 递归结束点
        if (quadTree1.isLeaf) {
            // 构建数无子树 | 右子树 永远一
            if (quadTree1.val) {
                return new Node(true, true, null, null, null, null);
            }
            // 其他情况 直接使用右子树的节点
            return new Node(quadTree2.val, quadTree2.isLeaf, quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
        }
        // 左子树没结束，右子树结束，递归
        if (quadTree2.isLeaf) {
            return intersect(quadTree2, quadTree1);
        }
        // 四叉树递归
        Node o1 = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node o2 = intersect(quadTree1.topRight, quadTree2.topRight);
        Node o3 = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node o4 = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        // 子树全为一,则递归到父树变更叶子节点
        if (o1.isLeaf && o2.isLeaf && o3.isLeaf && o4.isLeaf && o1.val == o2.val && o1.val == o3.val && o1.val == o4.val) {
            return new Node(o1.val, true, null, null, null, null);
        }
        // 返回根节点
        return new Node(false, false, o1, o2, o3, o4);
    }

    class Virus {
        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int containVirus(int[][] isInfected) {
            int m = isInfected.length, n = isInfected[0].length;
            int ans = 0;
            while (true) {
                List<Set<Integer>> neighbors = new ArrayList<>(); // 邻接点
                List<Integer> firewalls = new ArrayList<>(); // 防火墙数量
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (isInfected[i][j] == 1) {
                            Queue<int[]> queue = new ArrayDeque<>();
                            queue.offer(new int[]{i, j});
                            Set<Integer> neighbor = new HashSet<>();
                            int firewall = 0, idx = neighbors.size() + 1;
                            isInfected[i][j] = -idx;
                            // BFS 遍历区域感染的区域块
                            while (!queue.isEmpty()) {
                                int[] arr = queue.poll();
                                int x = arr[0], y = arr[1];
                                // 扩散
                                for (int d = 0; d < 4; ++d) {
                                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                        if (isInfected[nx][ny] == 1) { // 已经是病毒
                                            queue.offer(new int[]{nx, ny});
                                            isInfected[nx][ny] = -idx;
                                        } else if (isInfected[nx][ny] == 0) { // 非病毒
                                            ++firewall;
                                            neighbor.add(getHash(nx, ny));
                                        }
                                    }
                                }
                            }
                            // 区域对应的防火墙需要安装个数
                            neighbors.add(neighbor);
                            firewalls.add(firewall);
                        }
                    }
                }

                if (neighbors.isEmpty()) {
                    break;
                }

                // 找到最大的病毒区域
                int idx = 0;
                for (int i = 1; i < neighbors.size(); ++i) {
                    if (neighbors.get(i).size() > neighbors.get(idx).size()) {
                        idx = i;
                    }
                }
                // 加上最大区域所需防火墙
                ans += firewalls.get(idx);
                // 恢复矩阵
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (isInfected[i][j] < 0) {
                            if (isInfected[i][j] != -idx - 1) {
                                isInfected[i][j] = 1;
                            } else {
                                isInfected[i][j] = 2;
                            }
                        }
                    }
                }
                // 除了加上防火墙的，其余设置被感染
                for (int i = 0; i < neighbors.size(); ++i) {
                    if (i != idx) {
                        for (int val : neighbors.get(i)) {
                            int x = val >> 16, y = val & ((1 << 16) - 1);
                            isInfected[x][y] = 1;
                        }
                    }
                }
                // 待感染区已经被隔离,跳出循环
                if (neighbors.size() == 1) {
                    break;
                }
            }
            return ans;
        }

        /**
         * 将二维抽象为一维，保存坐标系
         *
         * @param x 横轴
         * @param y 纵轴
         * @return calc 高 16 位 与 低 16 位与运算.
         */
        public int getHash(int x, int y) {
            return (x << 16) ^ y;
        }
    }

    /**
     * 日常安排,模拟 (可以使用线段树 TODO 不会 :( 待学习
     * <br/>
     * <a href="https://leetcode.cn/problems/my-calendar-ii/">线段树练习题</a>
     */
    class MyCalendarTwo {
        List<int[]> booked;
        List<int[]> overlaps;

        public MyCalendarTwo() {
            booked = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] overlap : overlaps) {
                if (overlap[0] < end && overlap[1] > start) {
                    return false;
                }
            }
            for (int[] book : booked) {
                if (book[0] < end && book[1] > start) {
                    overlaps.add(new int[]{Math.max(book[0], start), Math.min(book[1], end)});
                }
            }
            booked.add(new int[]{start, end});
            return true;
        }
    }
}
