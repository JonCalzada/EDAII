#include<stdio.h>
#include<stdlib.h>

struct  nodo
{
	int dato ;
	struct nodo*sig;
}*inicio=NULL;

void radixSort();
int masSignificativo();
main()
{
	struct nodo*temp,*q;
	int conta,n,num_item;

	printf("Ingresar los datos : ");
	scanf("%d", &n);

	for(conta=0;conta<n;conta++)
	{
		printf(" %d : ",conta+1);
		scanf("%d",&num_item);

		temp= malloc(sizeof(struct nodo));
		temp->dato=num_item;
		temp->sig=NULL;

		if(inicio==NULL)
			inicio=temp;
		else
		{
			q=inicio;
			while(q->sig!=NULL)
				q=q->sig;
			q->sig=temp;
		}
	}

	radixSort();
	printf("Sorted List is :\n");

	q=inicio;
	while( q !=NULL)
	{
		printf("%d ", q->dato);
		q = q->sig;
	}
	printf("\n");

}

void radixSort()
{
	int conta,k,dig,menosSig,masSig;
	struct nodo*p, *post[10], *frente[10];

	menosSig=1;
	masSig=masSignificativo(inicio);

	for(k=menosSig; k<=masSig; k++)
	{
		for(conta=0; conta<=9 ; conta++)
		{
			post[conta] = NULL;
			frente[conta] = NULL ;
		}

		for( p=inicio; p!=NULL; p=p->sig )
		{
			dig = clave(p->dato, k);

			if(frente[dig] == NULL)
				frente[dig] = p ;
			else
				post[dig]->sig = p;
			post[dig] = p;
		}

		conta=0;
		while(frente[conta] == NULL)
			conta++;
		inicio = frente[conta];
		while(conta<9)
		{
			if(post[conta+1]!=NULL)
				post[conta]->sig=frente[conta+1];
			else
				post[conta+1]=post[conta];
			conta++;
		}
		post[9]->sig=NULL;
	}
}

int masSignificativo()
{
	struct nodo*p=inicio;
	int gran=0,ndato=0;

	while(p != NULL)
	{
		if(p ->dato > gran)
			gran = p->dato;
		p = p->sig ;
		//printf("%d",large[p->next]);
	}

	while(gran != 0)
	{
		ndato++;
		gran = gran/10 ;
	}
	return(ndato);
}
