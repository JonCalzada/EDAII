/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6calzadajonathan;

import java.util.LinkedList;

/**
 *
 * @author Jonathan
 */
public class graph2 {
       int V;
    LinkedList<Integer> adjArray[];

    graph2(int v){
            V=v;
            adjArray = new LinkedList[v];
             for(int i=0; i<v;++i)
                 adjArray[i] = new LinkedList();

    }
    
       /* void addEdge(int v,int w, int peso){
            adjArray[v].add(w);
            adjArray[w].add(v);
            adjArray[peso].add(peso); 
        }*/
        void addDir (int s, int r,  int peso){
        adjArray[s].add(r);
        adjArray[r].add(s);
        adjArray[s].add(peso);
      //  adjArray[s].add(r);
        }
        
        
    void printGraph(Graph1 graph){
        for(int  v=0; v<graph.V; v++){

            System.out.println("  PODERADO Lista de adyacencia del vertice "+ v);
            System.out.println(v);
            for(Integer node: graph.adjArray[v]){
                System.out.println("--> "+node);
            }
            System.out.println("\n");
    }
    
    }
}
