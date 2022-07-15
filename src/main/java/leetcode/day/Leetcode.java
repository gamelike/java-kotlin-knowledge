package leetcode.day;

import leetcode.structure.dataStructure.Node;

/**
 * @author violet
 */
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
}
