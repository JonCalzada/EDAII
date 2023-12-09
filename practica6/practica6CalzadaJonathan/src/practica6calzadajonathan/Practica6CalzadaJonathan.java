/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6calzadajonathan;

/**
 *
 * @author edaII05alu09
 */
public class Practica6CalzadaJonathan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int V;
        // TODO code application logic here
        V = 5;
        
        
        Graph1 graph = new Graph1 (V);
        graph.addEdge(1,2);
        graph.addEdge(0,4);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);              
       // graph.addEdge(3,4);
        //graph.addEdge(1,4);
       // graph.addEdge(1,4);
         System.out.println(" Grafica vista en clase");
        graph.printGraph (graph);
        
        
        
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
        
        
    }
    
}
