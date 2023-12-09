/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica9calzadamartinez;


import java.util.Scanner;
/**
 *
 * @author Jonathan
 */
public class Practica9CalzadaMartinez {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          // TODO code application logic here
    Scanner sc = new Scanner(System.in); 
       int op=0;
       int s=1;
        // TODO code application logic here
    /*  BTree nuevo = new BTree(4);
        nuevo.add(70);
        nuevo.add(50);
        nuevo.add(30);
        nuevo.add(40);
        nuevo.add(20);
        nuevo.add(80);
        nuevo.add(25);
        nuevo.add(90);
        nuevo.add(75);
        nuevo.add(10);
        nuevo.add(15);
        nuevo.mostrarArbol();
        
        System.out.println("-----------------------");
        BTree b2 = new BTree (5);
        
        b2.add(20);
        b2.add(40);
        b2.add(10);
        b2.add(30);
        b2.add(15);
        b2.add(35);
        b2.add(7);
        b2.add(26);
        b2.add(18);
        b2.add(22);
        b2.add(5);
        b2.add(42);
        b2.add(13);
        b2.add(46);
        b2.add(27);
        b2.add(8);
        b2.add(32);
        b2.add(38);
        b2.add(24);
        b2.add(43);
        b2.add(25);
        b2.mostrarArbol();
        
          System.out.println(b2.find(25));
        //b2.find(43);
    */
        System.out.println("-----------------------");
      BTree b3 = new BTree(4);
        do{
        System.out.println("----------------------");
        System.out.println("| 1). Agregar clave  |");
        System.out.println("| 2). Imprimir arbol |");
        System.out.println("| 3). Buscar Datos   |");
        System.out.println("| 4). Eliminar nodo  |");
        System.out.println("| 5). Salir          |");
        System.out.println("----------------------");
        op = sc.nextInt();
       // BTree b3 = new BTree(4);
      
        switch(op){
            case 1:// agregar claves
                 System.out.println(" De la clave ");
                int dato = sc.nextInt();
                 b3.add(dato);
                 break;
            case 2:  //imprmir el arbol
                 b3.mostrarArbol();
                 break;
            case 3: // Buscar claves
                 System.out.println("Elemento a buscar");
                 int find= sc.nextInt();
                 System.out.println(b3.find(find));
                 break;
            case 4: // eliminar dato
                 
                 System.out.println("OPCIÓN NO HABILITADA");
                 break;
            case 5:
                 s=4;
                 break;
        }
        }while(s<2);
        
    }
        }
    

