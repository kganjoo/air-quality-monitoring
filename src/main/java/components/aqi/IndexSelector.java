package components.aqi;

import components.*;
import java.util.concurrent.BlockingQueue;

public class IndexSelector implements Runnable {

    ControlUnit controlUnit;

    private BlockingQueue<Float> readings;

    IndexSelector(BlockingQueue<Float> readings, ControlUnit controlUnit) {
        this.readings = readings;
        this.controlUnit = controlUnit;
    }

    public void calculateIndex() {
        try {
            while(true) {
                if(readings.size()==5) {
                    Float aqi = Float.MIN_VALUE;
                    while (readings.size() > 0) {
                        Float curr = readings.remove();
                        aqi = Math.max(curr, aqi);
                    }
                    Display.addToDisplay(new DisplayValue(Display.AQI_MARKER,aqi));
                    Thread.sleep(1000);
                    controlUnit.triggerFan(aqi);
                    try {
                        Config.getBarrier().await();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }


                }
                else {
                    if(Config.getRoundValue()>72)
                        break;
                    Thread.sleep(1000);
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        calculateIndex();
    }
}
