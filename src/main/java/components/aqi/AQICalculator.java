package components.aqi;

import components.Config;
import components.ControlUnit;
import constants.Constants;
import java.util.concurrent.*;

public class AQICalculator {

    private IndexCalculator PM25Index;
    private  IndexCalculator NO2Index;
    private  IndexCalculator PM10Index;
    private IndexCalculator COIndex;
    private IndexCalculator CO2Index;
    private IndexSelector indexSelector;
    private Float aqiIndex;
    private final ExecutorService executorService;

    public AQICalculator(ControlUnit controlUnit) {
        this.executorService = Config.getExecutorService();
        PM25Index = new IndexCalculator("PM25", Constants.PM25_INDEX_FILE_PATH);
        NO2Index = new IndexCalculator("NO2",Constants.NO2_INDEX_FILE_PATH);
        PM10Index = new IndexCalculator("PM10",Constants.PM10_INDEX_FILE_PATH);
        COIndex = new IndexCalculator("CO",Constants.CO_INDEX_FILE_PATH);
        CO2Index = new IndexCalculator("CO2",Constants.CO2_INDEX_FILE_PATH);

        indexSelector = new IndexSelector(controlUnit);
        executorService.submit(indexSelector);

    }


    public void calculatePM25Index(Float reading) {
        Float pm25Index = PM25Index.getPollutantIndex(reading);
        indexSelector.addReading(pm25Index);

    }

    public void calculatePM10Index(Float reading)  {
        Float pm10Index = PM10Index.getPollutantIndex(reading);
        indexSelector.addReading(pm10Index);
    }


    public void calculateCOIndex(Float reading)  {
        Float cOIndex = COIndex.getPollutantIndex(reading);
        indexSelector.addReading(cOIndex);
    }

    public void calculateNO2Index(Float reading)  {
        Float no2Index = NO2Index.getPollutantIndex(reading);
        indexSelector.addReading(no2Index);

    }

    public void calculateCO2Index(Float reading)  {
        Float co2Index = CO2Index.getPollutantIndex(reading);
        indexSelector.addReading(co2Index);

    }

    public Float getAqiIndex() {
        return this.aqiIndex;
    }
}
