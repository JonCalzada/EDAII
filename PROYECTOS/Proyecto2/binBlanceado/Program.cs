using System;

namespace Proyecto2
{
    class Program
    {
        static void menu()
        {
            Console.WriteLine("***********MENU************");
            Console.WriteLine("*1) Arbol Binario         *");
            Console.WriteLine("*2) Arbol B+              *");
            Console.WriteLine("*3) Salir                 *");
            Console.WriteLine("***************************");
            Console.Write("Opcion: ");
        }

        static void menuArbol()
        {
            Console.WriteLine("************MENU*************");
            Console.WriteLine("*1) Insercion               *");
            Console.WriteLine("*2) Elimincacion            *");
            Console.WriteLine("*3) Busqueda                *");
            Console.WriteLine("*4) Impresion del Arbol     *");
            Console.WriteLine("*5) Volver al menu Principal*");
            Console.WriteLine("*****************************");
            Console.Write("Opcion: ");
        }
        static void Main(string[] args)
        {
            Boolean regresar, loop = true;
            int opcion, op2;
            ArbolBinario AB = new ArbolBinario();
            while (loop)
            {
                menu();
                opcion = int.Parse(Console.ReadLine());
                switch (opcion)
                {
                    case 1:
                        regresar = true;
                        while (regresar)
                        {
                            menuArbol();
                            op2 = int.Parse(Console.ReadLine());
                            switch (op2)
                            {
                                case 1:
                                    Console.Write("Ingrese el valor que desea agregar: ");
                                    AB.insertar(int.Parse(Console.ReadLine()));
                                    break;
                                case 2:
                                    AB.eliminarKey(int.Parse(Console.ReadLine()));
                                    break;
                                case 3:
                                    Console.Write("Ingrese el valor a buscar: ");
                                    if (AB.find(int.Parse(Console.ReadLine())))
                                        Console.WriteLine("La clave si existe.");
                                    else
                                        Console.WriteLine("Clave inexistente.");
                                    break;
                                case 4:
                                    AB.MostrarArbol();
                                    break;
                                case 5:
                                    regresar = false;
                                    break;
                                default:
                                    Console.WriteLine("Opocion no valida.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        menuArbol();
                        op2 = int.Parse(Console.ReadLine());
                        break;
                    case 3:
                        loop = false;
                        break;
                    default:
                        Console.WriteLine("Opcion no valida.");
                        break;
                }
            }
        }
    }
}