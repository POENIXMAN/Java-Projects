public class Main {
    public static void main(String[] args) {
        TrafficLightController tlc = new TrafficLightController(TrafficLights.GREEN);
        Thread tr = new Thread(tlc);
        tr.start();

        for (int i = 0;i<=9;i++){
            System.out.println(tlc.getColor());
            tlc.waitForChange();
        }
        tlc.cancel();

    }
}
