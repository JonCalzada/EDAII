/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4_garciacarlos;

import java.util.List;

/**
 *
 * @author Carlos
 */
public class busquedaLineal {
    public static void existe(List<Integer> arr, Integer b){
        int son=0;
        for( Integer comp:arr){
            if(comp.equals(b)){
                son=1;
            }
        }
        if(son==1)
                System.out.println("Verdadero");
        else
                System.out.println("Falso");
    }
    public static void indice(List<Integer> arr,Integer a){
        int b=0;
        for( Integer comp:arr){
            if(comp.equals(a)){
                System.out.println("Se encuentra en el indice:"+ arr.indexOf(a));
                b=1;
            }
        }
        if(b==0)
            System.out.println("-1");//lo devulve si no se encuentra el dato en la lista
    }
    public static void veces(List<Integer> arr, Integer c){
    int contador=0;
        for( Integer comp:arr){
            if(comp.equals(c)){
                contador=contador + 1;
            }
        }
         System.out.println("Se encuentra:"+ contador + " veces");
    }
}
