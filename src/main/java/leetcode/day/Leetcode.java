package leetcode.day;

import leetcode.structure.dataStructure.ListNode;
import leetcode.structure.dataStructure.Node;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author violet
 */
@SuppressWarnings("all")
@Slf4j
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

    /**
     * 病毒问题 leetcode 749 <br/>
     * <a href="https://leetcode.cn/problems/contain-virus/">病毒防火墙</a>
     */
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

    /**
     * 贪心 & sort <br/>
     * <a href="https://leetcode.cn/problems/set-intersection-size-at-least-two"> 设置交集最小为2 </a>
     *
     * @param intervals 连续的数组
     * @return 最小集合
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

    /**
     * leetcode calc seal arr ^
     * make sure last and first is equal.
     *
     * @param derived
     * @return
     */
    public boolean doesValidArrayExist(int[] derived) {
        long count = Arrays.stream(derived).filter(it -> it == 1).count();
        return (count & 1) == 0;
    }


    /**
     * calc the max.
     *
     * @param fruits array.
     * @return max.
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * count submatrices with all ones.
     *
     * @param mat matrices
     * @return count.
     */
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        if (m < 1) {
            return 0;
        }
        int n = mat[0].length;
        int[][] row = new int[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    row[i][j] = mat[i][j];
                } else {
                    row[i][j] = mat[i][j] == 0 ? 0 : mat[i][j - 1] + 1;
                }
                int cur = row[i][j];
                for (int k = i; k >= 0; k--) {
                    cur = Math.min(cur, row[k][j]);
                    if (cur == 0) {
                        break;
                    }
                    count += cur;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                log.info("索引为: {} {}, 对应的值: {}", i, j, row[i][j]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Leetcode leetcode = new Leetcode();
        /*
         1 0 2
         1 2 0
         1 2 0
         */
        System.out.println(leetcode.numSubmat(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        }));
    }

}
