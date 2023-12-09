#include <stdio.h>
#include <omp.h>

int main(){
	int tid,nht;
	#pragma omp parallel private(tid)
	{
		tid= omp_get_thread_num();
		nht= omp_get_num_threads();
		printf("Hola Mundo desde el hilo %d de un total de %d \n",tid,nht);
	}
	printf("Adios");
	return 0;
}
