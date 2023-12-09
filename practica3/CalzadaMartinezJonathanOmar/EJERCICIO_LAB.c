
#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#define max 25
int main() {
    int i=0,num=0,k=0,n=55;
    int arr1[25],arr2[8],orden[25],indi[8];
    for(k=0;k<25;k++){
        arr1[k]=0;
        arr2[k]=0;
        orden[k]=0;
    }


    for (i=0;i<max ; i++) {
         printf("\n Ingrese el elemento: %d \n",i);
         scanf("%d",&num);
        // num = rand () % 11 + 20;;
        if((num>=55)&&(num<=63)){
        arr1[i]=num;
            switch (num) {
                case 55:
                    arr2[0]=arr2[0]+1;
                    break;
                case 56:
                        arr2[1]=arr2[1]+1;
                    break;
                case 57:
                        arr2[2]=arr2[2]+1;
                    break;
                case 58:
                        arr2[3]=arr2[3]+1;
                    break;
                case 59:
                        arr2[4]=arr2[4]+1;
                    break;
                case 60:
                        arr2[5]=arr2[5]+1;
                    break;
                case 61:
                        arr2[6]=arr2[6]+1;
                    break;
                case 62:
                        arr2[7]=arr2[7]+1;

                    break;
                case 63:
                        arr2[8]=arr2[8]+1;
                    break;
                default:
                    break;
            }
        }
        else
            printf("\n Valor invalido \n");
    }
    printf("Los datos son: \n");
    for(i=0;i<25;i++){
    printf(" %d, ",arr1[i]);
    }

    for (i=0; i<8;i++ ) {
        printf("\n Cantidad de %d repetidos son: %d \n", n, arr2[i]);
        n++;
    }
    return 0;

}
