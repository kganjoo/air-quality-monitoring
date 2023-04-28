package sensors;

import java.util.Random;

public class CO implements Sensor {

    @Override
    public Float getReading() {
        return new Random().nextFloat(); 
    }
}
