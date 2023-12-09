/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6calzadajonathan;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author edaII05alu09
 */
public class Graph1 {
    int V;
    LinkedList<Integer> adjArray[];

    Graph1(int v){
            V=v;
            adjArray = new LinkedList[v];
             for(int i=0; i<v;++i)
                 adjArray[i] = new LinkedList();

    }
    
        void addEdge(int v,int w){
            adjArray[v].add(w);
            adjArray[w].add(v);
        }
        void addDir (int s, int r){
        adjArray[s].add(r);
      //  adjArray[s].add(r);
        }
        
        
    void printGraph(Graph1 graph){
        for(int  v=0; v<graph.V; v++){

            System.out.println("Lista de adyacencia del vertice "+ v);
            System.out.println(v);
            for(Integer node: graph.adjArray[v]){
                System.out.println("--> "+node);
            }
            System.out.println("\n");
    }
    }


    
    
 
void BFS (int s){
boolean visited[]= new boolean[V];
LinkedList<Integer> queue = new LinkedList<>();

visited[s]= true;
queue.add(s);
while (!queue.isEmpty()){
s= queue.poll();
System.out.print(s+" ");

Iterator<Integer> i = adjArray[s].listIterator();
while (i.hasNext()){
int n=i.next();
if (!visited[n]){
visited[n]=true;
queue.add(n);
}
}

}
}

     void addDir(int i, int i0, int i1) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
}