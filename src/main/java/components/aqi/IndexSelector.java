package components.aqi;

import java.util.Arrays;
import java.util.List;

public class IndexSelector {


    public Float calculateIndex(List<Float>readings){

        Float max = Float.MIN_VALUE;
        for(Float reading : readings){
            max = Math.max(reading,max);
        }
        return max;
    }
}
