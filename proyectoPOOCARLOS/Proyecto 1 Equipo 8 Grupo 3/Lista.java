
public class Lista {
    //Dos punteros,las listas tienen referencias al inicio y al final de la lista
    protected Nodo inicio,fin; //Punterostipo Nodo  para saber donde esta el inicio y el fin 
    
    //Constructor inicia los puntos
    public Lista (){
        inicio=null;
        fin=null;
    }
    //Método para Agregar un Nodo al inicio de la lista
    public void agregarAlInicio(String elemento){//Me sirve para crear un nodo al incio de la lista
        //Creando el Nodo
        inicio=new Nodo(elemento,inicio);//Para saber donde esta ubicado el inicio de mi lista
        if(fin==null){//Significa que no hay ningunno
            fin=inicio; // Haci que por tanto fin e inicio sera lo mismo
        }
    }
    //Metodo para saber si la lista esta vacia me retorna un true o un false
    public boolean estaVacia(){
        if(inicio==null){ // Si apunta a null es obvio que la lista esta vacia
            return true;
        }else{
            return false;
        }
        
    }
    //Método para insertar al final de la lista
    public void agregarAlFinal(String elemento){
        if(!estaVacia()){ // Si no esta vacia
            fin.siguiente=new Nodo(elemento);
            fin=fin.siguiente; // Fin apuntamelo a fin de siguiente
        }else{ // No hay elementos 
            inicio=fin=new Nodo(elemento);// El Inicio y el fin apuntan al nuedo nodo
        }
    }
    //Metodo para mostrar los datos
    public void mostrarLista(){
        Nodo recorrer=inicio; // Creamos un nodo auxiliar
        System.out.println("\n");
        while(recorrer!=null){
            System.out.print("["+recorrer.dato+"]---->");//Accede a recorrer.dato
            recorrer=recorrer.siguiente;//Para que vaya avanzando
        }
    }
    
    //Método borrar para eliminar un nodo del incio
    
    public String borrarDelInicio(){
        String elemento =inicio.dato;
        
        if(inicio==fin){
            inicio=fin=null;
        }else{ //al menos ya hay dos nodos
            inicio=inicio.siguiente;
        }
        return elemento;//Retornamos un elemento que no existe
    }
    
    //Método para eliminar un nodo del final
    
    public String borrarDelFinal(){ // no recibe parametros solo retorna
        String elemento=fin.dato;
        if(inicio==fin){
            inicio=fin=null;
        }else{
            Nodo temporal=inicio; //El nodo temporal nos sirve para ir recorriendo toda la lista y saber cual vamos a eliminar
            while(temporal.siguiente!=fin){
                temporal=temporal.siguiente;
            }
            fin=temporal;
            fin.siguiente=null;
        }
        return elemento;
    }
    //Método para eliminar un nodo en epecifico
    public void eliminar(String elemento){
            if(!estaVacia()){
                if(inicio==fin && (elemento == null ? inicio.dato == null : elemento.equals(inicio.dato))){// el unico nodo que tengo
                    inicio=fin=null;
                    
                }else if(elemento == null ? inicio.dato == null : elemento.equals(inicio.dato)){
                    inicio=inicio.siguiente; //Apuntar a inicio a inicio de siguiente
                }else{
                    //Se crean dos punteros de tipo nodo
                    Nodo anterior,temporal;
                    anterior=inicio;
                    temporal=inicio.siguiente;
                    //Recorriendo nuestra lista hasta que encuentre al elemento que estoy buscando
                    while(temporal!=null && (temporal.dato == null ? elemento != null : !temporal.dato.equals(elemento))){ //temporal!=null..hala llegada hasta donde no hay nadatemporal.dato!=elemento....elemento que estoy buscando
                       anterior=anterior.siguiente; //estoy recorriendo mis punteros
                       temporal=temporal.siguiente;
                    }
                    if(temporal!=null){// si es diferente de null,quiere decir que lo encontro
                        anterior.siguiente=temporal.siguiente;
                        if(temporal==fin){ //Quiere decir que el temporal es el final
                            fin=anterior;// Mover a fin a anterior por que se elimino uno
                        }
                    }
                }//fin else
               
            } 
        }//fin public
    
    //Método para buscar un Elemento
    public boolean estaEnLaLista(String elemento){
        Nodo temporal = inicio; // Nodo temporal que apunta a inicio
        while(temporal!=null && (temporal.dato == null ? elemento != null : !temporal.dato.equals(elemento))){
            temporal=temporal.siguiente;
            
        }
        return temporal!=null; // Si temporal es diferente de null ,es obvio que nos retorna un true
        
    }    
    
}
