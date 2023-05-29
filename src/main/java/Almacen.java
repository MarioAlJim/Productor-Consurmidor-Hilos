public class Almacen {
    private int kilosTrigoExistentes;
    private int operacionesDia;
    private boolean abierto;
    private int maximoOperaciones;
    private int minimoTrigo;
    private int maximoTrigo;

    public Almacen(int kilosTrigoExistentes, int operacionesDia, boolean estatus, int maximoOperaciones, int minimoTrigo, int maximoTrigo) {
        this.kilosTrigoExistentes = kilosTrigoExistentes;
        this.operacionesDia = operacionesDia;
        this.abierto = estatus;
        this.maximoOperaciones = maximoOperaciones;
        this.minimoTrigo = minimoTrigo;
        this.maximoTrigo = maximoTrigo;
    }

    public synchronized int getKilosTrigoExistentes() {
        return kilosTrigoExistentes;
    }

    public synchronized int producirKilosTrigo(int kilosTrigoAgregados, String nombre) throws InterruptedException {
        while (this.abierto && (kilosTrigoExistentes + kilosTrigoAgregados) > maximoTrigo) {
            wait();
        }
        if(this.abierto) {
            this.kilosTrigoExistentes += kilosTrigoAgregados;
            System.out.println(String.format("%s El productor %s a producido %s kilos de trigo , Kilos actuales: %s", operacionesDia, nombre, kilosTrigoAgregados, getKilosTrigoExistentes()));
            agregarOperacion();
            return kilosTrigoAgregados;
        }
        return 0;
    }

    public synchronized int venderKilosTrigo(int kilosTrigoVendidos, String nombre) throws InterruptedException {
        while (this.abierto && (kilosTrigoExistentes - 10) < minimoTrigo) {
            wait();
        }
        if (this.abierto) {
            this.kilosTrigoExistentes -= kilosTrigoVendidos;
            System.out.println(String.format("%s El consumidor %s a comprado %s kilos de trigo, kilos actuales: %s ", operacionesDia, nombre, kilosTrigoVendidos, getKilosTrigoExistentes()));
            agregarOperacion();
            return kilosTrigoVendidos;
        }
        return 0;
    }

    public synchronized int getOperacionesDia() {
        return operacionesDia;
    }

    public synchronized void agregarOperacion() {
        this.operacionesDia += 1;
        if (this.operacionesDia == this.maximoOperaciones) {
            System.out.println("Kilos finales: " + getKilosTrigoExistentes());
            this.abierto = false;
        }
        notify();
    }

    public boolean isAbierto() {
        return abierto;
    }

}