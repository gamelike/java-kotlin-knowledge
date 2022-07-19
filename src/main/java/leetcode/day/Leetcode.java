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
     * ����˼�� leetcode 558 <a href="https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/">�Ĳ�������</a>
     *
     * @param quadTree1 �Ĳ��� 1
     * @param quadTree2 �Ĳ��� 2
     * @return {@link Node}
     */
    public Node intersect(Node quadTree1, Node quadTree2) {
        // �ݹ������
        if (quadTree1.isLeaf) {
            // ������������ | ������ ��Զһ
            if (quadTree1.val) {
                return new Node(true, true, null, null, null, null);
            }
            // ������� ֱ��ʹ���������Ľڵ�
            return new Node(quadTree2.val, quadTree2.isLeaf, quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
        }
        // ������û�������������������ݹ�
        if (quadTree2.isLeaf) {
            return intersect(quadTree2, quadTree1);
        }
        // �Ĳ����ݹ�
        Node o1 = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node o2 = intersect(quadTree1.topRight, quadTree2.topRight);
        Node o3 = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node o4 = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        // ����ȫΪһ,��ݹ鵽�������Ҷ�ӽڵ�
        if (o1.isLeaf && o2.isLeaf && o3.isLeaf && o4.isLeaf && o1.val == o2.val && o1.val == o3.val && o1.val == o4.val) {
            return new Node(o1.val, true, null, null, null, null);
        }
        // ���ظ��ڵ�
        return new Node(false, false, o1, o2, o3, o4);
    }

    class Virus {
        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int containVirus(int[][] isInfected) {
            int m = isInfected.length, n = isInfected[0].length;
            int ans = 0;
            while (true) {
                List<Set<Integer>> neighbors = new ArrayList<>(); // �ڽӵ�
                List<Integer> firewalls = new ArrayList<>(); // ����ǽ����
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (isInfected[i][j] == 1) {
                            Queue<int[]> queue = new ArrayDeque<>();
                            queue.offer(new int[]{i, j});
                            Set<Integer> neighbor = new HashSet<>();
                            int firewall = 0, idx = neighbors.size() + 1;
                            isInfected[i][j] = -idx;
                            // BFS ���������Ⱦ�������
                            while (!queue.isEmpty()) {
                                int[] arr = queue.poll();
                                int x = arr[0], y = arr[1];
                                // ��ɢ
                                for (int d = 0; d < 4; ++d) {
                                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                        if (isInfected[nx][ny] == 1) { // �Ѿ��ǲ���
                                            queue.offer(new int[]{nx, ny});
                                            isInfected[nx][ny] = -idx;
                                        } else if (isInfected[nx][ny] == 0) { // �ǲ���
                                            ++firewall;
                                            neighbor.add(getHash(nx, ny));
                                        }
                                    }
                                }
                            }
                            // �����Ӧ�ķ���ǽ��Ҫ��װ����
                            neighbors.add(neighbor);
                            firewalls.add(firewall);
                        }
                    }
                }

                if (neighbors.isEmpty()) {
                    break;
                }

                // �ҵ����Ĳ�������
                int idx = 0;
                for (int i = 1; i < neighbors.size(); ++i) {
                    if (neighbors.get(i).size() > neighbors.get(idx).size()) {
                        idx = i;
                    }
                }
                // ������������������ǽ
                ans += firewalls.get(idx);
                // �ָ�����
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
                // ���˼��Ϸ���ǽ�ģ��������ñ���Ⱦ
                for (int i = 0; i < neighbors.size(); ++i) {
                    if (i != idx) {
                        for (int val : neighbors.get(i)) {
                            int x = val >> 16, y = val & ((1 << 16) - 1);
                            isInfected[x][y] = 1;
                        }
                    }
                }
                // ����Ⱦ���Ѿ�������,����ѭ��
                if (neighbors.size() == 1) {
                    break;
                }
            }
            return ans;
        }

        /**
         * ����ά����Ϊһά����������ϵ
         *
         * @param x ����
         * @param y ����
         * @return calc �� 16 λ �� �� 16 λ������.
         */
        public int getHash(int x, int y) {
            return (x << 16) ^ y;
        }
    }

    /**
     * �ճ�����,ģ�� (����ʹ���߶��� TODO ���� :( ��ѧϰ
     * <br/>
     * <a href="https://leetcode.cn/problems/my-calendar-ii/">�߶�����ϰ��</a>
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
