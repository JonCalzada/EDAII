//
//  main.c
//  prsactica1
//
//  Created by Alumno on 2/5/19.
//  Copyright © 2019 alumno. All rights reserved.
//

#include <stdio.h>
#include "ordenamientos.h"


int main () {
    int arr[] = {30,60,23,1,56,77,21,11,78,40};
    int size = sizeof(arr)/sizeof(arr[0]);
    bubbleSort(arr,size);
    //quickSort(arr,0,size-1);
    printArray(arr,size);
    
    return 0;
}
