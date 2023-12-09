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
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.Writer;
/**
 *
 * @author Jonathan
 */

//import static practica10calzadmartinez.Practica10CalzadMartinez.sc;


public class Practica10CalzadMartinez {
     
 static Scanner sc = new Scanner (System.in);

     static String nom(){
        //sc.nextLine();
        System.out.print("Ingresa el nombre del archivo (sin extension): ");
        String name = sc.nextLine();
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
          System.out.println("|1). Creacion de un archivo        |");
          System.out.println("|2). Sobreescritura de archivo     |");
          System.out.println("|3). Edicion de archivo            |");
          System.out.println("|4). Eliminación de archivo        |");
          System.out.println("|5). Salir                         |");
          System.out.println("-----------------------------------");
          op = sc.nextInt ();
          switch(op){
               case 1: // crear un archivo
                     Practica10CalzadMartinez crea = new Practica10CalzadMartinez();
                     direccion = direcc();
                     nombre =nom();
               
                     crea.crearArchivo(direccion, nombre);
                       break;
               case 2:// sobre-escribir un archivo
                    Practica10CalzadMartinez w = new Practica10CalzadMartinez();
                    direccion = direcc();
                     nombre =nom();
                                     
                  w.sobreEscribir(direccion,nombre);
              
                
                       break;


               case 3://Editar un archivo
                    Practica10CalzadMartinez b = new Practica10CalzadMartinez();
                    // File archis =new File(direccion+"\\"+nombre+".txt");
                    direccion = direcc();
                     nombre =nom();
                    b.editar( direccion, nombre);
                    break; 
                    case 4:// Eliminar un archivo
           Practica10CalzadMartinez d = new Practica10CalzadMartinez();
                 direccion = direcc();
                    nombre =nom();
                         d.borrar(direccion,nombre);
                         break;

               case 5: // salir
                    s=2;
                    break;
          }
          }while(s==4);
     }

 void crearArchivo(String direccion,String nombre) throws IOException{
 File archivoo =new File(direccion+"\\"+nombre+".txt");
                    System.out.println("Este es el archivo"+archivoo);
                    FileWriter archivo = new FileWriter (archivoo);
                    System.out.println(" ESCRIBA EL CONTENIDO");
                  String   cont = sc.next();
                    archivo.write(cont);
                    archivo.close();
                    System.out.println(direccion);
                    
                   File af = new File(nombre);
                         if (af.exists()) { 
                           // etcétera  
                             
                         }else{
                              System.out.println(" El archivo se creo correctamente");
                         }
 }    
     
     
void borrar (String direc,String nombre){

var sfichero = new File(direc+"\\"+nombre+".txt");
                               if (!sfichero.exists()) {
                               System.out.println("El archivo data no existe.");
                                } else {
                                sfichero.delete();
                                   System.out.println("El archivo data fue eliminado.");
                                     }
}

void editar(String direc,String nombre) throws IOException{
var archis = new File(direc+"\\"+nombre+".txt");
                     if (!archis.exists()) {
                               System.out.println("El archivo data no existe.");
                                } else {
                              FileWriter TextOut = new FileWriter(archis, true);
                              System.out.println("Escriba lo que va a agregar");
                              String agrega = sc.next();
                                   TextOut.write(" "+agrega+"\r\n");
                                   TextOut.close();
  

 }
        }




void sobreEscribir(String direccion,String nombre) throws IOException{
 File archi =new File(direccion+"\\"+nombre+".txt");
                
                if (!archi.exists()) {
                               System.out.println("El archivo data no existe.");
                                } else {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archi));
                    System.out.println(" ESCRIBA EL CONTENIDO");
                   String  cont = sc.next();
                    bw.write(cont);
                    bw.close();   

}


}


}
