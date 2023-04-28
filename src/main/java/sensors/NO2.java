package sensors;

import java.util.Random;

public class NO2 implements Sensor {
    @Override
    public Float getReading() {
        return new Random().nextFloat();
    }
}
