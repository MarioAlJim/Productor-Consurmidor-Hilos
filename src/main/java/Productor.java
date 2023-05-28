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
                this.almacen.producirKilosTrigo(this.kilosProduccion);
                this.kilosTotalesProducidos += this.kilosProduccion;
                System.out.println(String.format("El productor %s a producido %s kilos de trigo", nombre, kilosProduccion));
                System.out.println("Kilos actuales: " + this.almacen.getKilosTrigoExistentes());
                Thread.sleep(this.tiempoEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("El productor %s finalizado y a producido %s kilos en total de trigo", nombre, kilosTotalesProducidos));
    }
}