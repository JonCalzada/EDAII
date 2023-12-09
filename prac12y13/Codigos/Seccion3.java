class A extends Thread{
	public void run(){
	// code for the new thread to execute
	A a= new A();
	a.start();
	}
}

class B extends Perro implements Runable{
	
}

class Seccion3{
	public static void main(String args []){
		while(true){
			outsideCS();
			wantToEnterCS(i);//pre-control
			insideCS();
			finishedInCS(i);//post-control
		}
	}
}

