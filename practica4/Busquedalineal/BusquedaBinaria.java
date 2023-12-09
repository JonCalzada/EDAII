import java.util.Scanner;

public class BusquedaBinaria 
{
    public static int busquedaBinaria(int[] Arreglo, int elemento)
    {
        int i = 0,cont = 0, centro = 0, posicion = 0, inferior = 0, superior = Arreglo.length-1;
        
        while(inferior <= superior)
        {
            centro = (superior + inferior) / 2;
              
              if (Arreglo[centro] == elemento){
               cont = cont+1;
               System.out.println("VERDADERO, LA CLAVE SE ENCUENTRA");
            System.out.println("cantidad de veces "+cont);
                 return centro;
                  }
              else
                 if (Arreglo[centro] > elemento)
                       superior = centro - 1;
                 else
                       inferior = centro + 1;
        }
         
          return -1;
    }
    
    
    
    
    public static void main (String[] args) 
    {
        java.util.Scanner Leer = new java.util.Scanner(System.in);
    
            
            
            Scanner sc = new Scanner(System.in);
          int Buscar, posicion;
          int[] Arreglo = {2, 6, 7, 27, 38, 98,98,98, 105, 24, 37, 1399};
          
          System.out.println("Elemento a buscar: ");
          Buscar = sc.nextInt();
          posicion = busquedaBinaria(Arreglo, Buscar);
          System.out.println("El elemento se encuentra en la posición:" +posicion);
            //////////////////////////////////////////////////////////
        
        System.out.print("Elemento a buscar: ");
        int elemento = Leer.nextInt();
        
         posicion = busquedaBinaria(Arreglo, elemento);
        
        if(posicion == -1)
            System.out.println("\nElemento no encontrado");
        else
            System.out.println("\nElemento " + elemento + " encontrado en la posición " + posicion);
    }
}