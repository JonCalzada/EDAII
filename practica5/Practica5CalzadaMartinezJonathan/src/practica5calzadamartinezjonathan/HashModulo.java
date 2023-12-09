/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author alumno
 */
public class HashModulo {
 String [] arreglo;
     int tamanio, contador;
     
     public HashModulo(int tam){
     tamanio=tam;
     arreglo = new String[tam];
     Arrays.fill(arreglo,"-1");
     }
     public void funcionhash(String [] cadenaArreglo,String[] arreglo){
          for (int i=0;i<cadenaArreglo.length;i++){
               String elemento = cadenaArreglo[i];
               int indiceArreglo=Integer.parseInt(elemento)%7;
               System.out.println("El indice es:"+indiceArreglo+"para el Elemento o valor"+elemento);
              // Tratando las colisiones
               while(arreglo [indiceArreglo]!="-1"){
               indiceArreglo++;
               System.out.println("Ocurrio una colision en el indice "+(indiceArreglo-1)+"Cambiar al indice"+indiceArreglo);
               indiceArreglo%=tamanio;
               }
                       arreglo[indiceArreglo]=elemento;
          }
     
     }
     // Metodo para mostrar la tabla 
     public void mostrar (){
     int incremento=0,i,j;
     for(i=0;i<1;i++){
     incremento +=20;
     for(j=0;j<71;j++){
          System.out.print("-");
     }
          System.out.println("");
          for (j=incremento-20;j<incremento;j++){
               System.out.format("|%3s"+"",j);
          }
          System.out.print("|");
          for(int n=0;n<71;n++){
               System.out.print("_");
     }
          //
          System.out.println();
          for(j=incremento-20;j<incremento;j++){
          if (arreglo[j].equals("-1")){
               System.out.println("|");
          }else{
                  System.out.print(String.format("|%3s",arreglo[j]));
                  }      
          }
          System.out.println("|");
          for(j=0;j<71;j++){
               System.out.print("-");
          }
          System.out.println();
     }
             }
     
     
     // Método para buscar clave
     public String buscarClave(String elemento){
          //System.out.println("El buscado es esto:->  "+elemento);
          int numEntero = Integer.parseInt(elemento);
          System.out.println("Este element: "+numEntero);
          int indiceArreglo = Integer.parseInt(elemento)%7;
          System.out.println("Indice arreglo:->  "+indiceArreglo);
          int contador=0;
         // System.out.println("arreglo[indiceArreglo]"+arreglo[indiceArreglo]);
          while(arreglo[indiceArreglo]!="-1"){
            System.out.println("arreglo[indiceArreglo] 1  :"+arreglo[indiceArreglo]);
            System.out.println("                       2  :"+elemento);
               if(arreglo[indiceArreglo].equals(elemento))
               {
                    System.out.println("El elemento"+elemento+" fue encontrado en el indice"+indiceArreglo);
                    return arreglo[indiceArreglo];
               }else{
               indiceArreglo++;
               indiceArreglo%=tamanio;
                         System.out.println("Indice arreglo x2:->  "+indiceArreglo);

               contador++;
               }
               if(contador>20){
                    break;
                     }
               
          }
     return null;
     }
     
     
     public static void main(String[] args) {
          // TODO code application logic here
         
          Scanner teclado = new Scanner(System.in);
          HashModulo hash= new HashModulo(20);
          String nombre,buscado;
          String[] elementos={"2","3","7","0","0","0","0","0","0","0","0","0","0","63","25","67","42","92","10","97"};
          hash.mostrar();
           elementos[7]="333";  
           hash.funcionhash(elementos, hash.arreglo);
          hash.mostrar();
          //System.out.println("Ingrese :");
          //nombre = teclado.nextLine();
         // elementos[0]="333";
          //System.out.println("arreglo"+elementos[0]);
          //hash.mostrar();
          
          System.out.println("Deme la clave:");
          nombre = teclado.nextLine();
          buscado=hash.buscarClave(nombre);
          System.out.println(buscado);
          if(buscado==null){
               System.out.println("El elemento"+nombre+" no se encuentra en la tabla");
          }
     }
}