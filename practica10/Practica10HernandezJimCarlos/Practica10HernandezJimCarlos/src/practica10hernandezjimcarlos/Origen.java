package practica10hernandezjimcarlos;

import java.io.File;

public class Origen {

    boolean entrar = false;
    String ruta;
    //con esta "ruta" se regresa hasta la carpeta del usuario.
    File Direccion = new File("..//..//..");

    public String Carpeta(String name) { 
        buscandoCoA(Direccion, name, true);
        return this.ruta; //regresa la ruta donde se encuentra la carpeta 
    }                    // Si no existe la carpeta se regresa la ruta del "Escritorio".

    public String Txt(String name) {
        buscandoCoA(Direccion, name, false);
        return this.ruta; //Se regresa la ruta donde se encuentra el archivo .txt
    }                     //Si el archivo no existe regresa null.

    public void buscandoCoA(File direccion, String name, boolean dir) { 
        File Carpetas[] = direccion.listFiles(); // se llena el Array con los archivos y carpetas dentro de la ruta que se utiliza.
        if (Carpetas != null) { //si la ruta tiene carpetas se ingresa, si esta vacia se omite.
            if (dir) {  // el boolean dir, hace referencia a si se busca una carpeta.
                for (int i = 0; i < Carpetas.length; i++) {    //se recorren todas las carpetas
                    if (Carpetas[i].getName().compareTo(name) == 0 && Carpetas[i].isDirectory()) {  //si la carpeta coincide con la busqueda
                        System.out.println(Carpetas[i].getPath().toString());   //muestra la ruta en pantalla
                        entrar = true;  //evita que regrese la ruta del escritorio
                        ruta = Carpetas[i].getPath();   //cambia la ruta a donde se encuentra la que se buscaba
                    } else {    //en este else, si no se encuentra la carpeta deja la direccion en la del Escritorio
                        if (!entrar && (Carpetas[i].getName().compareTo("Escritorio") == 0 || Carpetas[i].getName().compareTo("Desktop") == 0)) {
                            ruta = Carpetas[i].getPath();   //modifica la ruta.
                        }
                    }
                    if (Carpetas[i].isDirectory()) {    //si el archivo actual es una carpeta entra al if
                        buscandoCoA(Carpetas[i], name, true);   //hace de forma recursiva la busqueda para entrar a las subcarpetas
                    }
                }
            } else {    //entra si se busca un archivo.
                for (int i = 0; i < Carpetas.length; i++) { //Recorre todos los archivos encontrados...
                    if (Carpetas[i].getName().compareTo(name + ".txt") == 0 && Carpetas[i].isFile()) { //si un archivo coincide con el de la busqueda entra.
                        System.out.println(Carpetas[i].getPath().toString());   //muestra la ruta en pantalla
                        entrar = true;  //evita que se asigne null a la ruta
                        ruta = Carpetas[i].getPath();   //modifica la ruta a donde se encuentra el archivo
                    } else if (!entrar) {   //si no encontro el archivo se ingresa al if.
                        ruta = null;    //cambia la ruta a null.
                    }
                    if (Carpetas[i].isDirectory()) { //si el elemento actual es una Carpeta hace de forma recursiva la busqueda.
                        buscandoCoA(Carpetas[i], name, false);
                    }
                }
            }
        }
    }
}
