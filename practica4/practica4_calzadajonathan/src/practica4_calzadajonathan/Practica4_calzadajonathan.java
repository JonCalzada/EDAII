/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica4_calzadajonathan;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class Practica4_calzadajonathan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List <Integer> lista1= new LinkedList<>();
        lista1.add(15);
        lista1.add(20);
        lista1.add(60);
        lista1.add(50);
        lista1.add(80);
        System.out.println("Estado punto 1");
        imprimirLista(lista1);
        System.out.println("***");
        
        lista1.add(1,300);
        lista1.add(3,500);
        lista1.add(5,700);
        
        System.out.println("Estado punto 2 ");
        imprimirLista(lista1);
        System.out.println("***");
        
        lista1.set(0,4);
        lista1.set(2,6);
        lista1.set(7,8);
        //Borrar un elemento de la lista
        lista1.remove(2);
    //
    
    // Se utilizo un a condicion para saber si la lista esta o no vacia
   
        System.out.println(lista1.contains(10));
         System.out.println("lista1.indexof(59)");
         System.out.println("Estado punto 3");
         imprimirLista(lista1);
         System.out.println("***");
         
         List<Integer>lista2;
         lista2=lista1.subList(3,6);
         imprimirLista(lista2);
         System.out.println("****");
         System.out.println(lista1.equals(lista2));
         System.out.println("////////////////////////////");
        
         System.out.println(lista1);
         lista1.remove(2);
         System.out.println(lista1);

         // recorrer la lista y obtener el inesimo elemento
         //lista.contains("2");
         
          
         System.out.println(lista1.contains(300));
        //System.out.println(lista1.indexOf(59));
         
         if (!lista1.isEmpty()){
         System.out.println("La lista no esta vacia");
    }else{
         System.out.println("La lista esta vacia");
    }
         System.out.println("////////////////////////////");   
    }
    
    public static void imprimirLista (List<Integer>listaPrint){
        // for(Integer var:listaPrint){
              System.out.println(listaPrint);
         //}
        
    }
    
}
