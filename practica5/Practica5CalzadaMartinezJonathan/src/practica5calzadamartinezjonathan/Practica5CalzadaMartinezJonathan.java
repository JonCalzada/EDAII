/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5calzadamartinezjonathan;

/**
 *
 * @author alumno
 */
import java.util.Scanner;
public class Practica5CalzadaMartinezJonathan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner (System.in);
         System.out.println("**** Menu***");
         System.out.println("1) Funcion hash por modulo            ");
         System.out.println("2) Encadenamiento                     ");
         System.out.println("3) Manejor de tablas hash por modulo  ");
         System.out.println("                                      ");
         
         int op = sc.nextInt();
         switch(op)
         {
              case 1:
                   
                   System.out.println(" **** MENU *******   ");
                   System.out.println(" 1) Agregar elementos");
                   System.out.println(" 2)imprimir lista    ");
                   System.out.println(" 3) Buscar elementos  ");
                   System.out.println("                     ");
                   int op1 = sc.nextInt();
                   op1 = sc.nextInt();
                   switch (op1){
                        case 1:// agregar elementos
                             
                             break;
                        case 2: // imprimir lista
                             break;
                        case 3:buscar elementos
                             break;
                        default:
                             break;
                              }
                   
                   break;
              case 2:
                   break;
              case 3:
                   break;
              default:
                   break;
                              
         }
         
    }
    
}
