/*
 * Paquetes manejo de Archivos
 *  java.io.File;
 *  java.io.PrintWriter;
 *  java.io.BufferedReader;
 *  java.io.BufferedWriter;
 *  java.io.FileReader;
 *  java.io.FileWriter;
 */
package practica10hernandezjimcarlos;

import java.io.IOException;
import java.util.Scanner;

public class Practica10HernandezJimCarlos {

    static Scanner sc = new Scanner(System.in);
    static Archivos archivo = new Archivos(); //PAra hacer uso de la clase donde se manejan los archivos.
    static Origen or = new Origen(); //Para hacer uso de la clase donde se buscara la ruta que se necesita
    static String rutaOr; //guarda la ruta buscada.

    public static void main(String[] args) throws IOException {
        boolean salir = false; // Si no se selecciona la opcion 5, se mantiene en "false"
        do {
            int op = menu(); //Se muestra el menu y se guarda la respuesta del usuario.
            sc.nextLine();  //Limpiando el buffer...
            String name;
            switch (op) {
                case 1: //Crear un archivo
                    System.out.println("Ingrese la carpeta donde desea crear el archivo");
                    System.out.print("Si la carpeta no existe el archivo se creara en el \'Escritorio\': ");
                    rutaOr = or.Carpeta(sc.nextLine()); //Se busca y se guarda la ruta de la carpeta.
                    System.out.print("Ingrese el nombre para el nuevo archivo (sin extension): ");
                    archivo.ArchivosU(sc.nextLine(), false, 1, rutaOr); //se manda lo necesario para poder crear el archivo.
                    break;
                case 2: //Sobreescribir un archivo.
                    System.out.print("Ingrese el nombre del archivo a sobreescribir (sin extension): ");
                    name = sc.nextLine();
                    rutaOr = or.Txt(name);  //Se busca y se guarda la ruta del archivo.
                    if (rutaOr != null) {   //Si se encontro el archivo se manda lo necesario para poder trabajarlo
                        archivo.ArchivosU(name, false, 2, rutaOr);
                    } else {    //Si no se encontró, se muestra un mensaje
                        System.out.println("El archivo no existe.");
                    }
                    break;
                case 3: //Editar un archivo, lleva la misma logica que el anterior.
                    System.out.print("Ingrese el nombre del archivo a editar (sin extension): ");
                    name = sc.nextLine();
                    rutaOr = or.Txt(name);
                    if (rutaOr != null) {
                        archivo.ArchivosU(name, true, 3, rutaOr);
                    } else {
                        System.out.println("El archvo no existe.");
                    }
                    break;
                case 4: //Eliminar archivo, lleva la misma logica que los anteriores.
                    System.out.print("Ingrese el nombre del archivo a eliminar (sin extension): ");
                    name = sc.nextLine();
                    rutaOr = or.Txt(name);
                    if (rutaOr != null) {
                        archivo.ArchivosU(name, false, 4, rutaOr);
                    } else {
                        System.out.println("El archivo no existe.");
                    }
                    break;
                case 5: //Salir
                    salir = true;   //Se cambia el booleano a "true" para terminar el ciclo.
                    break;
                default: //Los demás casos...  se muestra un mensaje.
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (!salir);
    }

    static int menu() { //Muestra el menu y regresa la opcion seleccionada.
        System.out.println("***********Menu***********");
        System.out.println("*1) Crear Archivo        *");
        System.out.println("*2) Sobreescribir Archivo*");
        System.out.println("*3) Editar Archivo       *");
        System.out.println("*4) Eliminar Archivo     *");
        System.out.println("*5) Salir                *");
        System.out.println("**************************");
        System.out.print("Opcion:  ");
        return sc.nextInt();
    }
}
