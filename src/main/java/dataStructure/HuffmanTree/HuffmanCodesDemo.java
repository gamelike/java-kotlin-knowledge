package HuffmanTree;

import java.io.*;
import java.util.*;

/**
 * @author: gameLike
 * @Date: 2020/6/3
 * 赫夫曼压缩
 */
public class HuffmanCodesDemo {

    public static void main(String[] args) {
        String str = "i like java and Python";
        byte[] bytes = str.getBytes();
        System.out.println("压缩前" + Arrays.toString(bytes) + "\n长度" + bytes.length);
/*    分步过程
       System.out.println(bytes.length);

        List<TreeNode> list = createMap(bytes);

        TreeNode root = createHuffman(list);

        preOrder(root);

        Map<Byte, String> codeList = createCodeList(root);
        System.out.println(codeList);

        byte[] result = getBytes(bytes, huffman);
        System.out.println(Arrays.toString(result));
        System.out.println(result.length);*/
        byte[] code = huffmanCode(bytes);
        System.out.println("压缩后" + Arrays.toString(bytes) + "\n长度：" + code.length);
        byte[] decode = decode(code, huffman);
        System.out.println("解压后" + new String(decode));

        System.out.println("========");
        zipFile("F:\\1.jpg","F:\\2.zip");
        System.out.println("压缩成功");
        decodeZipFile("F:\\2.zip","F:\\3.jpg");
        System.out.println("解压成功");
    }
    // 文件压缩
    /**
     * @param srcFile 源文件路径
     * @param desFile 压缩文件路径
     */
    public static void zipFile(String srcFile,String desFile){
        ObjectOutputStream oos = null;
        // 创建输入流
        FileInputStream is = null;
        // 创建输出流
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            // 创建缓冲区
            byte[] buffer = new byte[is.available()];
            // 读取文件
            is.read(buffer);
            // 对获取文件进行压缩
            byte[] code = huffmanCode(buffer);
            // 创建文件输出流,压缩文件
            os = new FileOutputStream(desFile);
            // 创建一个和输出流关联的
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的文件写入压缩文件
            oos.writeObject(code);
            // 哈夫曼编码表
            oos.writeObject(huffman);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 文件解压
    /**
     * @param srcFile 源文件路径
     * @param dstFile 解压文件路径
     */
    public static void decodeZipFile(String srcFile,String dstFile){
        // 定义文件输入流
        InputStream is = null;
        // 定义输入流关联对象流
        ObjectInputStream ois = null;
        // 定义输出流
        OutputStream os = null;
        try {
            // 创建流
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            // 读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取huffman编码表
            Map<Byte,String> map = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanBytes, map);
            os = new FileOutputStream(dstFile);
            // 数据写入
            os.write(decode);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 解码
    public static byte[] decode(byte[] bytes,Map<Byte,String> huffman){
        StringBuilder stringBuilder = new StringBuilder();
        // 将字节码转换为二进制
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = (i == bytes.length - 1);
            String b = toByte(bytes[i], flag);
            stringBuilder.append(b);
        }

//        System.out.println(stringBuilder.toString());
        // 反向查询huffman表
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffman.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        ArrayList<Byte> list = new ArrayList<>();
        int i = 0;
        // 滑动窗口
        while (i < stringBuilder.length()){
            int j = 1;
            String s = null;
            while (i + j <= stringBuilder.length()){
                s = stringBuilder.substring(i, i + j);
                if (map.get(s) == null) {
                    j++;
                }else {
                    list.add(map.get(s));
                    break;
                }
            }
            i += j;
        }
        byte[] b = new byte[list.size()];
        int k = 0;
        while (k < list.size()){
            b[k] = list.get(k);
            k++;
        }
        return b;
    }
    // 解码
    /**
     * @param b 需要转码的字节
     * @param flag 判断是否为最后一位
     * @return 返回字符串
     */
    public static String toByte(byte b, boolean flag){
        int temp = b;

        if (!flag) {
            temp = temp | 256;
        }
        String str = Integer.toBinaryString(temp);
        if (!flag || temp < 0){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }

    // 封装总方法 便于调用
    /**
     * @param bytes 原始字符串对应的bytes数组
     * @return 返回经过赫夫曼编码的数组
     */
    public static byte[] huffmanCode(byte[] bytes){
        // 1. 构建对应的结点
        List<TreeNode> list = createMap(bytes);
        // 2. 创建赫夫曼树 并获得根节点
        TreeNode root = createHuffman(list);
        // 3. 创建对应的赫夫曼表
        Map<Byte, String> map = createCodeList(root);
        // 4. 获得赫夫曼数组
        return getBytes(bytes,map);
    }

    // 前序遍历
    public static void preOrder(TreeNode root){
        root.preTraversal();
    }

    // 得到赫夫曼编码数组
    public static byte[] getBytes(byte[] bytes,Map<Byte,String> huffman){
        StringBuilder stringBuilder = new StringBuilder();
        // 利用 赫夫曼树改变原数组
        for (byte aByte : bytes) {
            stringBuilder.append(huffman.get(aByte));
        }
//        System.out.println(stringBuilder.toString());

        // 判断需要存储字符的长度
        int len = (stringBuilder.length() + 7) / 8;
        
        byte[] temp = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i = i + 8) {
            String str;
            if (i + 8 > stringBuilder.length()){
                str = stringBuilder.substring(i);
            }else {
                str = stringBuilder.substring(i, i + 8);
            }
            // 字符存放赫夫曼编码数组 8个二进制位
            temp[index] = (byte)Integer.parseInt(str,2);
            index++;
        }

        return temp;
    }

    // 存放赫夫曼树
    static Map<Byte,String> huffman = new HashMap<>();
    // 重载
    public static Map<Byte,String> createCodeList(TreeNode root){
        StringBuilder stringBuilder = new StringBuilder();
        createCodeList(root,"",stringBuilder);
        return huffman;
    }
    // 生成赫夫曼编码表
    public static void createCodeList(TreeNode treeNode, String code, StringBuilder str){
        // 连接赫夫曼编码
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append(code);
        // 判断是否为非叶子节点
        if (treeNode.data == null){
            // 判断左右路径，左为0，右为1
            if (treeNode.left != null){
                createCodeList(treeNode.left,"0",stringBuilder);
            }
            if (treeNode.right != null){
                createCodeList(treeNode.right,"1",stringBuilder);
            }
        }else {
            // 是非叶子节点后，加入
            huffman.put(treeNode.data,stringBuilder.toString());
        }
    }

    // 构建赫夫曼编码
    public static List<TreeNode> createMap(byte[] bytes) {
        Map<Byte, Integer> huffmanMap = new HashMap<>();
        for (byte aByte : bytes) {
            Integer count = huffmanMap.get(aByte);
            if (count != null) {
                huffmanMap.put(aByte, count + 1);
            } else {
                huffmanMap.put(aByte, 0);
            }
        }

        ArrayList<TreeNode> list = new ArrayList<>();
        for (Map.Entry<Byte,Integer> entry : huffmanMap.entrySet()){
            list.add(new TreeNode(entry.getValue(),entry.getKey()));
        }

        return list;
    }
    // 构建赫夫曼树
    public static TreeNode createHuffman(List<TreeNode> list){
        Collections.sort(list);
        while (list.size() > 1){
            TreeNode leftNode = list.get(0);
            TreeNode rightNode = list.get(1);

            TreeNode parentNode = new TreeNode(leftNode.val + rightNode.val, null);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);

            Collections.sort(list);
        }

        return list.get(0);
    }
}

// 存放的节点
class TreeNode implements Comparable<TreeNode>{
    // 存放出现的次数 ： 权值
    public int val;
    // 出现的字母
    public Byte data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val, Byte data) {
        this.val = val;
        this.data = data;
    }
    // 前序遍历
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
        return "TreeNode{" +
                "val=" + val +
                ", data=" + data +
                '}';
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.val - o.val;
    }
}
