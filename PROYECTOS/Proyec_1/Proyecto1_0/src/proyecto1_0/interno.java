package proyecto1_0;
import java.util.*;

public class interno {
   
    int partition(LinkedList<String> arr, int low, int high){
        String pivot = arr.get(high);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++){
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j).compareTo(pivot)<=0)
            {
                i++;
                 // swap arr[i] and arr[j]
                String temp = arr.get(i);
                arr.set(i,arr.get(j));
                arr.set(j,temp);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
       /* int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;*/

        String temp=arr.get(i+1);
        arr.set(i+1,arr.get(high));
        arr.set(high,temp);
        return i+1;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(LinkedList<String> arr, int low, int high){
        if (low < high){
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);
            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
     /* A utility function to print array of size n */
    void printArray(LinkedList<String> arr)
    {
        for (int i=0; i<arr.size(); ++i)
            System.out.print(arr.get(i)+" ");
        System.out.println();
    }
}

