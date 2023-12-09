package proyecto1_0;
import java.io.*;
import java.util.*;

public class distribucion {
    int iteraciones=0;
    int j=2;
    char[] abc={'A','B','C'
                ,'D','E','F'
                ,'G','H','I'
                ,'J','K','L'
                ,'M','N','O'
                ,'P','Q','R'
                ,'S','T','U'
                ,'V','X','Y'
                ,'Z'};
    
    void DistribucionR(String name){
        LinkedList<String> Cadenas=new LinkedList<>();
        
        String nuevo=name;
        name=name+".txt";
        try{
            File archivo= new File(name);
            //System.out.println(archivo.exists());
            if (!archivo.exists()){
                System.out.println("el archivo \'"+ archivo.getName()+"\' no existe.");
                //archivo.createNewFile();
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
                } //Aqui crea los archivos
                
                this.printArray(cadena, cadena.length);
                this.radixSort(cadena, cadena.length, ruta,nuevo);
                this.printArray(cadena, cadena.length);
            }
        }catch(Exception e){}
    }
    
    	public String getMaximo(String[] arr,int tam){
		String maximo=arr[0];
		for(int i=1;i<tam;i++){
			if(arr[i].compareTo(maximo)>0)
				maximo=arr[i];
		}
		return maximo;
	}

	public void sort(String[] arr,int tam, int exp,File ruta,String nuevo) throws IOException{
		LinkedList<String>salida=new LinkedList<>();
                
                File ficher;
                FileWriter archivo;
                BufferedWriter bw;
                PrintWriter pw;
                
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
                /*try{
                    for(char n:abc){
                        BufferedReader in=new BufferedReader(new FileReader((ruta+"\\"+nuevo+n+".txt")));
                        for(int i=2;i<=j;i--)
                           if(in.readLine()!=null)salida.addAll(Arrays.asList(in.readLine().split(",")));
                    }
                 }catch(Exception e){}*/
                System.out.println("///////"+salida);
                int it=0; 
                for(String a:salida){
                    arr[it]=a;
                    it++;
                }
                
                try{
                    ficher=new File(nuevo+".txt");
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
	}

	public void printArray(String[] arr,int tam){
		for(int i=0;i<tam;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public void radixSort(String[] arr,int n,File ruta,String nuevo) throws IOException{

		int mayor=111;
		for (int exponente = 1; mayor/exponente > 0; exponente *= 10)
			sort(arr, n, exponente, ruta,nuevo);

	}


}
