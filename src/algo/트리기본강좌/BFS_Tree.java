package algo.트리기본강좌;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS_Tree {
    public static void main(String[] args) throws Exception {
        Graph g = new Graph(9);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,4);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(5,6);
        g.addEdge(5,7);
        g.addEdge(6,8);

//        System.out.println("dfs");
//        g.dfs();
//        System.out.println("bfs");
//        g.bfs();
//        System.out.println("dfs");
//        g.dfs(3);
//        System.out.println("bfs");
//        g.bfs(3);
//        System.out.println("dfsR");
//        g.dfsR();
        System.out.println("dfsR");
        g.dfsR(3);
    }
}

class Graph{
    class Node{
        int data;
        LinkedList<Node> child;
        boolean visited;
        Node(int data){
            this.data = data;
            this.visited = false;
            child = new LinkedList<>();
        }
    }
    Node[] nodes;
    Graph(int size){
        nodes = new Node[size];
        for(int i=0; i<size; i++){
            nodes[i] = new Node(i);
        }
    }
    void addEdge(int i1, int i2){
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if(!n1.child.contains(n2)){
            n1.child.add(n2);
        }
        if(!n2.child.contains(n1)){
            n2.child.add(n1);
        }
    }
    void dfs(){
        dfs(0);
    }
    void dfs(int index){
        Node root = nodes[index];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.visited = true;
        while(!stack.isEmpty()){
            Node r = stack.pop();
            for(Node n : r.child){
                if(n.visited == false){
                    n.visited = true;
                    stack.push(n);
                }
            }
            visit(r);
        }
    }
    void bfs(){
        bfs(0);
    }
    void bfs(int index){
        Node root = nodes[index];
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        root.visited = true;
        while(!queue.isEmpty()){
            Node r = queue.poll();
            for(Node n : r.child){
                if(n.visited == false) {
                    n.visited = true;
                    queue.add(n);
                }
            }
            visit(r);
        }
    }
    void dfsR(Node r){
        if(r == null) return;
        r.visited = true;
        visit(r);
        for(Node n : r.child){
            if(n.visited == false){
                dfsR(n);
            }
        }
    }
    void dfsR(int index){
        Node r = nodes[index];
        dfsR(r);
    }
    void dfsR(){
        dfsR(0);
    }
    void visit(Node n){
        System.out.print(n.data + " ");
    }
}