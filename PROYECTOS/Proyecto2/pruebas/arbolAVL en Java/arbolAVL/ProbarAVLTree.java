/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;


/**
 *
 * @author fires
 * blog: http://proyectosbeta.blogspot.com/
 */
public class ProbarAVLTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
   //canner
        // TODO code application logic here
        AVLTree arbolAVL = new AVLTree();
        int op=0;

        Integer elemento1 = new Integer("45");
        Integer elemento2 = new Integer("2");
        Integer elemento3 = new Integer("3");
        Integer elemento4 = new Integer("4");
        Integer elemento5 = new Integer("12");
        Integer elemento6 = new Integer("6");
        Integer elemento7 = new Integer("7");
        Integer elemento8 = new Integer("15");
        Integer elemento9 = new Integer("22");
        Integer elemento10 = new Integer("13");
        Integer elemento11 = new Integer("12");
         Integer elemento12 = new Integer("14");
           Integer elemento13 = new Integer("15");
         Integer elemento14 = new Integer("16");
          Integer elemento15 = new Integer("17");
         Integer elemento16 = new Integer("18");
        //Integer elemento11 = new Integer("23");

        arbolAVL.insert(elemento1);
        arbolAVL.insert(elemento2);
        arbolAVL.insert(elemento3);
        arbolAVL.insert(elemento4);
        arbolAVL.insert(elemento5);
        arbolAVL.insert(elemento6);
        arbolAVL.insert(elemento7);
        arbolAVL.insert(elemento8);
        arbolAVL.insert(elemento9);
        arbolAVL.insert(elemento10);
         arbolAVL.insert(elemento11);
         arbolAVL.insert(elemento12);
         arbolAVL.insert(elemento13);
        arbolAVL.insert(elemento14);
         arbolAVL.insert(elemento15);
         arbolAVL.insert(elemento16);
   
        arbolAVL.imprimirPorNiveles();
        System.out.println("\n");
       //rbol.find(elemento16);     
       //yem.out.println( arbol.find(elemento16));

       //reak;
    //   System.out.println(arbolAVL.find(elemento14));

    }

}
