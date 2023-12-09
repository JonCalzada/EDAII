/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8calzada;

/**
 *
 * @author Jonathan
 */
public class ArbolBiBusq {
     public class Nodo {
    int numMat;
    Nodo izqda, drcha;
    public Nodo(int mat){
        numMat = mat;
        izqda = drcha = null;
    }
    public void enorden(){
        if(izqda != null)
            izqda.enorden();
        System.out.println("Matricula:   " +numMat);
        if(drcha != null)
            drcha.enorden();
    }
}
public class Abb {
    private Abb(){
        raiz = null;
    }
    public void insertar(int nuevoM){
        if(raiz==null){
            raiz =  new Nodo(nuevoM);
        }
        else{
            insertar(raiz,nuevoM);
        }
    }
    private void insertar(Nodo rz, int nm){
        if (rz == null)
            rz = new Nodo(nm);
        else if(nm < rz.numMat)
            insertar(rz.izqda,nm);
        else if(nm > rz.numMat)
            insertar(rz.drcha,nm);
        else
            System.out.println("Numero Duplicados");
    }
}
