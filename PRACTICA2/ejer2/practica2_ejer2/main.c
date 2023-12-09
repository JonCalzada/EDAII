#include <stdio.h>
#define LENGTH 500
#include "utilidades.h"
#include<time.h>
#include<windows.h>


int heapSize;

void Heapify(int* A, int i)
{
	int l = 2 * i + 1;
	int r = 2 * i + 2;
	int largest;

	if(l <= heapSize && A[l] > A[i])
    	largest = l;
  	else
    	largest = i;
  	if(r <= heapSize && A[r] > A[largest])
    	largest = r;
  	if(largest != i){
    	swap(&A[i],&A[largest]);
    	printArray(A,LENGTH);
    	Heapify(A, largest);
     }

}

void BuildHeap(int* A){
	heapSize = LENGTH - 1;
  	int i;
  	for(i = (LENGTH - 1) / 2; i >= 0; i--){
		Heapify(A, i);
  	}
	printf("Termin%c de construir el HEAP \n",162);
}

void HeapSort(int* A){
	BuildHeap(A);
  	int i;
  	for(i = LENGTH - 1; i > 0; i--){
    	swap(&A[0],&A[heapSize]);
      	heapSize--;
      	printf("Iteracion HS: \n");
  		printArray(A,LENGTH);
		Heapify(A, 0);
   }
}

int main(){

	int arr[LENGTH];
	int repet=0;
	int i,j;

     srand (time(NULL));
    for (i=0; i<=LENGTH; i++) {
        arr[i] = rand()%700;
    }
        repet=1;
        while (repet==1){
            repet=0;
        for (i=0; i<=LENGTH; i++) {
            for (j=i+1; j<=LENGTH; j++) {

                if (arr[i] == arr[j] && arr[i] !=99) { //Repetición
                    printf("Hay repeticion. Cambio de %d por %d \n", arr[i], arr[i]+1);
                    arr[i] = arr[i] +1;
                    repet = 1;

                }
                if (arr[i] == arr[j] && arr[i] ==99) { //Repetición
                    arr[i] = arr[i] -rand()%99+1;
                    printf("Hay repeticion de 29. Cambio de 29 por %d \n", arr[i]);
                    repet = 1;
                }
            }
        }
    }






	HeapSort(arr);
	for(i = 0; i < LENGTH; i++)
		printf("%d ",arr[i]);
  	return 0;
}

