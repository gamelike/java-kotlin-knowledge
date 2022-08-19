package leetcode.day;

import leetcode.TreeNode;
import leetcode.chainTable.LC203;
import leetcode.structure.dataStructure.ListNode;
import leetcode.structure.dataStructure.Node;
import org.apache.poi.ss.formula.functions.T;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.*;

import java.util.HashSet;
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

    /**
     * �������� leetcode 749 <br/>
     * <a href="https://leetcode.cn/problems/contain-virus/">��������ǽ</a>
     */
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

    /**
     * ̰�� & sort <br/>
     * <a href="https://leetcode.cn/problems/set-intersection-size-at-least-two"> ���ý�����СΪ2 </a>
     *
     * @param intervals ����������
     * @return ��С����
     */
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        int ans = 0, m = 2;
        Arrays.sort(intervals, ((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]));

        List<Integer>[] tem = new List[n];

        for (int i = 0; i < n; i++) {
            tem[i] = new ArrayList<>();
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = intervals[i][0], k = tem[i].size(); k < m; k++, j++) {
                ans++;
                help(intervals, tem, i - 1, j);
            }
        }

        return ans;
    }

    private void help(int[][] intervals, List<Integer>[] tem, int pos, int num) {
        for (int i = pos; i >= 0; i--) {
            if (intervals[i][1] < num) {
                break;
            }
            tem[i].add(num);
        }
    }

    static class CBTInserter {

        TreeNode root;
        Queue<TreeNode> queue;

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                TreeNode cur = queue.peek();
                if (cur.getLeft() == null) break;
                queue.offer(cur.getLeft());
                if (cur.getRight() == null) break;
                queue.offer(cur.getRight());
                queue.poll();
            }
        }

        public int insert(int val) {
            TreeNode cur = queue.peek();
            TreeNode node = new TreeNode(val);
            if (cur.getLeft() == null) {
                cur.setLeft(node);
                queue.offer(node);
            }
            if (cur.getRight() == null) {
                cur.setRight(node);
                queue.poll();
                queue.offer(node);
            }
            return cur.getVal();
        }

        public TreeNode get_root() {
            return root;
        }

    }

    static class Palindrome {
        public boolean isPalindrome(ListNode head) {
            int[] num = new int[10];

            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode curr = slow.next;
            ListNode pre = null;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            ListNode cur = head;
            while (pre != null) {
                if (pre.val != cur.val) {
                    return false;
                }
                pre = pre.next;
                cur = cur.next;
            }
            return true;
        }


    }

    @Test
    public void test111() {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        head.next = two;
        ListNode three = new ListNode(2);
        two.next = three;
        ListNode four = new ListNode(1);
        three.next = four;
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome(head));
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        root.setLeft(t2);
        root.setRight(t3);
        t2.setLeft(t4);
        t2.setRight(t5);
        t3.setLeft(t6);
        CBTInserter cbtInserter = new CBTInserter(root);
        System.out.println(cbtInserter.insert(6));
        System.out.println(cbtInserter.insert(7));
        System.out.println(cbtInserter.get_root());

        List<Integer> list = List.of(1, 2, 3, 4);
    }

}
