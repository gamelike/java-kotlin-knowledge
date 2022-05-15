package Tree;
/**
 * @author: gameLike
 * @Date: 2020/5/28
 * 顺序存储二叉树
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        ArrTree tree = new ArrTree(arr);
        tree.preTraversal();
        System.out.println("\n==========");
        tree.infixTraversal();
        System.out.println("\n===========");
        tree.postTraversal();
    }
}

class ArrTree{
    private int[] arr;

    public ArrTree(int[] arr) {
        this.arr = arr;
    }
    // 重载调用 ， 默认传参 0 根节点
    public void preTraversal(){
        this.preTraversal(0);
    }
    // 顺序存储二叉树 ， 前序遍历
    public void preTraversal(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        System.out.print(arr[index] + " ");
        if (index * 2 + 1 < arr.length){
            preTraversal(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length){
            preTraversal(index * 2 + 2);
        }
    }
    public void infixTraversal(){
        this.infixTraversal(0);
    }
    // 中序遍历
    public void infixTraversal(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }

        if (index * 2 + 1 < arr.length){
            infixTraversal(index * 2 + 1);
        }

        System.out.print(arr[index] + " ");

        if (index * 2 + 2 < arr.length){
            infixTraversal(index * 2 + 2);
        }
    }
    public void postTraversal(){
        this.postTraversal(0);
    }
    // 后序遍历
    public void postTraversal(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }

        if (index * 2 + 1 < arr.length){
            postTraversal(index * 2 + 1);
        }

        if (index * 2 + 2 < arr.length){
            postTraversal(index * 2 + 2);
        }

        System.out.print(arr[index] + " ");
    }
}
