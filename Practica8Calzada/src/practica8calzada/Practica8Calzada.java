/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8calzada;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author edaII05alu09
 */
public class Practica8Calzada {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolBin prueba = new ArbolBin();
        Practica8Calzada metd = new Practica8Calzada();
        ArbolBin arbol;
        Nodo n7 = new Nodo (7);
        Nodo n9 = new Nodo (9);
        Nodo n1 = new Nodo (1,n7,n9);
        Nodo n15 = new Nodo (15);
        Nodo n8 = new Nodo (8);
        Nodo n4 = new Nodo (4);
        Nodo n2 = new Nodo (2);
        Nodo n16 = new Nodo (16);
        Nodo n3 = new Nodo (3);
        
        arbol = new ArbolBin(n1);
        arbol.add(n7,n15,0);
        arbol.add(n7,n8,1);
        arbol.add(n9,n4,0);
        arbol.add(n9,n2,1);
        arbol.add(n15,n16,1);
        arbol.add(n8,n3,0);
        arbol.breadthFirst();
        
        prueba.InOrden();
        prueba.PreOrden();
        prueba.PostOrden();
        metd.menu();   
        
    }
                         
   public void menu(){  
     
        int op;
 
        
        
        Scanner lee = new Scanner(System.in);
      
        
        System.out.println("Debe ingresar la raiz");
        
        do{
            
            System.out.println("| 1) Crear Arbol Binario de 15 nodos            |");
            System.out.println("| 2) Crear Arbol Binario de Busqueda de 15 nodos|");
            System.out.println("| 3) Salir                                      |");
            System.out.println("|Seleccione una opción:                          |");
            op = Integer.parseInt(lee.next());
            switch(op){
                case 1:
               
                    break;
                case 2:
                 
                    break;
                case 3: 
                    break;
                
                default:
                    
                    break;
                
            }
        }while(op!=0);
  } 
}
