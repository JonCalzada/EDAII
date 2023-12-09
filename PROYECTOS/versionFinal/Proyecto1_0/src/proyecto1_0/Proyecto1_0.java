package proyecto1_0;
import java.io.IOException;
import java.util.Scanner;
public class Proyecto1_0 {
    static Scanner sc=new Scanner(System.in);
    
    static String Menu2(){
        sc.nextLine();
        System.out.print("Ingresa el nombre del archivo (sin extension): ");
        String name=sc.nextLine();
        return name;
    }
    
    public static void main(String[] args) throws IOException {
        int opcion=0;
        boolean salir=false;
        while(!salir){
            do{
                System.out.println("********Algoritmo deseado********");
                System.out.println("*1) Polifase\t\t\t*");
                System.out.println("*2) Mezcla Equilibrada\t\t*");
                System.out.println("*3) Distribucion (Radix) \t*");
                System.out.println("*4) Salir \t\t\t*");
                System.out.println("*********************************");
                System.out.print("Opcion: ");
                opcion=sc.nextInt();
            }while(opcion<1&&opcion>3);

            switch(opcion){
                    case 1:
                        Polifase pol= new Polifase();
                        pol.polifaseMetodo(Menu2());
                        break;
                    case 2:
                        mezclaEquilibrada me= new mezclaEquilibrada();
                        me.Mezclar(Menu2());
                        break;
                    case 3:
                        distribucion radix=new distribucion();
                        radix.DistribucionR(Menu2());
                        break;
                    case 4:
                        salir=true;
                        break;
            }
        }
    } 
}