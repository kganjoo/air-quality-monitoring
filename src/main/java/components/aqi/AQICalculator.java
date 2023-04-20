package components.aqi;

import components.Config;
import components.ControlUnit;
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
    private BlockingQueue<Float>pollutantIndexes;
    private Float aqiIndex;
    private final ExecutorService executorService;

    public AQICalculator(ControlUnit controlUnit) {
        this.executorService = Config.getExecutorService();
        PM25Index = new IndexCalculator("PM25", Constants.PM25_INDEX_FILE_PATH);
        NO2Index = new IndexCalculator("NO2",Constants.NO2_INDEX_FILE_PATH);
        PM10Index = new IndexCalculator("PM10",Constants.PM10_INDEX_FILE_PATH);
        COIndex = new IndexCalculator("CO",Constants.CO_INDEX_FILE_PATH);
        CO2Index = new IndexCalculator("CO2",Constants.CO2_INDEX_FILE_PATH);
        pollutantIndexes = new ArrayBlockingQueue<>(5);
        indexSelector = new IndexSelector(pollutantIndexes,controlUnit);
        executorService.submit(indexSelector);

    }


    public void calculatePM25Index(Float reading) {
        Float pm25Index = PM25Index.getPollutantIndex(reading);
        System.out.println("Calculated index value for PM25 is "+ pm25Index);
        try {
            pollutantIndexes.put(pm25Index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void calculatePM10Index(Float reading)  {
        Float pm10Index = PM10Index.getPollutantIndex(reading);
        System.out.println("Calculated index value for PM10 is "+ pm10Index);
        try {
            pollutantIndexes.put(pm10Index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void calculateCOIndex(Float reading)  {
        Float cOIndex = COIndex.getPollutantIndex(reading);
        System.out.println("Calculated index value for CO is "+ cOIndex);
        try {
            pollutantIndexes.put(cOIndex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calculateNO2Index(Float reading)  {
        Float no2Index = NO2Index.getPollutantIndex(reading);
        System.out.println("Calculated index value for NO2 is "+ no2Index);
        try {
            pollutantIndexes.put(no2Index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void calculateCO2Index(Float reading)  {
        Float co2Index = CO2Index.getPollutantIndex(reading);
        System.out.println("Calculated index value for CO2 is "+ co2Index);
        try {
            pollutantIndexes.put(co2Index);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }





    public Float getAqiIndex() {
        return this.aqiIndex;
    }
}
