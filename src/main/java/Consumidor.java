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
                Thread.sleep(this.tiempoEspera);
                this.kilosTotalesComprados += this.almacen.venderKilosTrigo(this.kilosCompra, this.nombre);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("El consumidor %s a finalizado y a comprado %s kilos en total de trigo", nombre, kilosTotalesComprados));
    }
}
