public class Consumidor extends Thread {
    private String nombre;
    private Almacen almacen;
    private int kilosCompra;
    private int kilosTotalesComprados;
    private int tiempoEspera;

    public Consumidor(String nombre, Almacen almacen, int kilosComprados, int tiempoEspera) {
        this.nombre = nombre;
        this.almacen = almacen;
        this.kilosCompra = kilosComprados;
        this.kilosTotalesComprados = 0;
        this.tiempoEspera = tiempoEspera;
    }

    public void run() {
        while (this.almacen.isAbierto()) {
            try {
                this.almacen.venderKilosTrigo(this.kilosCompra);
                this.kilosTotalesComprados += this.kilosCompra;
                System.out.println(String.format("El consumidor %s a comprado %s kilos de trigo", nombre, kilosCompra));
                System.out.println("Kilos actuales: " + this.almacen.getKilosTrigoExistentes());
                Thread.sleep(this.tiempoEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("El consumidor %s a finalziado y a comprado %s kilos en total de trigo", nombre, kilosTotalesComprados));
    }
}
