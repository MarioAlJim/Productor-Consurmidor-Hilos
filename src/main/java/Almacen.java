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

    public synchronized void producirKilosTrigo(int kilosTrigoAgregados) throws InterruptedException {
        while (this.abierto && (kilosTrigoExistentes + kilosTrigoAgregados) > maximoTrigo) {
            wait();
        }
        if (this.abierto) {
            this.kilosTrigoExistentes += kilosTrigoAgregados;
            agregarOperacion();
        }
    }

    public synchronized void venderKilosTrigo(int kilosTrigoVendidos) throws InterruptedException {
        while (this.abierto && (kilosTrigoExistentes - 10) < minimoTrigo) {
            wait();
        }
        if (this.abierto) {
            this.kilosTrigoExistentes -= kilosTrigoVendidos;
            agregarOperacion();
        }
    }

    public synchronized int getOperacionesDia() {
        return operacionesDia;
    }

    public synchronized void agregarOperacion() {
        this.operacionesDia += 1;
        System.out.println("Kilos actuales: " + getKilosTrigoExistentes());
        if (this.operacionesDia == this.maximoOperaciones)
            this.abierto = false;
    }

    public boolean isAbierto() {
        return abierto;
    }

}