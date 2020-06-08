package AVLTree;

/**
 * @author: gameLike
 * @Date: 2020/6/4
 * avl树，平衡二叉树
 */
public class avl {
    public static void main(String[] args) {
        int[] arr = {10,11,7,6,8,9};
        avlTree tree = new avlTree();
        for (int value : arr) {
            tree.addNode(new Node(value));
        }
        Node root = tree.getRoot();
        tree.infixOrder(root);
        System.out.println(root.leftHeight());
        System.out.println(root.rightHeight());
        System.out.println(root.height());
    }
}

// 创建二叉排序树
class avlTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void addNode(Node node){
        if(root != null){
            root.addNode(node);
        }else {
            setRoot(node);
        }
    }

    public void infixOrder(Node root){
        if (root == null){
            System.out.println("树为null");
        }else {
            root.infixTraversal();
        }
    }

    public Node searchNode(Node root, int val){
        if (root != null){
            return root.search(val);
        }else {
            System.out.println("树为null");
            return null;
        }
    }

    public Node searchParent(Node root, int val){
        if (root != null){
            return root.searchParent(val);
        }else {
            System.out.println("树为null");
            return null;
        }
    }

    public void delete(Node root, int val){
        if (root == null){
            System.out.println("树为空");
        }else {
            Node curNode = searchNode(root,val);

            if (curNode == null){
                System.out.println("未找到");
                return;
            }
            Node parentNode = searchParent(root,val);
            if (curNode == root && root.getLeft() == null && root.getRight() == null){
                curNode = null;
                return;
            }else if (curNode == root && root.getLeft() != null && root.getRight() == null){
                Node target = curNode.getLeft();
                while (target.getRight() != null){
                    target = target.getRight();
                }
                int temp = target.getVal();
                delete(root,temp);
                curNode.setVal(temp);
                return;
            }else if (curNode == root && root.getLeft() == null && root.getRight() != null){
                // 临时指针，用于找到右子树最小节点
                Node target = curNode.getRight();
                while (target.getLeft() != null){
                    target = target.getLeft();
                }
                // 存储最小节点的值
                int temp = target.getVal();
                // 删除右子树最小节点
                delete(root,temp);
                // 交换最小节点和当前节点的值
                curNode.setVal(temp);
                // 删除有一颗子树的节点
            }
            // 删除叶子节点
            if (curNode.getRight() == null && curNode.getLeft() == null){
                if (parentNode.getLeft() != null && parentNode.getLeft().getVal() == val){
                    parentNode.setLeft(null);
                }else if (parentNode.getRight() != null && parentNode.getRight().getVal() == val){
                    parentNode.setRight(null);
                }
                // 删除有两颗子树的节点
            }else if (curNode.getLeft() != null && curNode.getRight() != null){
                // 临时指针，用于找到右子树最小节点
                Node target = curNode.getRight();
                while (target.getLeft() != null){
                    target = target.getLeft();
                }
                // 存储最小节点的值
                int temp = target.getVal();
                // 删除右子树最小节点
                delete(root,temp);
                // 交换最小节点和当前节点的值
                curNode.setVal(temp);
                // 删除有一颗子树的节点
            }else {
                if (curNode.getLeft() != null){
                    if (parentNode.getLeft().getVal() == val){
                        parentNode.setLeft(curNode.getLeft());
                    }else {
                        parentNode.setRight(curNode.getLeft());
                    }
                }else {
                    if (parentNode.getLeft().getVal() == val){
                        parentNode.setLeft(curNode.getRight());
                    }else {
                        parentNode.setRight(curNode.getRight());
                    }
                }
            }
        }
    }
}

// 创建节点
class Node{
    private int val;
    private Node left;
    private Node right;

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    // 遍历
    public void infixTraversal(){
        if (this.getLeft() != null){
            this.getLeft().infixTraversal();
        }

        System.out.println(this);

        if (this.getRight() != null){
            this.getRight().infixTraversal();
        }
    }

    // 添加
    public void addNode(Node node){
        if (node == null) return;

        if (node.val <= this.val){
            if (this.getLeft() == null){
                this.setLeft(node);
            }else {
                this.getLeft().addNode(node);
            }
        }else {
            if (this.getRight() == null){
                this.setRight(node);
            }else {
                this.getRight().addNode(node);
            }
        }

        if (rightHeight() - leftHeight() > 1){
            // 如果左子树的左子树高度大于右子树高度
            if (right != null && right.rightHeight() < right.leftHeight()){
                // 左节点右旋
                right.rightRotate();
            }
            levoversionTree();
        }
        if (leftHeight() - rightHeight() > 1){
            // 如果左子树的右子树高度大于左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                // 左节点左旋
                left.levoversionTree();
            }
            rightRotate();
        }
    }

    // 查询节点
    public Node search(int val){
        if (this.val == val){
            return this;
        }else if (this.val > val){
            if (this.left == null){
                return null;
            }
            return this.left.search(val);
        }else {
            if (this.right == null){
                return null;
            }
            return this.right.search(val);
        }
    }

    // 查询节点的父节点
    public Node searchParent(int val){
        // 该节点为查询节点父节点
        if ((this.left != null && this.left.val == val) || (this.right!= null && this.right.val == val)) {
            return this;
        }else if (val < this.val && this.left != null){
            // 向左递归
            return this.left.searchParent(val);
        }else if (val > this.val && this.right != null){
            // 向右递归
            return this.right.searchParent(val);
        }else {
            // 无
            return null;
        }
    }

    // 返回以该节点为根节点的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
    }
    // 左子树高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }
    // 右子树高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }
    // 左旋
    public void levoversionTree(){
        // 创建新的节点旋转，同根节点值相同
        Node node = new Node(val);
        // 将新节点左子树指向根节点左子树
        node.left = left;
        // 右子树为旋转过来的新的根节点的右子树的左子树
        node.right = right.left;
        // 将根节点值变化为右子树的值（旋转）
        val = right.val;
        // 根节点的值指向原本的右子树
        right = right.right;
        // 根节点左子树指向创建的新子树
        left = node;
    }
    // 右旋
    public void rightRotate(){
        // 同左旋原理相同
        Node node = new Node(val);
        node.right = right;
        node.left = left.right;
        val = left.val;
        left = left.left;
        right = node;
    }
}