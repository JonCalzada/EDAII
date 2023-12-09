package ejercicio3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edgar
 */
public class MiHilo implements Runnable {
     Thread hilo;
    static Suma sumarray= new Suma();
    int a[];
    int resp;
    //Construye un nuevo hilo.
    MiHilo(String nombre, int nums[]){
        hilo= new Thread(this,nombre);
        a=nums;
    }
    //Un método que crea e inicia un hilo
    public static MiHilo creaEInicia (String nombre,int nums[]){
        MiHilo miHilo=new MiHilo(nombre,nums);
        miHilo.hilo.start(); //Inicia el hilo
        return miHilo;
    }
    //Punto de entrada del hilo
    public void run(){
        int sum;
        System.out.println(hilo.getName()+ " iniciando.");
        //synchronize llama a sumArray()
        synchronized (sumarray) {
            //Aquí, las llamadas a sumArray() en sumarray se sincronizan
            resp = sumarray.sumArray(a);
        }
        System.out.println("Suma para "+hilo.getName()+ " es "+resp);
        System.out.println(hilo.getName()+ " terminado.");
    }
}

