package main.java.sensors;

import java.util.Random;

public class TempHum implements Sensor{
    @Override
    public Float getReading() {
        return new Random().nextFloat();
    }
}
