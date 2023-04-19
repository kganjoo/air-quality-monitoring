package components.aqi;

import components.Config;
import components.ControlUnit;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class IndexSelector implements Runnable {

    ControlUnit controlUnit;

    private BlockingQueue<Float> readings;

    IndexSelector(BlockingQueue<Float> readings) {
        this.readings = readings;
        this.controlUnit = new ControlUnit();
    }


    public void calculateIndex() {
        try {
            while(true) {
                if(readings.size()==5) {
                    Float max = Float.MIN_VALUE;
                    while (readings.size() > 0) {
                        Float curr = readings.remove();
                        max = Math.max(curr, max);

                    }
                    System.out.println("Calling control unit with aqiIndex "+max);

                    controlUnit.getControlAction(max);

                }
                else {
                    if(Config.getRoundValue()>=5)
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
