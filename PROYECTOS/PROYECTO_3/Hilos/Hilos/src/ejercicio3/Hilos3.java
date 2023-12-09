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
public class Hilos3 {
    public static void main(String[] args) {
        int a[]={1,2,3,4,5,6,7,8,9,10,11,12,13};
        MiHilo mh1 = MiHilo.creaEInicia("#1",a);
        MiHilo mh2 = MiHilo.creaEInicia("#2",a);
        try {
            mh1.hilo.join();
            mh2.hilo.join();
        }catch (InterruptedException exc){
            System.out.println("Hilo principal interrumpido.");
        }
    }
}
