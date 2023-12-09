
import java.util.Enumeration;
import javax.swing.JOptionPane;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeSet;
import java.util.Scanner;

public class Proyecto1poo {

    public static void main(String[] args) {
        int option = 0, opc, tam;
        String elemento;
        String nombre;
        String[] array;

        //Esto devuelve un string no se puede hacer y se debe transfromar a un entero
        option = 0;

        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null,
                         "2.Almacen\n"
                        + "3.Salir\n"
                        + "Elige una opcion...", "Menu de Colecciones en Java", JOptionPane.QUESTION_MESSAGE));

                switch (option) {

                    case 1:
                        Lista listita = new Lista(); //Creamos un objeto

                        int opcion = 0;
                        String el; // el es para elemento
                        do {
                            try { //Try catch es por si tenemos errores
                                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                                        "1.Agregar un jugador al inicio de la lista\n2.Agregar un jugador al final de la lista\n"
                                        + "3.Mostrar los Datos de los jugadores\n"
                                        + "4.Eliminar un jugador del inicio de la lista\n"
                                        + "5.Eliminar un jugador al final de la lista\n"
                                        + "6.Buscar un jugador en la lista\n"
                                        + "7.Salir", "Menu de Opciones", 3)); // 3 de question
                                switch (opcion) {

                                    case 1:
                                        try {
                                            el = JOptionPane.showInputDialog(null, "Ingresa el jugador:",
                                                    "Insertando al Inicio", 3);

                                            //Agregando al Nodo
                                            listita.agregarAlInicio(el);//Recibe de parametro al elemento
                                        } catch (NumberFormatException n) {//Para validar que nos introduzcan un dato
                                            JOptionPane.showMessageDialog(null, "Error " + n.getMessage());
                                        }
                                        break;
                                    case 2:
                                        try {
                                            el = JOptionPane.showInputDialog(null, "Ingresa el jugador:",
                                                    "Insertando al Final", 3); //3 es de question

                                            //Agregando al Nodo
                                            listita.agregarAlFinal(el);//Recibe de parametro al elemento
                                        } catch (NumberFormatException n) {//Para validar que nos introduzcan un dato
                                            JOptionPane.showMessageDialog(null, "Error " + n.getMessage());
                                        }
                                        break;
                                    case 3:
                                        listita.mostrarLista();
                                        break;
                                    case 4:
                                        el = listita.borrarDelInicio();//Ponemos el porque sabemos que nos retornara un elemento
                                        JOptionPane.showMessageDialog(null, "El jugador eliminado es " + el,
                                                "Eliminando nodo del inicio", JOptionPane.INFORMATION_MESSAGE
                                        );
                                        break;
                                    case 5:
                                        el = listita.borrarDelFinal();//Ponemos el porque sabemos que nos retornara un elemento
                                        JOptionPane.showMessageDialog(null, "El Elemento eliminado es " + el,
                                                "Eliminando nodo del final", JOptionPane.INFORMATION_MESSAGE
                                        );
                                        break;
                                    
                                       
                                    case 6:
                                        el = JOptionPane.showInputDialog(null, "Ingresa el "
                                                + "jugador a buscar....", "Buscando nodos en la lista",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        if (listita.estaEnLaLista(el) == true) {
                                            JOptionPane.showMessageDialog(null, "El jugador " + el + " ,Si esta en la lista",
                                                    "Nodo encontrado", JOptionPane.INFORMATION_MESSAGE);

                                        } else {
                                            JOptionPane.showMessageDialog(null, "El jugador " + el + ",No esta en la lista",
                                                    "Nodo no encontrado", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;

                                    case 7:
                                        JOptionPane.showMessageDialog(null, "Programa Finalizado ");
                                        break;

                                    default:
                                        JOptionPane.showMessageDialog(null, "Opcion Incrrecta ");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
                            }

                        } while (opcion != 7); //Mientras no este en nuestras opciones

                 /*   case 0:
                        int menu;
                        menu = 0;
                        String name,
                         name1,
                         name2,
                         name4,
                         pos;
                        int num;
                        Jugador player;
                        Scanner leer = new Scanner(System.in);
                        TreeSet<Jugador> trest = new TreeSet<>();/*El tree set almacena objetos de tipo jugador
                        do {
                            try { //Try catch es por si tenemos errores
                                menu = Integer.parseInt(JOptionPane.showInputDialog(null,
                                        "1.Anadir un jugador Set\n2.Remover del Set\n"
                                        + "3.Existencia del elemento\n"
                                        + "4.Limpiar el Set(Borrar)\n"
                                        + "5.Elimina el primer Jugador\n"
                                        + "6.Eliminar el ultimo Jugador\n"
                                        + "7.Esta vacia\n"
                                        + "8.Imprimir todo el Set\n"
                                        + "9.Tamanio del Set\n"
                                        + "10.Salir", "Menu de Opciones", 3)); // 3 de question
                                switch (menu) {

                                    case 1:
                                        System.out.println("Ingresa el nombre del jugador");
                                        name = leer.next();
                                        System.out.println("Ingresa la posicion del jugador");
                                        pos = leer.next();
                                        System.out.println("Ingresa el numero del jugador");
                                        num = leer.nextInt();
                                        Jugador jug = new Jugador(name, pos, num);
                                        System.out.println(trest.add(jug));
                                        break;
                                    case 2:
                                        player = Jugador.opcionJugador();
                                        trest.remove(player);
                                        break;
                                    case 3:
                                        player = Jugador.opcionJugador();
                                        System.out.println(trest.contains(player));

                                        break;
                                    case 4:

                                        trest.clear();
                                        break;
                                    case 5:
                                        trest.pollFirst();
                                        break;
                                    case 6:
                                        trest.pollLast();

                                        break;
                                    case 7:
                                        System.out.println(trest.isEmpty());

                                        break;
                                    case 8:
                                        for (Jugador juga : trest) {
                                            System.out.println(juga.dorsal + "    " + juga.name + "    " + juga.posicion);
                                        }
                                        break;
                                    case 9:
                                        System.out.println(trest.size());

                                        break;

                                    case 10:
                                        JOptionPane.showMessageDialog(null, "Programa Finalizado ");
                                        break;

                                    default:
                                        JOptionPane.showMessageDialog(null, "Opcion Incrrecta ");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
                            }

                        } while (menu != 10); //Mientras no este en nuestras opciones

                        break;*/
                    case 2:
                        Scanner scan = new Scanner(System.in);

                        Hashtable<String, String> TablaLibros = new Hashtable<String, String>();
                        //Aca se crea la tabla de HashTable con parametros String,String

                        int claveLibros = 1002; //Se usa de esta variable como contador para crear claves de forma continua.
                         double precio =00.0;
                         String clave,titulo,tituloIngresado,claveIngresada;
                         

                        TablaLibros.put("1000", "Harry_Potter");
                        TablaLibros.put("1001", "Moby_Dick");
                        TablaLibros.put("1002", "El_Principito"); //Se agregan 3 libros iniciales a la tabla "TablaLibros"

                        int opcione;
                        opcione = 0;
                        do {
                            try { //Try catch es por si tenemos errores
                                opcione = Integer.parseInt(JOptionPane.showInputDialog(null,
                                        "1.Ver Libros\n2.Agregar Libros\n"
                                        + "3.Buscar un Libro\n"
                                        + "4.Salir", "Menu de Opciones", 3)); // 3 de question
                                switch (opcione) {

                                    case 1:
                                        Enumeration<String> claves = TablaLibros.keys(); //Variable de enumeracion para los valores llave de la tabla
                                        while (claves.hasMoreElements()) {	//El ciclo continua mientras existan mas llaves de tipo "Clave"
                                            clave = claves.nextElement();	//Se asigna como valor de "clave" el siguiente elemento de la tabla hash.
                                            titulo = TablaLibros.get(clave);	//Se obtiene el valor asignado a la llave de la posicion actual
                                            System.out.println("Clave : " + clave + "\tTitulo : " + titulo);
                                        }
                                        System.out.println();

                                        break;
                                    case 2:
                                        System.out.println("Cual sera el titulo del libro? (En vez de usar espacio vacio use '_') ");
                                        //Si se usan espacios el programa no funciona correctamente
                                        tituloIngresado = scan.next();
                                        claveLibros++; //Avanza la variable de claveLibros para nunca repetir clave Hash
                                        TablaLibros.put(Integer.toString(claveLibros), tituloIngresado);
                                        //Se asignan dos valores de tipo "String", uno como la clave automatica y el otro como el titulo del libro ingresado
                                        System.out.println();

                                        break;
                                    case 3:
                                        System.out.println("Ingrese la clave del libro para verificar si se encuentra en el sistema.");
                                        claveIngresada = scan.next(); //Variable de tipo String recibida que es la clave
                                        if (TablaLibros.containsKey(claveIngresada) == true) {
                                            //Se revisa en toda la tabla Hash si la clave se encuentra dentro. 
                                            System.out.println("El libro si se encuentra en el sistema. Es '" + TablaLibros.get(claveIngresada) + "'.");
                                            //Si la clave se encuentra, se le aclara al usuario y se busca el titulo del libro de la clave asignada.
                                        } else { //Si no se encuentra libro, se imprime la aclaracion
                                            System.out.println("No hay libro en el sistema con la clave ingresada.");
                                        }
                                        System.out.println();

                                        break;

                                    case 4:
                                        JOptionPane.showMessageDialog(null, "Programa Finalizado ");
                                        break;

                                    default:
                                        JOptionPane.showMessageDialog(null, "Opcion Incrrecta ");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
                            }

                        } while (opcione != 4); //Mientras no este en nuestras opciones

                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Ha salido con exito del programa !", "Fin", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "No se encuentra el numero");
                        break;
                }

            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Error " + n.getMessage());
            }
        } while (option != 3);
    }

}

    
    

