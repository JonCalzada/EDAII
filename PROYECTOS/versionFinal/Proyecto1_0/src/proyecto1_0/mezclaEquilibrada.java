package proyecto1_0;
import java.io.*;
import java.util.*;

public class mezclaEquilibrada {
    interno orden=new interno();
        int j=1;
        int iteraciones=0;

    void Mezclar(String name) throws IOException{
        String nuevo=name;
        name=name+".txt";
        LinkedList<String> cadenas=new LinkedList<>();
        
        try{
            File archivo=new File(".\\src\\Archivos M E\\"+name);
            
            if(!archivo.exists()){
                System.out.println("el archivo \'"+ archivo.getName()+"\' no existe.");
                Scanner sc=new Scanner(System.in);
                System.out.println("Desea crear el archivo? s/n: ");
                char q=sc.nextLine().charAt(0);
                if(Character.toUpperCase(q)=='S'){
                    archivo.createNewFile();
                    try{
                        PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
                        String ABC="abcdefghijklmnopqrstuvwxyz";
                        Random rand= new Random();
                        char[] text=new char[3];
                        int loop=100;
                        for(int l=0;l<loop;l++){
                            for(int i=0;i<3;i++)
                                text[i]=ABC.charAt(rand.nextInt(ABC.length()));
                            pw.print(text);
                            pw.print(",");
                        }
                        pw.close();
                    }catch(Exception e){}
                    System.out.println("El archivo"+archivo.getName()+" se creo con 100 claves aleatorias.");
                    System.out.print("Desea Continuar el ordenamiento con \'"+archivo.getName()+"\'? s/n:");
                    q=sc.nextLine().charAt(0);
                    if(Character.toUpperCase(q)=='S'){
                        BufferedReader in=new BufferedReader(new FileReader(archivo));
                        cadenas.addAll(Arrays.asList(in.readLine().split(",")));
                        File ruta=new File(".\\src\\Archivos M E\\"+nuevo);
                        ruta.mkdir();
                        File B1=new File(ruta+"\\"+nuevo+"B1.txt");
                        B1.createNewFile();
                        File B2=new File(ruta+"\\"+nuevo+"B2.txt");
                        B2.createNewFile();
                        int tam=cadenas.size();
                        this.recursivo(cadenas, tam,archivo,B1,B2);
                    }
                }else System.out.println("El archivo no ha sido creado.");
            }
            else{
                BufferedReader in=new BufferedReader(new FileReader(archivo));
                cadenas.addAll(Arrays.asList(in.readLine().split(",")));
                File ruta=new File(".\\src\\Archivos M E\\"+nuevo);
                ruta.mkdir();
                File B1=new File(ruta+"\\"+nuevo+"F1.txt");
                B1.createNewFile();
                File B2=new File(ruta+"\\"+nuevo+"F2.txt");
                B2.createNewFile();
                int tam=cadenas.size();
                this.recursivo(cadenas, tam,archivo,B1,B2);
            }
        }catch(Exception e){}
    }






    
    void recursivo(LinkedList<String> cadenas,int tam, File archivo, File B1, File B2) throws IOException{
        boolean seguir=false;
        LinkedList<String>[] bloque1=new LinkedList[tam/2];
        LinkedList<String>[] salida=new LinkedList[tam/2];
        LinkedList<String>[] bloque2;
        PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(archivo,true)));
        PrintWriter pw1=new PrintWriter(new BufferedWriter(new FileWriter(B1,true)));
        PrintWriter pw2=new PrintWriter(new BufferedWriter(new FileWriter(B2,true)));
        bloque2 = new LinkedList[tam/2];
            for(int i=0;i<tam/2;i++){
                bloque1[i]=new LinkedList();
                bloque2[i]=new LinkedList();
                for(int a=0;a<j;a++)
                        if(cadenas.size()!=0)bloque1[i].add(cadenas.poll());
                    orden.sort(bloque1[i], 0, (bloque1[i].size()-1));
                for(int a=0;a<j;a++)
                        if(cadenas.size()!=0)bloque2[i].add(cadenas.poll());
                    orden.sort(bloque2[i], 0, (bloque2[i].size()-1));
                }
            try{
                System.out.println("Bloque 1:");
                for(LinkedList<String> l:bloque1){
                    if(l.size()!=0){
                        System.out.print(l+",");
                        pw1.print(l+",");}
                    }
                System.out.println();
                pw1.println();
                System.out.println("Bloque 2:");
                for(LinkedList<String> l2:bloque2){
                    if(l2.size()!=0){
                        System.out.print(l2+",");
                        pw2.print(l2+",");}
                    }
                pw2.println();
                 if(j<tam){ j=j*2; seguir=true;} else seguir=false;
                 for(int i=0;i<bloque1.length;i++){
                       salida[i]=new LinkedList();
                while(bloque1[i].size()!=0){
                    salida[i].add(bloque1[i].poll());}
                while(bloque2[i].size()!=0){
                    salida[i].add(bloque2[i].poll());}
                orden.sort(salida[i], 0, (salida[i].size()-1));
                }
                pw.println();
                pw.println("Iteracion #"+(iteraciones+1));
                System.out.println("Juntando...");
                for (LinkedList<String> salida1 : salida) {
                    if (salida1.size() != 0) {
                        System.out.print(salida1 + ",");
                        pw.print(salida1 + ",");
                    while(salida1.size()!=0) cadenas.add(salida1.poll());
                }
            }
            pw.println();
            pw.println("Fin iteracion");
            System.out.println();
            pw.close();
            pw1.close();
            pw2.close();
            }catch(Exception e){}
            iteraciones++;
        if(seguir) recursivo(cadenas,tam,archivo,B1,B2);
        
    }
}