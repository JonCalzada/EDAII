import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TBP{
	int m;
	public NodoBP raiz;

	public TBP(int m){//constructor
		this.m=m;
		raiz=new NodoBP();//se instancia un NodoBPlus
		raiz.setM(m);
	}
	public void add(int n){
		if(find(n)){//verifica si la clave existe, porque no se pueden 
			//repetir valores
			System.out.println("La clave ya existe");
		}
		else{//si no la encuentra
			NodoBP hoja=leafNode(raiz,n);//se instancia un nodo que posteriormente
			addToNode(hoja,n);//se usara en el metodo agregar al nodo
		}
	}
	private void addToNode(NodoBP nodo, int n){
		if(nodo.key.size() <m-1){//verifica si el tamaño del nodo es menor a su capacidad total
			System.out.println("Tamaño de la llave del nodo"+nodo.key.size());
			insert(nodo,n);// si es menor se podra insertar
		}
		else
			divisionCelular(nodo,n);// si es mayor se debe de dividir el nodo
	}

	private void insert(NodoBP nodo, int n){
		int i=0;
		while(i< nodo.key.size() && n>nodo.getKey(i)){//loop que verifica que
			//el indice sea menor que el tamaño del nodo y que el numero a ingresar sea mayor al dato a ingresar
			i++;
		}
		if(nodo.isLeaft()==true){//verifica que el nodo sea hoja para poder insertarlo
			nodo.key.add(i,n);// se añade al nodo
		}
	}
	public boolean find(int value){//regresa un boollean si se encuentra
		if(raiz.child.isEmpty()==true && raiz.key.isEmpty()==true){//verifica que no tenga hijos y que el nodo este vacio
			return false;//significa que no se enceuntra nada en el arbol
		}
		return find(value, raiz);//se usa de manera recursiva hasta encontrar
	}

	private NodoBP leafNode(NodoBP nodo,int n){//regresa un nodo
		if(nodo.leaf==true){//si el nodo es hoja
			return nodo;//se regresa el ndo
		}
		else{//de lo contrario se empieza a navegar entre los datos del nodo no hoja
			int i=0;
			for(;i<nodo.key.size();i++){
				if(n<nodo.getKey(i)){
					i++;
					break;
				}
			}
			if(n<nodo.getKey(i-1))//se verifica que el valor de n sea mayor que el indice-1
				i--;//se resta para poder navegar en la indice pasado
			return leafNode(nodo.getChild(i),n);//se hace recursiva
		}
	}

	private boolean find(int v, NodoBP n){//es la opcion recursiva de la otra funcion de buscar
		if(n==null){//si el nodo que se pasa no contiene nada
			return false;// se regresa un falso, puesto que no se enceuntra
		}
		int i;
		if(n.getKey(0) > v)//si el indice es mayor que el valor
			return find(v,n.getChild(0));//se va al nodo izquierdo
		for(i=0;i<n.key.size()-1;i++){//recursivo para comparar el indice
			if(n.getKey(i)==v)//se compara el indice con el valor
				if(n.isLeaft()==true)//si el nodo es hoja, si es que no lo hay no puede regresar porque solo son referencias
					return true;//es que se encuentra el valor, realmente
				else{
					return find(v,n.getChild(i));//si no es nodo hoja, se va a buscar en el indice de hijos de ese nodo
				}
			if(n.getKey(i)< v && n.getKey(i+1)>v){//si el valor es mayor al valor del 
				//indice siguinete y a la vez mayor que el indice se debe de ser recursivo
				return find(v,n.getChild(i+1));
			}
		}
		if(n.getKey(i)==v)//si el valor de la llave es igual al dato, entra
				if(n.isLeaft()==true)//pero primero se verifica si es hoja
					return true;//existe
		else{
			if(n.getKey(i)<v)//si el indice es menor que el valor
				return find(v,n.getChild(i+1));//se buisa en el siguiente indice
			else
				return find(v,n.getChild(i));//busca en esa referencia
		}
	}

	public void mostrarArbol(){//imprime el arbol
		if(raiz.child.isEmpty()==true && raiz.key.isEmpty()==true){//si la raiz esta vacia , tanto como a hijos y sin erlemntos
			System.out.println("No hay elementos");
			return  ;
		}
		Queue<NodoBP> nodos= new LinkedList<>();//se encola a los nodos 
		nodos.add(raiz);//se añade a la raiz del arbol
		NodoBP padre=null;// la raiz no puede tener padre
		while(!nodos.isEmpty()){//mientas la lista no este vacia
			NodoBP v=nodos.poll();//
			if(v.padre==null){// si el nodo no tiene padre, significa que es la raiz
				System.out.println("Raiz");
			}
			if(padre!=v.padre){//si tiene el nodo algun padre, significa que es nodo hoja o nodo rama
				System.out.println("\n\n\n Nodo Padre: ");
				v.padre.mostrarLLaves();//muestra los elementos del padre
				padre=v.padre;//
				System.out.println("\n\t\t Nodos: ");
			}
			System.out.println("\n\t\t");
			v.mostrarLLaves();//muestra las llaves del nodo hijo/hoja
			for(int i=0;i<v.child.size();i++)
				nodos.add(v.child.get(i));
		}
		System.out.println("\n");
	}

	private void divisionCelular(NodoBP nodo,int n){
		int h=(m-1)/2;//das un limite inferior de llenado "half"
		System.out.println("Valor del tamaño del nodo hasta ahora: "+h);
		if(m % 2 !=0){//si es inpar
			System.out.println(n+" se anadira");
			insert(nodo,n);//se inserta
		}
		int medio=nodo.key.get(h);//usas la mitad como el limite

		ArrayList<Integer> key1=new ArrayList(nodo.key.subList(0,h));//llave 1
		ArrayList<Integer> key2=new ArrayList(nodo.key.subList(h+1,2*h+1));//llave 2
		ArrayList<NodoBP> child1= new ArrayList();//hijo 1 para la division
		ArrayList<NodoBP> child2= new ArrayList();//hijo 2 para la division

		if(nodo == raiz){//si el nodo es igual a la raiz
			NodoBP nuevoNodo1 = new NodoBP();//se instancian dos nuevos nodos
			NodoBP nuevoNodo2= new NodoBP();
			nuevoNodo1.leaf=nuevoNodo2.leaf=nodo.leaf;//se igualan las referencias

			nuevoNodo1.setKeys(key1);//se añaden las llaves al nuevo nodo
			nuevoNodo2.setKeys(key2);
			nodo.key.clear();//se limpia el nodo, ya no hay elementos
			nodo.key.add(medio);//se añade al dato medio
			if(m % 2==0){//si m es par 
				if(n<medio)//si el dato es menor que el valor medio
					insert(nuevoNodo1,n);// se añade en el nodo 1
				else
					insert(nuevoNodo2,n);//de lo contartio se añade al nodo 2
			}
			if(!nodo.leaf){//si el nodo no es una hoja
				if(m % 2 !=0){//y si m es inpar
					child1=new ArrayList(nodo.child.subList(0, h+1));//se añade como a su hijo desde el dato 0 hasta el dato medio mas 1
					child2=new ArrayList(nodo.child.subList(h+1,m+1));// se añade a su hijo desde le medio +1 hasta m+1
				}
				else{
					if(n< medio){//si el dato es mennor que el medio
						child1= new ArrayList(nodo.child.subList(0,h+2));//
						child2= new ArrayList(nodo.child.subList(h+2,m+1));//
					}
					if(n>medio){//si el dato es mayor que el medio
						child1=new ArrayList(nodo.child.subList(0,h+1));//
						child2=new ArrayList(nodo.child.subList(h+1,m+1));//
					}
				}

				nuevoNodo1.setChildren(child1);//el nuevo nodo tiene como hijos a los nodos child
				nuevoNodo2.setChildren(child2);//
				for(NodoBP i: nuevoNodo1.child)//
					i.padre=nuevoNodo1;
				for(NodoBP i: nuevoNodo2.child)
					i.padre=nuevoNodo2;
			}
			nodo.child.clear();//se limpia el nodo, dejandolo vacio
			nodo.child.add(nuevoNodo1);//se añade como hijo al nuevo nodo1
			//aqui se inserta la referencia

			nodo.child.add(nuevoNodo2);
			//aqui se inserta la referencia

			nuevoNodo1.padre=nuevoNodo2.padre=raiz;//la referencias indican que el padre es la raiz
			nodo.leaf=false;// le da al atributo nodo como falso
		}
		else{// si el nodo no es la raiz
			NodoBP nuevoNodo= new NodoBP();// se instancia un nuevio nodobplus
			nuevoNodo.leaf=nodo.leaf;//se usa como no auxiliar, se igualan las referencias, como la hoja
			nuevoNodo.padre=nodo.padre;//se iguala al padre

			int childIndex=nodo.getChildIndex();// se espera el valor del indice se sus hijos

			nodo.setKeys(key1);//se añaden las llaves al nodo
			nuevoNodo.setKeys(key2);// se añaden las llaves al nuevo nodo
			if(m % 2==0){
				if(n<medio)//si el valor del dato es menor
					insert(nodo,n);//se van con el nodo originsl
				else
					insert(nuevoNodo,n);// se van con el nuevo nodo, puesto que se esta dividiendo
			}
			if(!nodo.leaf){//si el nodo no es una hoja
				if(m % 2!=0){//y si no es par
					child1= new ArrayList(nodo.child.subList(0,h+1));//se añaden loslementos desde el indice 0 hasta la mitad más uno
					child2=new ArrayList(nodo.child.subList(h+1,m+1));//la mitad mas uno hasta el fin del nodo original
				}
				else{
					if(n<medio){//si el valor es menor que el valor medio
						child1= new ArrayList(nodo.child.subList(0,h+2));//
						child2=new ArrayList(nodo.child.subList(h+2,m+1));
					}
					if(n>medio){//
						child1=new ArrayList(nodo.child.subList(0,h+1));
						child2=new ArrayList(nodo.child.subList(h+1,m+1));
					}
				}
				nodo.setChildren(child1);//se añaden la lista al nodo
				nuevoNodo.setChildren(child2);//el segundo nodo se le añaden la segunda lista
				for(NodoBP i: nodo.child)
					i.padre=nodo;
				for(NodoBP i: nodo.child)
					i.padre=nuevoNodo;
			}
			nodo.padre.child.add(childIndex + 1, nuevoNodo);//al nodo padre en su atributo hijos se le añaden el valor
			//siguiente en el indice y el nuevo nodo
			addToNode(nodo.padre, medio);//manda al nodo padre y al valor medio
		}
	}

	/*
	public void errase(int n){
		boolean v;
		v=find(n);
		if(v){
							
		}
		else{
			System.out.println("El elemento no existe");
		}
	}
	*/
	/*
	private void errase(NodoBP nodo, int n){
	
	}
	*/
	/*
	private void unionCelular(NodoBP n, int n){
		//se supone que aqui corroboro si hay elementos a la izquierda y despues
		//a la derecha para tomar prestadas las referencias
		//de lo contrario se fusionan el nodo con el nodo izquierdo, si es que hay
	}
	*/
}