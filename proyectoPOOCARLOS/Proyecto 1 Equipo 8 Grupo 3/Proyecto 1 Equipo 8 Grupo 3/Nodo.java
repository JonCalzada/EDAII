
public class Nodo {
    
    public String dato;
    public Nodo siguiente;//Puntero enlazante para poder ir enlazando nuestros nodos
    //Constructor para insertar al final
    public Nodo(String d){ //Constructor llamado igual a nuestra clase
        this.dato=d;
        this.siguiente =null;
    }
    //Constructor para insertar al inicio
    public Nodo(String d , Nodo n){ //Recibe 2 parametros ,el dato como tal d y un puntero hacia nodo
        dato=d;
        siguiente=n;
    }

    Nodo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}