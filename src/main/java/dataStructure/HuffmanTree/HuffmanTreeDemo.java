package HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: gameLike
 * @Date: 2020/6/3
 * 赫夫曼树
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {54,13,65,11,3,5,9,1,10};
        List<Node> list = new ArrayList<>();
        for (int i : arr) {
            list.add(new Node(i));
        }
        Collections.sort(list);
        Node root = createHuffmanTree(list);
        forEach(root);
    }

    // 构建哈夫曼树
    public static Node createHuffmanTree(List<Node> list){
        while (list.size() > 1){
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);

            Node parent = new Node(leftNode.val + rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;

            list.remove(1);
            list.remove(0);

            list.add(parent);
            Collections.sort(list);
        }

        return list.get(0);
    }

    // 前序遍历
    public static void forEach(Node root){
        root.preTraversal();
    }
}

// 构建节点
class Node implements Comparable<Node> {
    public int val;
    public Node left;
    public Node right;

    public Node(int val){
        this.val = val;
    }

    public void preTraversal(){
        System.out.println(this);
        if (this.left != null){
            this.left.preTraversal();
        }
        if (this.right != null){
            this.right.preTraversal();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}