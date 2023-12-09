/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8calzada;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author edaII05alu09
 */
public class ArbolBin {
     
    ArbolBin prueba = new ArbolBin();
    Nodo root;
    public ArbolBin(){
        root=null;
    }
    public ArbolBin(int val){
        root=new Nodo(val);
    }
    public ArbolBin(Nodo root){
        this.root=root;
    }
    public void add(Nodo padre, Nodo hijo,int lado){
        if(lado==0)
            padre.setIzq(hijo);
        else
            padre.setDer(hijo);
    }
    protected void visit(Nodo n){
        System.out.print(n.valor+" ");
    }
    
 
    public void breadthFirst(){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                visit(r);
                if(r.izq!=null)
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
            
            }
        }
    }
    public void PostOrden(){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        RecorridoPostOrden(r,queue);
    }
    public void PreOrden(){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        RecorridoPreOrden(r,queue);
    }
    public void InOrden(){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        RecorridoInOrden(r,queue);
    }
    
    public void RecorridoPreOrden(Nodo n,Queue<Nodo> lista){
        lista.add(n);
        visit(n);
        if(n.izq!=null)
            RecorridoPreOrden(n.izq,lista);
        if(n.der!=null)
            RecorridoPreOrden(n.der,lista);
        }
    public void RecorridoInOrden(Nodo n,Queue<Nodo> lista){
        if(n.izq!=null)
            RecorridoInOrden(n.izq,lista);
        lista.add(n);
        visit(n);
        if(n.der!=null)
            RecorridoInOrden(n.der,lista);
    }
    
    public void RecorridoPostOrden(Nodo n,Queue<Nodo> lista){
        if(n.izq!=null)
            RecorridoPostOrden(n.izq,lista);
        if(n.der!=null)
            RecorridoPostOrden(n.der,lista);
        lista.add(n);
        visit(n);
    }
    
    
}

    
    
    