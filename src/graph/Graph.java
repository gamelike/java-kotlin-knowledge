package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private List<String> vertexList;
    private int[][] adjacencyMatrix;
    private int numOfEdges;
    private boolean[] isVisit;

    public static void main(String[] args) {
        String[] value = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(value.length);
        for (String s : value) {
            graph.addVertex(s);
        }
        graph.addEdges(0,1,1);
        graph.addEdges(0,2,1);
        graph.addEdges(1,3,1);
        graph.addEdges(1,4,1);
        graph.addEdges(3,7,1);
        graph.addEdges(4,7,1);
        graph.addEdges(2,5,1);
        graph.addEdges(2,6,1);
        graph.addEdges(5,6,1);

        graph.showGraph();
        System.out.println(graph.getNumOfEdges());

        graph.BFS(0); // 1 2 3 4 5 6 7 8

        graph.DFS(0); // 1 2 4 8 5 3 6 7
    }

    // 广度优先遍历
    public void BFS(int start){
        isVisit[start] = true;
        Queue<String> queue = new LinkedList<>();
        queue.offer(vertexList.get(start));

        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisit[i] && adjacencyMatrix[start][i] != 1){
                i = 0;
                start++;
                if (i == vertexList.size() - 1) break;
            }else if (!isVisit[i] && adjacencyMatrix[start][i] == 1){
                isVisit[i] = true;
                queue.offer(vertexList.get(i));
            }
        }

        for (String s : queue) {
            System.out.print(s + " ");
        }
    }

    // 深度优先遍历
    public void DFS(int start){
        isVisit[start] = true;
        System.out.print(vertexList.get(start) + " ");
        for (int i = 0; i < vertexList.size(); i++) {
            // 判断是否遍历过，和是否相连
            if (!isVisit[i] && adjacencyMatrix[start][i] == 1) {
                DFS(i);
            }
        }
    }

    public boolean instanceOf(String target){
        boolean flag = false;
        for (String s : vertexList) {
            if (target.equals(s)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isTarget(String target,int i){
        return target.equals(vertexList.get(i));
    }

    /**
     * @param n 顶点个数
     */
    public Graph(int n){
        this.adjacencyMatrix = new int[n][n];
        this.numOfEdges = 0;
        this.vertexList = new ArrayList<>(n);
        this.isVisit = new boolean[n];
    }

    public void showGraph(){
        for (int[] matrix : adjacencyMatrix) {
            for (int m : matrix) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    // 得到边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }

    // 得到顶点个数
    public int getVertexNum(){
        return vertexList.size();
    }

    // 通过下标获得数据
    public String getVertex(int i){
        if (i > getVertexNum()){
            System.out.println("错误");
            return null;
        }else {
            return vertexList.get(i);
        }
    }

    /**
     * 添加顶点
     * @param vertex 顶点的数值
     */
    public void addVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param x 添加顶点的下标
     * @param y 添加顶点的下标
     * @param num 将边置为1
     */
    public void addEdges(int x,int y,int num){
        if (num == 1 && adjacencyMatrix[x][y] == 0) numOfEdges++;
        else if (num == 0 && adjacencyMatrix[x][y] == 1) numOfEdges--;
        else return;
        adjacencyMatrix[x][y] = num;
        adjacencyMatrix[y][x] = num;
    }
}
