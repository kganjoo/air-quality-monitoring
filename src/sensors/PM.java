package sensors;

import java.util.Random;

public class PM implements Sensor {
    @Override
    public Float getReading() {
        return new Random().nextFloat();
    }
}
