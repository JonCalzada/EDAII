#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>
#define n 10

void llenaArreglo(int *a);
void suma(int *a,int *b,int *c );
void suma62(int *a, int *b, int *c);
void suma8(int *a, int *b, int *c);

int main(){
	int max,*a,*b,*c;

	a=(int *)malloc(sizeof(int) *n);
	b=(int *)malloc(sizeof(int) *n);
	c=(int *)malloc(sizeof(int) *n);

	llenaArreglo(a);
	llenaArreglo(b);
    printf("\nPrimero\n");
	suma(a,b,c);
	printf("\nParallel 1\n");
	suma62(a,b,c);
	printf("\nParallel 2\n");
	suma8(a,b,c);
}

void llenaArreglo(int *a){
	int i;
	for(i=0;i<n;i++){
		a[i]=rand()%n;
		printf("%d\t", a[i]);
	}
	printf("\n");
}
//version original

void suma(int *A, int *B,int *C){
	int i;
	for(i=0;i<n;i++){
		C[i]=A[i] + B[i];
		printf("%d\t",C[i]);
	}
}


//actividad 6-2
/*
for(i=inicio:i<fin;i++)
	C[i]=A[i]+B[i];

inicio=tid*5;
fin=(tid+1)*5-1;
*/

void suma62(int *A, int *B,int *C){
	int i,tid,inicio,fin;
	omp_set_num_threads(2);
	#pragma omp parallel private(inicio,fin,tid,i)
	{
		tid= omp_get_thread_num();
		inicio= tid*5;
		fin=(tid+1)*5-1;
		for(i=inicio;i<fin;i++){
			C[i]=A[i]+B[i];
			printf("hilo %d calculo C[%d] = %d\n",tid,i,C[i]);
		}
	}
}



//parte de la actividad 8
void suma8(int *A, int *B, int *C){
	int i,tid;
	#pragma omp parallel private(tid)
	{
		tid= omp_get_thread_num();
		#pragma omp for
		for(i=0;i<n;i++){
			C[i]=A[i]+B[i];
			printf("hilo %d calculo C[%d] = %d\n",tid,i,C[i]);
		}
	}
}

/*
for (i=0;i<10;i++){
	C[i]=A[i]+B[i];
printf(" hilo %d calculo[%d]= %d\n " +tid,i, c[i]);
}
*/
