#include <stdio.h>

int  main()
{
	int j,n=25,i,k=0,num;
	int arr1[25],arr2[100],arr3[25];
	printf("\n\n\t       ----------Ordenamiento por conteo----------    \n\n\n");
	printf("\n Ingresa el valor :\n");
	for (i=0;i<=25;i++)
	{
		printf("\n Ingrese el elemento: %d \n",i+1);
		scanf("%d",&num);
        // num = rand () % 11 + 20;;
        if((num>=55)&&(num<=63)){
		arr3[i]=num;
		if(arr3[i] > k)
		{
			k = arr3[i];
		}
	}
	else{
            printf("\n Valor invalido \n");
            i=i-1;
	}
	}



	for(i =0;i<=k;i++)
		arr2[i]=0;
    // Se realiza el conteo de datos repetidos
	for(j =1;j<=n;j++)
		arr2[arr3[j]]=arr2[arr3[j]] + 1;

	for(i =1;i<=k;i++){
		arr2[i]=arr2[i]+arr2[i-1];
	//printf("%d",arr2[i]);
	}


	for(j=n;j>=1;j--)
	{
		arr1[arr2[arr3[j]]] = arr3[j];
		arr2[arr3[j]] = arr2[arr3[j]] - 1;
	}
	printf("\nEl arreglo ordenado es : ");
	for(i=1;i<=n;i++)
	{
		printf("\t");
		printf("%d",arr1[i]);
	}


return 0;
}




