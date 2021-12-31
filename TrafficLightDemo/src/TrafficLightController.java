enum TrafficLights{
    GREEN(10000),YELLOW(3000),RED(12000);
    private int delay;
    TrafficLights(int d){this.delay = d;}
    int getDelay(){return this.delay;}
}

public class TrafficLightController implements Runnable {
    private TrafficLights tl;
    private boolean changed = false;
    private boolean stop = false;

    TrafficLightController(TrafficLights init){
        tl = init;
    }
    TrafficLightController(){
        tl = TrafficLights.RED;
    }

    public void run(){
        while (!stop){
            try{
                Thread.sleep(tl.getDelay());
            }catch (InterruptedException exc){
                System.out.println(exc);
            }
            changeColor();
        }
    }

    synchronized void changeColor(){
        switch (tl){
            case GREEN -> tl = TrafficLights.YELLOW;
            case YELLOW -> tl = TrafficLights.RED;
            case RED -> tl = TrafficLights.GREEN;
        }
        changed = true;
        notify();
    }

    synchronized void waitForChange(){
        try{
            while(!changed){
                wait();
            }
            changed = false;
        }catch (InterruptedException exc){
            System.out.println(exc);
        }
    }

    synchronized TrafficLights getColor(){
        return tl;
    }
    synchronized void cancel(){
        stop = true;
    }
}
