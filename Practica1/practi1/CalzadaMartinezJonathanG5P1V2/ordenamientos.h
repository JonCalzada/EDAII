#include "utilidades.h"

void quickSort(int arr[], int low, int high){
	if (low < high){
		int pi = partition(arr, low, high);
        	printSubArray(arr,low,pi-1);
        	printSubArray(arr,pi+1,high);
			quickSort(arr, low, pi - 1);
        	quickSort(arr, pi + 1, high);
    	}

}

int partition (int arr[], int low, int high){
   	int pivot = arr[high];
	printf("Pivote:%d \n",pivot);
	int j,i = (low - 1);
   	for (j = low; j <= high- 1; j++){
       	if (arr[j] <= pivot){
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}



void bubbleSort(int a[], int size){
	int intercambios=0;
	int comp=0,cambi=0;
	int i,j,n;
	n= size;
	for(i=n-1;i>0;i--){
		int cambios=0;
		for(j=0; j<i; j++){
        comp=comp+1;
			if(a[j]>a[j+1]){
				swap(&a[j], &a[j+1]);
				cambios = 1;
        cambi++;
			}
			printArray(a,size);
		}
		if(cambios==0)
			break;
	printArray(a,size);
	printf("\t Fin Iteracion \n");
	}
	printf("\n comparaciones %d,  cambios  %d \n\t",comp,cambi);
}


