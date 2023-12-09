/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7calzadajonathan;

import java.util.Iterator;

/**
 *
 * @author edaII05alu09
 */
public class Practica7CalzadaJonathan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int v =8,v2=8,v3=10,v4=13;
        
        
        Graph1 graph = new Graph1 (v);
        graph.addEdge(1,3);
        graph.addEdge(1,5);
        graph.addEdge(5,1);
        graph.addEdge(5,7);
        graph.addEdge(5,4);
        
        graph.addEdge(0,4);
        graph.addEdge(0,2);
        graph.addEdge(0,6);
        graph.addEdge(6,0);
        graph.addEdge(6,2);
        graph.addEdge(6,4);
        graph.addEdge(2,0);
        graph.addEdge(2,6);
        graph.addEdge(2,7);
        graph.addEdge(7,2);
        graph.addEdge(7,3);
        graph.addEdge(7,5);
        graph.addEdge(7,4);
        graph.addEdge(3,7);
        graph.addEdge(3,1);
        graph.addEdge(3,6);
        
        Graph1 graph2 = new Graph1 (v2);
         graph2.addEdge(6,4);
         graph2.addEdge(4,3);
         graph2.addEdge(4,5);
         graph2.addEdge(4,6);
         graph2.addEdge(5,1);
         graph2.addEdge(5,2);
         graph2.addEdge(5,4);
         graph2.addEdge(1,5);
         graph2.addEdge(1,2);
         graph2.addEdge(3,2);
         graph2.addEdge(3,2);
         graph2.addEdge(3,4);
        
        
        Graph1 graph3 = new Graph1 (v3);
         graph3.addEdge(1,8);
        graph3.addEdge(1,4);
        graph3.addEdge(1,2);
        graph3.addEdge(2,6);
        graph3.addEdge(6,3);
        graph3.addEdge(3,7);
        graph3.addEdge(4,9);
        graph3.addEdge(8,5);
        
        
        Graph1 graph4 = new Graph1 (v4);
        graph4.addEdge(5,6);
        graph4.addEdge(5,7);
        graph4.addEdge(9,7);
        graph4.addEdge(9,10);
        graph4.addEdge(3,10);
        graph4.addEdge(3,1);
        graph4.addEdge(3,6);
        graph4.addEdge(1,6);
        graph4.addEdge(1,3);
        graph4.addEdge(1,2);
        
        
        
        graph2 graph5 = new graph2 (v4);
        graph5.addEdge(5,6,2);
        graph5.addEdge(5,7,4);
        graph5.addEdge(9,7,3);
        graph5.addEdge(9,10,1);
        graph5.addEdge(3,10,3);
        graph5.addEdge(3,1,2);
        graph5.addEdge(3,6,4);
        graph5.addEdge(1,6,1);
        graph5.addEdge(1,3,2);
        graph5.addEdge(1,2,1);
        graph5.addEdge(2,6,3);
        graph5.addEdge(2,1,1);
        
       
        
        
        
       // graph.addEdge(3,4);
        //graph.addEdge(1,4);
       // graph.addEdge(1,4);
       
       
        // System.out.println(" Grafica vista en clase");
        //graph.printGraph (graph);
        
        
        System.out.println("\n DFS 1 desde vertice 1 ");
         graph.DFS(1);
         System.out.println("                ");
         System.out.println("\n DFS 2 desde vertice 1");
         graph2.DFS(1);
         System.out.println("                ");
         System.out.println("\n DFS 3 desde vertice 1");
         graph3.DFS(1);
         System.out.println("                ");
         System.out.println("\n DFS 4 desde vertice 1");
         graph4.DFS(1);      
         System.out.println("                    ");
         System.out.println("\n DFS 5 desde vertice 1");
         graph5.printGraph(graph5);
        
        
        /*
        V=5;
        Graph1 graphDir = new Graph1(V);
        graphDir.addDir(1,3);
        graphDir.addDir(1,4);
        graphDir.addDir(2,1);
        graphDir.addDir(4,1);
        graphDir.addDir(4,5);
        
        System.out.println(" Grafica Dirigida ");
        graph.printGraph (graphDir);
        
         System.out.println("\n BFS desde vertice 1 ejejmplo)");
         graph.BFS(1);
        System.out.println("\n BFS desde vertice 3 ejejmplo)");
         graph.BFS(3);
         System.out.println("\n BFS desde vertice 4 ejejmplo)");
         graph.BFS(4);
         System.out.println("\n BFS desde vertice 5 ejejmplo)");
         graph.BFS(0);
       
         
          V=5;
         graph2 ponde = new graph2(V);
         ponde.addDir(4,5,2);
         ponde.addDir(3,4,5);
        ponde.printGraph (graph);
        
        
    */
    
    
    
    }
  
   
    
}
