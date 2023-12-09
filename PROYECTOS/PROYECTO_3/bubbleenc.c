#include<stdio.h>
#include<stdlib.h>
#include<omp.h>
#include<time.h>
#define tam 200500
double bubble_sort (int *array);
double parallel_bubble_sort (int *array);

int main(){
    int array[tam],array2[tam];
    srand(time(NULL));
    for(int i=0;i<tam;i++){
        array[i]=1+rand()%1000;
        array2[i]=array[i];
    }
    printf("Tiempo En Serie: %.20lf",bubble_sort(array));
    printf("\nTiempo En Paralelo: %.20lf",parallel_bubble_sort(array2));
}


double bubble_sort (int *array){
    int temp;
    double fin, inicio;
    inicio=omp_get_wtime();
        for (int i =0; i < tam-1 ;  i++){
            for (int j =0; j < tam-1-i; j++){
                if (array[j]>array[j+1]){
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    fin=omp_get_wtime();
    return fin-inicio;
}

double parallel_bubble_sort (int *array){
    int temp;
    double fin,inicio;
    inicio=omp_get_wtime();
    #pragma omp parallel for private(temp)
        for (int i =0; i < tam - 1 ;  i++){
            for (int j =0; j < tam- 1 - i; j++){
                if (array[j+1]<array[j]){
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    fin=omp_get_wtime();
    return fin-inicio;
}
