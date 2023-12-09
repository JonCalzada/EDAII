/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica10calzadmartinez;

//import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
import java.io.FileWriter;
import java.io.Writer;


public class Practica10CalzadMartinez {
     
 static Scanner sc = new Scanner (System.in);

     static String nom(){
        //sc.nextLine();
        System.out.print("Ingresa el nombre del archivo (sin extension): ");
        String name = sc.nextLine();
        sc.nextLine();
        System.out.print("Ingresa la direccion: ");
        String direc = sc.nextLine();
        return name;
    }
     
     static String direcc(){
        sc.nextLine();
        System.out.print("Ingresa la direccion: ");
        String direc = sc.nextLine();
        return direc;
    }
     
     
     public static void main(String[] args) throws IOException {
          // TODO code application logic here
       
          int s=4,op = 0;
          String nombre = null;
          String direccion = null; 
          String cont = null;
          String agreega = null;
          
          
          
         // String  = nombre,direccion,cont;
          do{
          System.out.println("-----------------------------------");
          System.out.println("|1). Creacion de un archivo  si    |");
          System.out.println("|2). Sobreescritura de archivo     |");
          System.out.println("|3). Edicion de archivo            |");
          System.out.println("|4). Eliminación de archivo    si  |");
          System.out.println("|5). Salir                         |");
          System.out.println("-----------------------------------");
          op = sc.nextInt ();
          switch(op){
               case 1: // crear un archivo
                     direccion = direcc();
                     nombre =nom();
                       File archivoo =new File(direccion+"\\"+nombre+".txt");
                    System.out.println("Este es el archivo"+archivoo);
                    FileWriter archivo = new FileWriter (archivoo);
                    System.out.println(" ESCRIBA EL CONTENIDO");
                     cont = sc.next();
                    archivo.write(cont);
                    archivo.close();
                    System.out.println(direccion);
                    
                   File af = new File(nombre);
                         if (af.exists()) { 
                           // etcétera  
                             
                         }else{
                              System.out.println(" El archivo se creo correctamente");
                         }
                       break;
               case 2:// sobre-escribir un archivo 
                    String direc = direcc();
                    String nomb =nom();
                      var sfichero = new File(direc+"\\"+nomb+".txt");
                               if (!sfichero.exists()) {
                               System.out.println("El archivo data no existe.");
                                } else {
                                   System.out.println("El archivo no existe");
                                     }
                   /*  var fichero = new File(direccion+"\\"+nombre+".txt");
                               if (!fichero.exists()) {
                               System.out.println("El archivo data no existe.");
                                } else {
                         
                                   System.out.println("El archivo data fue eliminado.");
                                     }*/
                File archi =new File(direccion+"\\"+nombre+".txt");
                
                  
              //  Escribir(archi,cont);
                BufferedWriter bw = new BufferedWriter(new FileWriter(archi));
                    System.out.println(" ESCRIBA EL CONTENIDO");
                     cont = sc.next();
                    bw.write(cont);
                    bw.close();
                       break;


               case 3://Editar un archivo
                    
                     var archis = new File(direccion+"\\"+nombre+".txt");
                    
                  
                  BufferedWriter bws = new BufferedWriter(new FileWriter(archis));
                    System.out.println(" ESCRIBA Lo que se va a agregar");
                     String agrega = sc.next();
                     
                    bws.write(cont+" "+agrega);
              
                    System.out.println(nombre);
                    bws.close();
                    break;
               
                    case 4:
                         // Eliminar un archivo
                                
                               var fichero = new File(direcc()+"\\"+nom()+".txt");
                               if (!fichero.exists()) {
                               System.out.println("El archivo data no existe.");
                                } else {
                                fichero.delete();
                                   System.out.println("El archivo fue eliminado.");
                                     }
                    break;

               case 5: // salir
                    s=2;
                    break;
               case 6:
                    System.out.println(" hey que tal");
                    System.out.println("holamnu");
                    break;
          
          }
     }while(s==4);
}
}
     
