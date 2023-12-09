package practica7calzadajonathan;
import java.util.LinkedList;

public class graph2 {
    int v;
    LinkedList<Integer> adjArray[][];
    
    graph2(int V){
        v=V;
        adjArray=new LinkedList[V][V];
        for(int i=0;i<V;i++)
            for(int j=0;j<V;++j)
                adjArray[i][j]=new LinkedList();
    }
    void addEdge(int V, int W, int valor){
        adjArray[V][W].add(valor);
        //adjArray[W][V].add(valor);
        //adjArray[V][V].add(0);
        //adjArray[W].add(V);
    }
    void printGraph(graph2 graph){
        for (int V=0;V<graph.v;V++){
            System.out.println("Lista de Adyacencia del vertice "+V);
            //System.out.println(V);
            for(int J=0;J<graph.v;J++)
                for(Integer node: graph.adjArray[V][J]){
                    if(node!=0) System.out.println(V+"--- (" +node+")--->"+J);
                }
            System.out.println("\n");
        }
    }
    void BFS(int s,graph2 graph){
        boolean visited[]=new boolean[v];
        
        LinkedList<Integer> queue=new LinkedList<>();
        
        visited[s]=true;
        queue.add(s);
        
        while(!queue.isEmpty()){
            s=queue.poll();
            System.out.print(s+" ");
            for(int J=0;J<graph.v;J++){
                for (Integer node : graph.adjArray[s][J]) {
                    if(!visited[J] && node!=0) {
                        visited[J]=true;
                        queue.add(J);
                    }
                }
            }
        }
    }
}