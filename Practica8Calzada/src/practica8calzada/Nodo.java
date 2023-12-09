/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8calzada;

import java.util.Queue;

/**
 *
 * @author edaII05alu09
 */
public class Nodo{
    int valor;
    Nodo izq = null;
    Nodo der = null;
   
    
    public Nodo(){
    izq = der= null;
    }


    public Nodo (int data){
            this(data,null,null);
    }
    public Nodo(int data, Nodo lt, Nodo rt){
        valor= data;
        izq = lt;
        der = rt;
    }

    public void setIzq  (Nodo izq){
    this.izq=izq;
    }

    public void setDer( Nodo der){
    this.der = der;
    }
    
    public void RecorridoPreorden( Nodo n,Queue<Nodo> lista){
    lista.add(n);
    visit(n);
    if(n.izq!=null)
         RecorridoPreorden(n.izq,lista);
    if (n.der!=null)
         RecorridoPreorden(n.der,lista);
    }
    

public void RecorridoInOrden (Nodo n,Queue<Nodo> lista){
if (n.izq!=null)
     RecorridoInOrden(n.izq,lista);
        lista.add(n);
        if(n.der!=null)
          RecorridoInOrden(n.der,lista);
}

public void RecorridoPosOrden (Nodo n,Queue <Nodo>lista){
if (n.izq!=null)
      RecorridoPosOrden(n.izq,lista);
      if(n.der!=null)
          RecorridoPosOrden(n.der,lista);
        lista.add(n);
        visit(n);
}

     private void visit(Nodo n) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

}