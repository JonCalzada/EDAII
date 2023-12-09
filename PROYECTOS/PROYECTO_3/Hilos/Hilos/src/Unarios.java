public class Unarios{
    public void operacion() {
             int x = 8;
             int y = 12;
             int z = 14;
             try {
                 System.out.println("Antes de calcular voy a dormir");
                 Thread.sleep(5000);
             } catch (InterruptedException exc) {
                 System.out.println("Hilo principal interrumpido.");
             }
             System.out.println("ya desperté");
             z += ++y * 10 + --y * y - 30 / y++ % 3 + 2*x  ; 
             System.out.println(z);
    }
    public static void main(String[] args) {
            Unarios nuevo = new Unarios();
            nuevo.operacion();
    }
}

