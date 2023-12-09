import java.util.ArrayList;

public class NodoBP{
	static int m;
	ArrayList<Integer> key;
	ArrayList<NodoBP> child;
	boolean leaf;
	NodoBP padre;

	public NodoBP(){
		this.key=new ArrayList();
		this.child= new ArrayList();
		this.padre=null;
		this.leaf=true;
	}
	public boolean isLeaft(){
		if(this.child==null){
			return true;
		}
		else{
			return false;
		}
	}
	public int getKey(int i){
		return this.key.get(i);
	}
	public BNode getChild(int i){
		try{
			this.child.get(i);
		}catch(Exception e){
			return null;
		}
		return this.child.get(i);
	}
	public void setM(int m){
		this.m=m;
	}
	public void setKeys(ArrayList<Integer> list){

	}
	public void setChildren(ArrayList<NodoBP> list){
		this.child=list;
	}
	public void getChildIndex(){
		if(this.padre==null){
			return -1;
		}
		else{
			NodoBP father=padre;
			for(int i=0;i<father.child.size(i))
				if(father.child.get(i)==this)
					retunr i;
		}
		return -1;
	}
	public void mostrarLLves(){
		for(int i=0;i<key.size();i++)
			System.out.print(key.get(i)+" ");
	}
}