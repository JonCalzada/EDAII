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

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) throws IOException {
          // TODO code application logic here
          Scanner sc = new Scanner (System.in);
          int s=4,op = 0;
          String  nombre = null;
          String direccion = null; 
          String cont = null;
          
          
          
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
                    
                    System.out.println(" Deme el nombre del archivo");
                    nombre = sc.next();
                    
                    System.out.println(" Deme la direccion ");
                       direccion = sc.next();
                       File archivoo =new File(direccion+"\\"+nombre+".txt");

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
                File archi =new File(direccion+"\\"+nombre+".txt");
                 
                  
              //  Escribir(archi,cont);
                BufferedWriter bw = new BufferedWriter(new FileWriter(archi));
                    bw.write("");
                    bw.close();
                    System.out.println(" ESCRIBA EL CONTENIDO");
                     cont = sc.next();
                    bw.write(cont);
              
                    System.out.println(nombre);
                
                       break;


               case 3://Editar un archivo
                   File arch =new File(direccion+"\\"+nombre+".txt");
/*
                                        try {
                            
                            File file = new File(direccion+"\\"+nombre+".txt");
                            // Si el archivo no existe, se crea!
                            if (!file.exists()) {
                                file.createNewFile();
                            }
               // flag true, indica adjuntar información al archivo.
               FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                            bw = new BufferedWriter(fw);
                            bw.write(cont);
                            System.out.println("información agregada!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                            //Cierra instancias de FileWriter y BufferedWriter
                                if (bw != null)
                                    bw.close();
                                 Object fw = null;
                                if (fw != null)
                                    fw.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        

*/
                    System.out.println(" agregar");
                   String  agregar = sc.next();
                    modificar(arch,cont,agregar);
                    break;
               case 4:// Eliminar un archivo
                  
                  var fichero = new File(direccion+"\\"+nombre+".txt");
              if (!fichero.exists()) {
                        System.out.println("El archivo data no existe.");
                         } else {
                         fichero.delete();
                            System.out.println("El archivo data fue eliminado.");
                              }
                    break;

               case 5: // salir
                    s=2;
                    break;
          }
          }while(s==4);
     } 

     
  /*   
    static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
      String cadena;
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      while((cadena = b.readLine())!=null) {
          System.out.println(cadena);
      }
      b.close();*/



 void modificar(File fAntiguo,String aCadena,String nCadena)
    {
       
        Random numaleatorio = new Random(3816L);
        String nFnuevo = fAntiguo.getParent()+"/auxiliar"+String.valueOf(Math.abs(numaleatorio.nextInt()))+".txt";


        File fNuevo= new File(nFnuevo);
        
        BufferedReader br;
        try
        {
            

            if(fAntiguo.exists())
            {
                br = new BufferedReader(new FileReader(fAntiguo));

                String linea;

                
                while((linea=br.readLine()) != null)
                {
                    if(linea.equals(aCadena))
                    {
                        Escribir(fNuevo,nCadena);

                    }
                    else
                    {
                        Escribir(fNuevo,linea);
                    }
                }

              // Cierro el buffer de lectura
                br.close();

                // Capturo el nombre del fichero antiguo
                String nAntiguo = fAntiguo.getName();
                // Borro el fichero antiguo
                borrar(fAntiguo);
                //Renombro el fichero auxiliar con el nombre del fichero antiguo
                fNuevo.renameTo(fAntiguo);




            }
            else
            {
                System.out.println("Fichero no Existe");
            }

        }catch(IOException e)
        {
            System.out.println(e);
        }}
    

    void Escribir(File fFichero,String cadena)
    {
        // Declaramos un buffer de escritura
        BufferedWriter bw;

        try
        {
            // Comprobamos si el archivo no existe y si es asi creamos uno nuevo.
         if(!fFichero.exists())
         {
             fFichero.createNewFile();
         }

           // Luego de haber creado el archivo procedemos a escribir dentro de el.
            bw = new BufferedWriter(new FileWriter(fFichero,true));
            bw.write(cadena);
            bw.close();

        }catch(Exception e)
        {
            System.out.println(e);
        }

    }
    
void borrar (File Ffichero)
    {
        try
        {
           // si el fichero existe  de ser así procedemos a borrar el archivo
            if(Ffichero.exists())
            {
                Ffichero.delete();
                System.out.println("Ficherro Borrado");
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}