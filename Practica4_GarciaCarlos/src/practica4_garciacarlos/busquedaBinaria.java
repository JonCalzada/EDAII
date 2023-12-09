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
public class busquedaBinaria {
    public static void verdaderoBinario(int [] arr, int  c){
        int existe=0;
        int m;
        int izq=1;
        int der=arr.length;
        while(izq<=der){
            m=(izq + der)/2;
            if(c==arr[m]){
               existe=1 ;
               break;
            }
            if(c<arr[m]){
                der=m-1;
            }
            if(c>arr[m]){
                izq=m+1;
            }
        }
        if(existe==1)
            System.out.println("Verdadero");
        else
            System.out.println("Falso");
        
    }
    public static void vecesBinario(int [] arr, int  c){
        int contador=0;
        int m;
        int izq=1;
        int der=arr.length;
        while(izq<=der){
            m=(izq + der)/2;
            if(c==arr[m]){
               while(c==arr[m]){
                m=m-1;
                }
                while(c==arr[m]){
                    contador=contador+1;
                }
                System.out.println("Veces que aparece:"+contador);
                break;
            }
            if(c<arr[m]){
                der=m-1;
            }
            if(c>arr[m]){
                izq=m+1;
            }
        }
    }
}
