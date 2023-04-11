package main.java.sensors;

import java.util.Random;

public class CO implements Sensor {



    @Override
    public Float getReading() {
        // TODO: 4/8/23  check range to return 
        return new Random().nextFloat(); 
    }
}
