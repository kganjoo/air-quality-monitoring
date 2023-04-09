package components.aqi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class AQICalculator {


    private IndexCalculator PM25Index;
    private   IndexCalculator NO2Index;
    private   IndexCalculator PM10Index;
    private  IndexCalculator COIndex;
    private MaxIndexCalculator maxIndex;
    private List<Float>pollutantIndexes;
    private Float aqiIndex;

    public AQICalculator() {

        PM25Index = new IndexCalculator("PM25", "");
        NO2Index = new IndexCalculator("NO2","");
        PM10Index = new IndexCalculator("PM10","");
        COIndex = new IndexCalculator("CO","src/main/resources/COIndex.csv");
        maxIndex = new MaxIndexCalculator();
        pollutantIndexes = Collections.synchronizedList(new ArrayList<Float>());
    }




    public void calculatePM25Index(Float reading) throws ExecutionException, InterruptedException {
        Float pm25Index = PM25Index.getPollutantIndex(reading);
        pollutantIndexes.add(pm25Index);
        triggerMaxIndex();
    }

    public void calculatePM10Index(Float reading) throws ExecutionException, InterruptedException {
        Float pm10Index = PM10Index.getPollutantIndex(reading);
        pollutantIndexes.add(pm10Index);
        triggerMaxIndex();
    }

    public void calculateCOIndex(Float reading) throws ExecutionException, InterruptedException {
        Float cOIndex = COIndex.getPollutantIndex(reading);
        pollutantIndexes.add(cOIndex);
        triggerMaxIndex();
    }

    public void calculateNO2Index(Float reading) throws ExecutionException, InterruptedException {
        Float no2Index = NO2Index.getPollutantIndex(reading);
        pollutantIndexes.add(no2Index);
        triggerMaxIndex();
    }


    public void triggerMaxIndex() throws ExecutionException, InterruptedException {
        if(pollutantIndexes.size()==4){
            List<Float> indexes = new ArrayList<>();
            this.aqiIndex = maxIndex.calculateMaxIndex(indexes);
        }
        //ignore triggers
    }

    public Float getAqiIndex() {
        return this.aqiIndex;
    }
}
