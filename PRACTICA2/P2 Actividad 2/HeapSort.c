#include <stdio.h>
#define LENGTH 11
#include "utilidades.h"

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
	int prueba[LENGTH] = {8,25,40,17,66,72,4,29,23,5,34};
	HeapSort(prueba);
	int i;
	for(i = 0; i < LENGTH; i++)
		printf("%d ",prueba[i]);
  	return 0;
}



