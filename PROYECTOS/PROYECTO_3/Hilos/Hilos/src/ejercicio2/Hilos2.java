package ejercicio2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edgar
 */
public class Hilos2 {
        public static void main(String[] args) {
            PrioridadHilos ph1= new PrioridadHilos("Prioridad Alta");
            PrioridadHilos ph2= new PrioridadHilos("Prioridad Baja");
            PrioridadHilos ph3= new PrioridadHilos("Prioridad Normal #1");
            PrioridadHilos ph4= new PrioridadHilos("Prioridad Normal #2");
            PrioridadHilos ph5= new PrioridadHilos("Prioridad Normal #3");
            //Establecer prioridades
            ph1.hilo.setPriority(Thread.NORM_PRIORITY+4);
            ph2.hilo.setPriority(Thread.NORM_PRIORITY-4);
            ph1.hilo.start();
            ph2.hilo.start();
            ph3.hilo.start();
            ph4.hilo.start();
            ph5.hilo.start();
            try {
                ph1.hilo.join();
                ph2.hilo.join();
                ph3.hilo.join();
                ph4.hilo.join();
                ph5.hilo.join();
            }catch (InterruptedException exc){
                System.out.println("Hilo principal interrumpido.");
            }
                System.out.println("\nHilo de alta prioridad contó hasta \t"+ph1.contar);
                System.out.println("Hilo de baja prioridad contó hasta \t"+ph2.contar);
                System.out.println("Hilo de normal prioridad #1 contó hasta "+ph3.contar);
                System.out.println("Hilo de normal prioridad #2 contó hasta "+ph4.contar);
                System.out.println("Hilo de normal prioridad #3 contó hasta "+ph5.contar);
    }
}
