#include<stdio.h>
#include "ordenamientos.h"
#include<time.h>

    int main (){
    int repet=0;
    int arr[10]; //Indices de 0 a 9, resultan 10 numeros
    int i, j;

    srand (time(NULL));
    for (i=0; i<=10; i++) {
        arr[i] = rand()%19;
    }


        repet=1;
        while (repet==1){
            repet=0;
        for (i=0; i<=10; i++) {
            for (j=i+1; j<=10; j++) {

                if (arr[i] == arr[j] && arr[i] !=19) { //Repetición
                    printf("Hay repeticion. Cambio de %d por %d \n", arr[i], arr[i]+1);
                    arr[i] = arr[i] +1;
                    repet = 1;

                }
                if (arr[i] == arr[j] && arr[i] ==19) { //Repetición
                    arr[i] = arr[i] -rand()%19+1;
                    printf("Hay repeticion de 29. Cambio de 29 por %d \n", arr[i]);
                    repet = 1;
                }
            }
        }
    }

    int size =sizeof(arr)/sizeof(arr[0]);
    //bubbleSort(arr,size);
    quickSort(arr,0,size-1);
    printArray(arr,size);

    }
