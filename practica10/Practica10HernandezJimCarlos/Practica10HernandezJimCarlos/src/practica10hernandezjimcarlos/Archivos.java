package practica10hernandezjimcarlos;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Archivos {
    //Se recibe el nombre del archivo, "true" para editar "false" para sobreescribir,
    //la posicion y la ruta donde se encuentra el archivo.
    public void ArchivosU(String nombre, boolean edicion, int pos, String ruta) throws IOException {
        Scanner sc = new Scanner(System.in); //sirve para escanear los datos que se pediran
        File archivo;
        try {
            switch (pos) {  //Dependiendo de la opcion que se elija entrara a diferentes casos.
                case 1: //Crear archivo
                    archivo = new File(ruta + "\\" + nombre + ".txt"); //se recibe la ruta de la carpeta y se le agrega lo necesario para crear el archito.
                    if (archivo.exists()) { //si ya existe muestra un mensaje.
                        System.out.println("El archivo " + archivo.getName() + " ya existe.");
                    } else {    //Si no existe, realiza lo necesario para crearlo.
                        archivo.createNewFile();
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo, edicion)));
                        System.out.println("Ingrese el texto inicial: ");
                        pw.print(sc.nextLine());
                        pw.close();
                    }
                    break;
                case 2: // Sobreescribir un archivo.
                    archivo = new File(ruta);   //Se le asigna la ruta del archivo y se realiza lo necesario para sobreescribir.
                    PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(archivo, edicion)));
                    System.out.println("Ingrese el texto a sobreescribir.");
                    pw2.print(sc.nextLine());
                    pw2.close();
                    break;
                case 3: //Editar un archivo.
                    archivo = new File(ruta);   //Asignamos la ruta del archivo y se realiza lo necesario para editas.
                    PrintWriter pw3 = new PrintWriter(new BufferedWriter(new FileWriter(archivo, edicion)));
                    pw3.println();
                    System.out.println("Ingrese el texto que desea agregar.");
                    pw3.print(sc.nextLine());
                    pw3.close();
                    break;
                case 4: //Eliminar un archivo
                    archivo = new File(ruta);   //se le asigna la ruta del archivo.
                    if (archivo.exists()) {//Si existe se realiza lo necesario para eliminar.
                        System.out.println("El archivo \'" + archivo.getName() + "\' ha sido eliminado");
                        archivo.delete();
                    } else { //Si no existe, se muestra un mensaje en pantalla.
                        System.out.println("El archivo no existe.");
                    }
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
        }
    }
}
