package practica4_garciacarlos;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author alumno
 */
public class Practica4_GarciaCarlos {

    public static void main(String[] args) {
        List<Integer> lista1 = new LinkedList<>();
        
        System.out.println(lista1.isEmpty());
        
        lista1.add(15);
        lista1.add(75);
        lista1.add(25);
        lista1.add(55);
        lista1.add(80);
        
        System.out.println("Estado punto 1 ");
        imprimirLista(lista1);
        System.out.println("***");
        
        lista1.add(1, 300);
        lista1.add(3,500);
        lista1.add(5,700);
        
        System.out.println("Estado punto 2");
        imprimirLista (lista1);
        System.out.println("***");
        
        lista1.set(0, 4);
        lista1.set(2, 6);
        lista1.set(7, 8);
        
        lista1.remove(2);
        System.out.println(lista1.contains(10));
        System.out.println(lista1.indexOf(59));
        System.out.println("Estado punto 3");
        imprimirLista(lista1);
        System.out.println("***");
        
        List<Integer> lista2;
        lista2 = lista1.subList(3, 6);
        imprimirLista(lista2);
        System.out.println("***");
        System.out.println(lista1.equals(lista2));
        
        lista1.remove(1);
        imprimirLista(lista1);
        System.out.println("***");
        
        System.out.println(lista1.indexOf(500));
        System.out.println(lista1.contains(500));
        
        System.out.println(lista1.isEmpty());
        
        busquedaLineal busca= new busquedaLineal();
        busca.existe(lista1, 500);
        busca.veces(lista1, 700);
        busca.indice(lista1,55);
        
        int [] lista3={10,20,20,30,40,50,60,70,80,90,100};
        busquedaBinaria busca2= new busquedaBinaria();
        busca2.verdaderoBinario(lista3, 100);
        busca2.vecesBinario(lista3, 100);
        
        System.out.println("***Inicia test de objetos");
        Carro carro1= new Carro();
        carro1.modelo=2010;
        carro1.marca= "Avanza";
        Carro carro2= new Carro();
        carro2.marca= "Gol";
        carro2.modelo=2015;
        Carro carro3= new Carro();
        carro3.marca="Tida";
        carro3.modelo=2019;
        List<Integer> lista4 = new LinkedList<>();
        lista4.add(carro1.modelo);
        lista4.add(carro2.modelo);
        lista4.add(carro3.modelo);
        imprimirLista(lista4);
        busca.existe(lista4,2019);
        busca.indice(lista4,2010);
        busca.veces(lista4,2015);
        busquedaLineal2 b2= new busquedaLineal2();
        List<String> lista5 = new LinkedList<>();
        lista5.add(carro1.marca);
        lista5.add(carro2.marca);
        lista5.add(carro3.marca);
        imprimirListalstring(lista5);
        b2.existe(lista5,"Gol");
        b2.indice(lista5,"Tida");
        b2.veces(lista5,"Avanza");
    }
    
    public static void imprimirLista(List<Integer> listaPrint){
        //for(Integer var : listaPrint){
            System.out.println(listaPrint);
        //}
    }
    public static void imprimirListalstring(List<String> listPrint){
        //for(Integer var : listaPrint){
            System.out.println(listPrint);
        //}
    }
    
}
