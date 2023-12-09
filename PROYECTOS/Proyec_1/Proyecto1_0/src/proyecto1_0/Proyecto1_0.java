/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_0;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author johnc
 */
public class Proyecto1_0 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    
    static Scanner sc=new Scanner(System.in);
    
    static String Menu2(){
        sc.nextLine();
        System.out.print("Ingresa el nombre del archivo (sin extension): ");
        String name=sc.nextLine();
        return name;
    }
    
    public static void main(String[] args) {
        int opcion=0;
        interno orden=new interno();
        do{
            System.out.println("******Algoritmo deseado******");
            System.out.println("*1) Polifase\t\t\t*");
            System.out.println("*2) Mezcla Equilibrada\t\t*");
            System.out.println("*3) Distribucion (Radix) \t*");
            System.out.println("*****************************");
            System.out.print("Opcion: ");
            opcion=sc.nextInt();
        }while(opcion<1&&opcion>3);
        
        switch(opcion){
                case 1:
                    Polifase pol= new Polifase();
                    pol.PolifaseArchivo(Menu2()+".txt");
                    break;
                case 2:
                    mezclaEquilibrada me= new mezclaEquilibrada();
                    break;
                case 3:
                    distribucion radix=new distribucion();
                    radix.DistribucionR(Menu2());
                    break;
                case 4:
                    break;
        }
    }
    
}
