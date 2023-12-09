using System;
using System.Collections.Generic;
using System.Text;

namespace Proyecto2
{
    class NodoBin
    {
        int valor;
        NodoBin izq = null;
        NodoBin der = null;
        NodoBin padre = null;
        int nivel = -1;

        public NodoBin()
        {
            padre = izq = der = null;
            nivel = 0;
        }

        public NodoBin(int key)
        {
            this.valor = key;
            this.izq = null;
            this.der = null;
            this.padre = null;
            this.nivel = this.nivel + 1;
        }

        public int getKey()
        {
            return this.valor;
        }

        public NodoBin getIzquierdo()
        {
            return this.izq;
        }

        public NodoBin getDerecho()
        {
            return this.der;
        }

        public NodoBin getPadre()
        {
            return this.padre;
        }

        public Boolean isLeaf()
        {
            return this.izq == null && this.der == null ? true : false;
        }

        public void setHI(NodoBin izquierdo)
        {
            this.izq = izquierdo;
        }

        public void setHD(NodoBin derecho)
        {
            this.der = derecho;
        }

        public void setFather(NodoBin padre)
        {
            this.padre = padre;
            if (padre != null) this.nivel = padre.nivel + 1;
            else this.nivel = 0;
        }

        public int getProfundidad()
        {
            return this.nivel ;
        }

        public void newProfundidad(int key)
        {
            this.nivel = key;
        }
    }
}
