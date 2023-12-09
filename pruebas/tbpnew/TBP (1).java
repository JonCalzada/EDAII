public class TBP{
	int m;
	public NodoBP raiz;

	public TBP(int m){
		this.m=m;
		raiz=new NodoBP();
		raiz.setM(m);
	}
	public void add(int n){
		if(find(n)){
			System.out.println("La clave ya existe");
		}
		else{

		}
	}
	public boolean find(int value){
		if(raiz.child.isEmpty()==true && raiz.key.isEmpty()==true){
			retunr false;
		}
		return find(value, raiz);
	}

	private boolean find(int v, NodoBP n){
		if(n==null){
			return false;
		}
		int i;
		if(n.getKey(0) > v)
			return find(v,n.getChild(0));
		for(i=0;i<n.key.size()-1;i++){
			if(n.getKey(i)==v)
				if(n.isLeaft==true)
					return true;
				else{
					return find(n.getChild(i));
				}
			if(n.getKey(i)< v && n.getKey(i+1)>v){
				return find(v,n.getChild(i+1));
			}
		}
		/*if(n.getKey(i)==v)
				if(n.isLeaft==true)
					return true;*/
		/*else{
			if(n.getKey(i)<v)
				return find(v,n.getChild(i+1));
			else
				return find(v,n.getChild(i));
		}*/
	}
}