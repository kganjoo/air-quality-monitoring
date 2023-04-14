package components.aqi;

import constants.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class AQICalculator {

    private IndexCalculator PM25Index;
    private  IndexCalculator NO2Index;
    private  IndexCalculator PM10Index;
    private IndexCalculator COIndex;
    private IndexCalculator CO2Index;
    private IndexSelector indexSelector;
    private List<Float>pollutantIndexes;
    private Float aqiIndex;

    public AQICalculator() {

        PM25Index = new IndexCalculator("PM25", Constants.PM25_INDEX_FILE_PATH);
        NO2Index = new IndexCalculator("NO2",Constants.NO2_INDEX_FILE_PATH);
        PM10Index = new IndexCalculator("PM10",Constants.PM10_INDEX_FILE_PATH);
        COIndex = new IndexCalculator("CO",Constants.CO2_INDEX_FILE_PATH);
        CO2Index = new IndexCalculator("CO2",Constants.CO_INDEX_FILE_PATH);
        indexSelector = new IndexSelector();
        pollutantIndexes = Collections.synchronizedList(new ArrayList<Float>());
    }


    public void calculatePM25Index(Float reading) {
        Float pm25Index = PM25Index.getPollutantIndex(reading);
        pollutantIndexes.add(pm25Index);
        triggerMaxIndex();
    }

    public void calculatePM10Index(Float reading)  {
        Float pm10Index = PM10Index.getPollutantIndex(reading);
        pollutantIndexes.add(pm10Index);
        triggerMaxIndex();
    }


    public void calculateCOIndex(Float reading)  {
        Float cOIndex = COIndex.getPollutantIndex(reading);
        pollutantIndexes.add(cOIndex);
        triggerMaxIndex();
    }

    public void calculateNO2Index(Float reading)  {
        Float no2Index = NO2Index.getPollutantIndex(reading);
        pollutantIndexes.add(no2Index);
        triggerMaxIndex();
    }

    public void calculateCO2Index(Float reading)  {
        Float co2Index = CO2Index.getPollutantIndex(reading);
        pollutantIndexes.add(co2Index);
        triggerMaxIndex();
    }


    private void triggerMaxIndex() {
        while (true) {
            if (pollutantIndexes.size() == 5) {
                this.aqiIndex = indexSelector.calculateIndex(pollutantIndexes);
                System.out.println("Calculated aqi index is " + this.aqiIndex);
            }
            System.out.println("Ignoring trigger as size list is not 4");
        }
    }

    public Float getAqiIndex() {
        return this.aqiIndex;
    }
}
