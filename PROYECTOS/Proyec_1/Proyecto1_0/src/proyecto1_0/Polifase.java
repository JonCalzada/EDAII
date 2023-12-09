package proyecto1_0;
import java.io.*;
import java.util.*;

public class Polifase {
    public void PolifaseArchivo(String name){
        LinkedList<String> Cadenas=new LinkedList<>();
        interno ordenamiento=new interno();
        try{
            File archivo= new File(name);
            //System.out.println(archivo.exists());
            if (!archivo.exists())
                System.out.println("el archivo \'"+ archivo.getName()+"\' no existe.");
            else{
                BufferedReader in= new BufferedReader(new FileReader(archivo));
                //System.out.println(in.readLine());
                String[] cadena=in.readLine().split(",");
                Cadenas.addAll(Arrays.asList(cadena));
                ordenamiento.printArray(Cadenas);
                ordenamiento.sort(Cadenas, 0, (Cadenas.size()-1));
                ordenamiento.printArray(Cadenas);
            }
        }catch(Exception e){}
    }
}