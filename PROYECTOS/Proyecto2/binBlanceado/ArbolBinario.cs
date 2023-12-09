using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;

namespace Proyecto2
{
    class ArbolBinario
    {
        NodoBin root = new NodoBin();//es la raiz
        private int hL = 0, hR = 0;

        public ArbolBinario()//constructor
        {
            root = null;
        }

        public void insertar(int key)
        {
            if (root == null)//si la raiz es nula se crea un nodo raiz con el valor
                root = new NodoBin(key);
            else if (find(key))//si no, se debe de encontrar el valor
                Console.WriteLine("El valor ya se encuentra en el arbol.");//si lo encuentra ya no se agrega
            else
                agregarAlArbol(key, root);//se añade al arbol
            Reacomodar();
        }
        private void Reacomodar()
        {
            if (isBalanced())
                Console.WriteLine("Arbol balanceado.");
            else if(root != null)
            {
                Console.WriteLine("Arbol no balanceado, reorganizando...");
                if (hL > hR)
                    rotarLeft(root);
                else
                    rotarRight(root);
                Console.WriteLine("Arbol Balanceado.");
            }
        }
        private void agregarAlArbol(int key, NodoBin raiz)
        {
            NodoBin hijo = new NodoBin(key);
            if (raiz.isLeaf() && raiz.getKey() < key)//verifica que la raiz no tenga hijos y que el valor de la raiz sea ,menor que el valor
                raiz.setHD(hijo);//asigna el nodo como hijo derecho
            else if (raiz.isLeaf() && raiz.getKey() > key)//sin hijos y mayor que el numero
                raiz.setHI(hijo);//asigna al nodo hijo izquierdo
            else if (raiz.getIzquierdo() != null && raiz.getKey() > key)//
                agregarAlArbol(key, raiz.getIzquierdo());//llamado recursivo con el hijo izquierdo
            else if (raiz.getDerecho() != null && raiz.getKey() < key)
                agregarAlArbol(key, raiz.getDerecho());//llamado recursivo con el hijo derecho
            else if (raiz.getDerecho() == null && raiz.getKey() < key)//si no existe el hijo derecho y el valor es mayor a la clave se asigana a la derecha
                raiz.setHD(hijo);//
            else if (raiz.getIzquierdo() == null && raiz.getKey() > key)//si no existe el hijo izquierdo y el valor es menor a la clave actual se asigna a la izquierda
                raiz.setHI(hijo);
            hijo.setFather(raiz);//se le asigna el padre al nuevo nodo.
        }
        public Boolean find(int key)//para encontrar el valor
        {
            NodoBin r = root;//primero se crea un nodo igualado a la raiz del arbol
            if (r == null)//si la raiz es inexistente devuelve falso
                return false;
            else if (r.getKey() == key)//se compra si los valores son iguales
                return true;//significa que lo encontro y devulve true
            else if (r.getKey() > key && r.getIzquierdo() != null)//se compara si el valor es menor
            {
                return find(key, r.getIzquierdo());// se pasa el valor de la llave y el nodo izquiero
            }
            else if (r.getKey() < key && r.getDerecho() != null)//si es mayor
            {
                return find(key, r.getDerecho());//se pasa el valor a buscar y el nodo derecho
            }
            else
                return false;//no lo encontro

        }
        private Boolean find(int key, NodoBin root2)//esta es la llamada recursiva que se ocupa en find
        {/*Al igual que en la primer find se comparan los valores y dependiendo de la opcion
            se emplea la instruccion*/
            NodoBin r = root2;
            if (r.getKey() == key)
                return true;
            else if (r.getKey() > key && r.getIzquierdo() != null)
            {
                return find(key, r.getIzquierdo());
            }
            else if (r.getKey() < key && r.getDerecho() != null)
            {
                return find(key, r.getDerecho());
            }
            else if (r.getKey() == key)
                return true;
            else
                return false;
        }

        private Boolean isBalanced()
        {
            if (root != null) {
                profundidad(root.getDerecho(), false);
                profundidad(root.getIzquierdo(), true);
                Console.WriteLine(hL + "   " + hR);
                if ((hL - hR) == -1 || (hL - hR) == 0 || (hL - hR) == 1)
                    return true;
                else return false;
            }
            else
                hL = hR = 0;
            return false;
        }

        private void profundidad(NodoBin nodo, Boolean flag)
        {
            if (nodo != null)
            {
                NodoBin r = nodo;
                Queue<NodoBin> q = new Queue<NodoBin>();
                q.Enqueue(r);
                while (q.Count != 0)
                {
                    r = q.Dequeue();
                    if (r.getIzquierdo() != null) q.Enqueue(r.getIzquierdo());
                    if (r.getDerecho() != null) q.Enqueue(r.getDerecho());
                }
                if (flag)
                    this.hL = r.getProfundidad();
                else this.hR = r.getProfundidad();
            }
        }

        private void rotarLeft(NodoBin raiz)
        {
            NodoBin aux1, aux2, aux3;
            aux2 = aux3 = new NodoBin();
            NodoBin r = raiz;
            Queue<NodoBin> q = new Queue<NodoBin>();
            q.Enqueue(r);

            while (q.Count != 0)
            {
                r = q.Dequeue();
                if (r.getIzquierdo() != null && !r.getIzquierdo().isLeaf()) q.Enqueue(r.getIzquierdo());
                if (r.getDerecho() != null && !r.getDerecho().isLeaf()) q.Enqueue(r.getDerecho());
            }
            if (r.getIzquierdo() != null)
                aux3 = r.getIzquierdo();
            else if (r.getDerecho() != null)
                aux3 = r.getDerecho();
            aux2 = r;
            aux1 = r.getPadre();

            raiz = aux2;
            raiz.newProfundidad(aux1.getProfundidad());
            raiz.setFather(aux1.getPadre());
            raiz.setHD(aux1);
            raiz.setHI(aux3);
            if (aux1 == root) root = raiz;
            else aux1.getPadre().setHI(raiz);
            aux3.setFather(raiz);
            aux1.setFather(raiz);
            aux1.setHI(null);
        }

        private void rotarRight(NodoBin raiz)
        {
            NodoBin aux1, aux2, aux3;
            aux2 = aux3 = new NodoBin();
            NodoBin r = raiz;
            Queue<NodoBin> q = new Queue<NodoBin>();
            q.Enqueue(r);

            while (q.Count != 0)
            {
                r = q.Dequeue();
                if (r.getIzquierdo() != null && !r.getIzquierdo().isLeaf()) q.Enqueue(r.getIzquierdo());
                if (r.getDerecho() != null && !r.getDerecho().isLeaf()) q.Enqueue(r.getDerecho());
            }
            if (r.getIzquierdo() != null)
                aux3 = r.getIzquierdo();
            else if (r.getDerecho() != null)
                aux3 = r.getDerecho();
            aux2 = r;
            aux1 = r.getPadre();

            raiz = aux2;
            raiz.newProfundidad(aux1.getProfundidad());
            raiz.setFather(aux1.getPadre());
            raiz.setHD(aux3);
            raiz.setHI(aux1);
            if (aux1 == root) root = raiz;
            else aux1.getPadre().setHD(raiz);
            aux3.setFather(raiz);
            aux1.setFather(raiz);
            aux1.setHD(null);
        }

        public void eliminarKey(int key)//el método para eliminar
        {
            if (!find(key))//si no se encunetra el dato a eliminar se manda un mensaje y no se hace nada.
                Console.WriteLine("No se puede eliminar una clave inexistente.");
            else//Si se encuentra, se llama al metodo correspondiente para eliminarlo
            {
                eliminarKey(key, root);
            }
        }

        private void eliminarKey(int key, NodoBin nodo)//Método complementario para eliminar
        {
            if (key == nodo.getKey() && nodo.isLeaf())
            {
                if (nodo.getPadre() != null)
                {
                    if (key < nodo.getPadre().getKey())
                        nodo.getPadre().setHI(null);
                    else
                        nodo.getPadre().setHD(null);
                }
                else if (nodo == root && nodo.isLeaf())
                    root = null;
            }
            else if (nodo.getKey() == key && nodo.getIzquierdo() == null && nodo.getDerecho() != null
                    && nodo.getDerecho().isLeaf())
            {
                NodoBin aux1, aux2;
                aux1 = aux2 = new NodoBin();
                aux1 = nodo;
                aux2 = nodo.getDerecho();
                if (aux1.getPadre() != null)
                {
                    aux1.getPadre().setHD(aux2);
                    aux2.setFather(aux1.getPadre());
                }
                else
                {
                    aux2.setFather(null);
                }
                if (aux1 == root) root = aux2;
            }
            else if(nodo.getKey() == key && nodo.getIzquierdo() != null && nodo.getDerecho() == null
                    && nodo.getIzquierdo().isLeaf())
            {
                NodoBin aux1, aux2;
                aux1 = aux2 = new NodoBin();
                aux1 = nodo;
                aux2 = nodo.getIzquierdo();
                if (aux1.getPadre() != null)
                {
                    aux1.getPadre().setHI(aux2);
                    aux2.setFather(aux1.getPadre());
                }
                else
                {
                    aux2.setFather(null);
                }
                if (aux1 == root) root = aux2;
            }else if(nodo.getKey()==key && nodo.getDerecho()!=null && !nodo.getDerecho().isLeaf())
            {
                NodoBin aux1 = nodo, aux2 = new NodoBin();
                Queue<NodoBin> q = new Queue<NodoBin>();
                q.Enqueue(nodo.getDerecho());
                while (q.Count!=0)
                {
                    aux2 = q.Dequeue();
                    if (aux2.getIzquierdo() != null) q.Enqueue(aux2.getIzquierdo());
                }

                if (aux1.getPadre() != null) 
                {
                    aux2.getPadre().setHI(null);
                    aux2.setFather(aux1.getPadre());
                    if (nodo.getPadre().getKey() < key) nodo.getPadre().setHD(aux2);
                    else nodo.getPadre().setHI(aux2);
                }
                else
                {
                    root = aux2;
                    aux2.setFather(null);
                }
                aux2.setHD(nodo.getDerecho());
                aux2.setHI(nodo.getIzquierdo());
            }
            else if (nodo.getKey() < key)
                eliminarKey(key, nodo.getDerecho());
            else
                eliminarKey(key, nodo.getIzquierdo());
            if (nodo != root||(nodo==root && !nodo.isLeaf())) Reacomodar();
            nodo = null;
            
        }

        public void MostrarArbol()//solo imprime el arbol
        {/*Cabe destacar que al imprimirse se hace de nodo en nodo, por lo que se veran demasiados 
           en la pantalla*/
            NodoBin r = root;
            if (r != null)
            {
                Queue<NodoBin> q = new Queue<NodoBin>();
                q.Enqueue(r);
                while (q.Count != 0)
                {
                    NodoBin sig = q.Dequeue();
                    if (sig.getIzquierdo() != null) q.Enqueue(sig.getIzquierdo());
                    if (sig.getDerecho() != null) q.Enqueue(sig.getDerecho());
                    Console.WriteLine("padre:\t\t [" + sig.getKey() + "]\t-->\tnivel:(" + sig.getProfundidad() + ")");
                    if (!sig.isLeaf()) Console.Write("Nodos:\t");
                    if (sig.getIzquierdo() != null)
                        Console.Write("[" + sig.getIzquierdo().getKey() + "]");
                    if (sig.getDerecho() != null)
                        Console.Write("\t\t[" + sig.getDerecho().getKey() + "]");
                    Console.WriteLine("\n******************************");
                }
            }
            else
                Console.WriteLine("Arbol vacio.");
        }
    }
}