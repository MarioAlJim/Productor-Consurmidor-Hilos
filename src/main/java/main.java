public class main {

    public static void main(String[] args) throws InterruptedException {

        Almacen almacen = new Almacen(10, 0, true, 1000, 10, 5000);

        Productor productor1 = new Productor(
                "Mario", almacen, 10, 5000);
        Productor productor2 = new Productor(
                "Joshua", almacen, 10, 5000);

        Consumidor consumidor1 = new Consumidor(
                "Liu", almacen, 1, 2000);
        Consumidor consumidor2 = new Consumidor(
                "Alberto", almacen, 1, 2000);
        Consumidor consumidor3 = new Consumidor(
                "Ale", almacen, 1, 2000);

        productor2.start();
        productor1.start();
        consumidor1.start();
        consumidor2.start();
        consumidor3.start();

        try {
            productor2.join();
            productor1.join();
            consumidor1.join();
            consumidor2.join();
            consumidor3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
