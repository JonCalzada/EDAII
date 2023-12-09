#include <stdio.h>
#include <omp.h>
int main(){
	#pragma omp parallel
	{
	int i;
	printf("Hola mundo\n");
	for(i=0;i<10;i++)
		printf("Iteracion: %d \n",i);
	}
	printf("Adios \n");
	return 0;
}
//actividad7
/*
int main(){
	#pragma omp parallel
	{
		printf("Hola mundo \n");
		#pragma omp for
		for(i=0;i<10;i++)
			printf("Iteracion : %d \n",i);
	}
	printf("Adios \n");
	return 0;
}

*/
