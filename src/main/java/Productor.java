public class Productor extends Thread {
    private String nombre;
    private Almacen almacen;
    private int kilosProduccion;
    private int kilosTotalesProducidos;
    private int tiempoEspera;

    public Productor(String nombre, Almacen almacen, int kilosProduccion, int tiempoEspera) {
        this.nombre = nombre;
        this.almacen = almacen;
        this.kilosProduccion = kilosProduccion;
        this.kilosTotalesProducidos = 0;
        this.tiempoEspera = tiempoEspera;
    }

    public void run() {
        while (this.almacen.isAbierto()) {
            try {
                Thread.sleep(this.tiempoEspera);
                this.kilosTotalesProducidos += this.almacen.producirKilosTrigo(this.kilosProduccion, this.nombre);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("El productor %s finalizado y a producido %s kilos en total de trigo", nombre, kilosTotalesProducidos));
    }
}