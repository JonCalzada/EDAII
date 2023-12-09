package proyecto1_0;
import java.util.*;

public class interno {
   
    int partition(LinkedList<String> arr, int low, int high){
        String pivot = arr.get(high);
        int i = (low-1);
        for (int j=low; j<high; j++){
            if (arr.get(j).compareTo(pivot)<=0)
            {
                i++;
                String temp = arr.get(i);
                arr.set(i,arr.get(j));
                arr.set(j,temp);
            }
        }

        String temp=arr.get(i+1);
        arr.set(i+1,arr.get(high));
        arr.set(high,temp);
        return i+1;
    }
    void sort(LinkedList<String> arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
    void printArray(LinkedList<String> arr)
    {
        for (int i=0; i<arr.size(); ++i)
            System.out.print(arr.get(i)+" ");
        System.out.println();
    }
}