package main.java.sensors;

import java.util.Random;

public class NO2 implements Sensor {
    @Override
    public Float getReading() {
        // TODO: 4/8/23  check range to return
        return new Random().nextFloat();
    }
}
