/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BusquedaLineal {

    public static void main(String[] args) {
        // TODO code application logic here
          Scanner sc = new Scanner(System.in);
          int elementBuscar, posicion;
          int[] arr = {2, 6, 7, 27, 38, 98, 105, 24, 37, 1399};
          System.out.println("Elemento a buscar: ");
          elementBuscar = sc.nextInt();
          posicion = busquedaSecuencial(arr, elementBuscar);
          System.out.println("El elemento se encuentra en la posición:" +posicion);
    }
    
    public static int busquedaSecuencial(int[] array, int x) {
        int posicion = -1;
        for (int i = 0; i < array.length; i++) { //Recorre todo el arreglo
            //Pregunta si un numero del arreglo es igual a x 
            if (array[i] == x) {
               array.contains(x);
                posicion = i;
                break;
            }
        }
        return posicion;
    }
}