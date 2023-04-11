package main.java.sensors;

import java.util.Random;

public class CO2 implements Sensor {
    @Override
    public Float getReading() {
        return new Random().nextFloat();
    }
}
