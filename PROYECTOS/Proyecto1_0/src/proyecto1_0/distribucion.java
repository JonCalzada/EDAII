
package proyecto1_0;
import java.io.*;
import java.util.*;

public class distribucion {
/* Declaración de variables*/
    int iteraciones=0;
    int j=2;
    char[] abc={'A','B','C'
                ,'D','E','F'
                ,'G','H','I'
                ,'J','K','L'
                ,'M','N','O'
                ,'P','Q','R'
                ,'S','T','U'
                ,'V','W','X'
                ,'Y','Z'};
  /*    SENTENCIA DE FUNCION   */

  /** DISTRIBUCIÓN PARA LA ORDENACIÓN.
 Esta función ordena los caracteres por medio de la distribución .
 */

    void DistribucionR(String name){
        LinkedList<String> Cadenas=new LinkedList<>();
        /*     DECLARACIÓN DE VARIABLE  */
        String nuevo=name;
        name=name+".txt";
        /*     DECLARACIÓN DE excepciones  */

        try{
            File archivo= new File(".\\src\\Archivos Distribucion\\"+name);

            if (!archivo.exists()){
                System.out.println("el archivo \'"+ archivo.getName()+"\' no existe.");
                /*    CREACION DE UN OBJETO SC    */
                Scanner sc=new Scanner(System.in);
                System.out.println("Desea crear el archivo? s/n: ");
                /*     DECLARACION DE VARIABLE CON LECTURA   */
                char q=sc.nextLine().charAt(0);
                if(Character.toUpperCase(q)=='S'){
                    archivo.createNewFile();
                    try{
                        PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
                        String ABC="abcdefghijklmnopqrstuvwxyz";
                        Random rand= new Random();
                        char[] text=new char[3];
                        /*    DECLARACION DE VARIABLE   */
                        int loop=100;
                        for(int l=0;l<loop;l++){
                            for(int i=0;i<3;i++)
                                text[i]=ABC.charAt(rand.nextInt(ABC.length()));
                            pw.print(text);
                            pw.print(",");
                        }
                        pw.close();
                    }catch(Exception e){}

                    System.out.println("El archivo "+archivo.getName()+" se creo con 100 claves aleatorias.");
                    System.out.println("Desea contnuar el ordenamiento con \'"+archivo.getName()+"\'? s/n");
                    q=sc.nextLine().charAt(0);
                    if(Character.toUpperCase(q)=='S'){
                        BufferedReader in= new BufferedReader(new FileReader(archivo));
                        String[] cadena=in.readLine().split(",");
                        Cadenas.addAll(Arrays.asList(cadena));
                        File ruta=new File(".\\src\\Archivos Distribucion\\"+nuevo);
                        ruta.mkdir();
                        for(char n:abc){
                            String nombreN=nuevo+n+".txt";
                            File archivoCrear=new File(ruta+"\\"+nombreN);
                            archivoCrear.createNewFile();
                            /*   SENTENCIA DE CONSTRUCTORES    */
                        this.printArray(cadena, cadena.length);
                        this.radixSort(cadena, cadena.length, ruta,nuevo);
                        this.printArray(cadena, cadena.length);
                        }
                    }
                }else System.out.println("No se creo el archivo.");
            }
            else{
                BufferedReader in= new BufferedReader(new FileReader(archivo));
                String[] cadena=in.readLine().split(",");
                Cadenas.addAll(Arrays.asList(cadena));
                File ruta=new File(".\\src\\Archivos Distribucion\\"+nuevo);
                ruta.mkdir();
                for(char n:abc){
                    String nombreN=nuevo+n+".txt";
                    File archivoCrear=new File(ruta+"\\"+nombreN);
                    archivoCrear.createNewFile();
                }

                /*    SENTENCIA DE CONSTRUCTORES   */
                this.printArray(cadena, cadena.length);
                this.radixSort(cadena, cadena.length, ruta,nuevo);
                this.printArray(cadena, cadena.length);
            }
        }catch(Exception e){}

        /*    FIN DE LA FUNCIÓN  */
    }



        /*   SENTENCIA DE FUNCIONES */



/** Algoritmo Sort.
 *  Esta función implementa el algoritmo de ordenamiento interno

 */

	public void sort(String[] arr,int tam, int exp,File ruta,String nuevo) throws IOException{
		LinkedList<String>salida=new LinkedList<>();

                File ficher;
                FileWriter archivo;
                BufferedWriter bw;
                PrintWriter pw;


        /*   SENTENCIA DE EXCEPCIONES */
                try{
                    for(char n:abc){
                        ficher=new File(ruta+"\\"+nuevo+n+".txt");
                        archivo=new FileWriter(ficher,true);
                        bw=new BufferedWriter(archivo);
                        pw=new PrintWriter(archivo);
                        for(int i=0;i<tam;i++){
                            String cadena=arr[i];
                            if(Character.toUpperCase(cadena.charAt(j))==n){
                                pw.print(cadena+",");
                                salida.add(cadena);
                            }
                        }
                        pw.println();
                        pw.close();
                        bw.close();
                    }
                }catch(Exception e){}

                int it=0;
                for(String a:salida){
                    arr[it]=a;
                    it++;
                }
                try{
                    ficher=new File(".\\src\\Archivos Distribucion\\"+nuevo+".txt");
                    archivo=new FileWriter(ficher,true);
                    bw=new BufferedWriter(archivo);
                    pw=new PrintWriter(archivo);
                    pw.println();
                    pw.println("Iteracion #"+(iteraciones+1)+":");
                    for(String a: arr)
                        pw.print(a+",");
                    pw.close();
                    bw.close();

                }catch(Exception e){}

                j--;
		System.out.print("Ordenando los datos={ ");
		for(int a=0;a<tam;a++)
			System.out.print(arr[a]+" ");
		System.out.print("}");
		System.out.println("\n****Fin iteracion No:"+(iteraciones+=1)+"****");
		System.out.println();

        /*    FIN DE LA FUNCIÓN  */

	}



        /*    SENTENCIA DE FUNCION   */

        /** Algoritmo Sort.
 *  Esta función imprime el arreglo

 */

	public void printArray(String[] arr,int tam){
		for(int i=0;i<tam;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();

        /*    FIN DE LA FUNCIÓN  */

	}

        /*   SENTENCIA DE FUNCION   */

/** Algoritmo Sort.
 *  Esta función implementa el algoritmo de ordenamiento interno RADIX

 */
	public void radixSort(String[] arr,int n,File ruta,String nuevo) throws IOException{

		int mayor=111;
		for (int exponente = 1; mayor/exponente > 0; exponente *= 10)
			sort(arr, n, exponente, ruta,nuevo);

        /*    FIN DE LA FUNCIÓN  */

	}


}

