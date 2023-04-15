package components;

import components.smoothener.DataSmoothener;
import constants.Constants;
import sensors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataPreprocessor {
    private int round;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private DataSmoothener smoothener;

    public void Init(int round) throws IOException {
        this.smoothener = new DataSmoothener(Constants.window_size);
        this.round = round;
        getTemperature();
        getHumidity();
        getCO2GasConcentration();
        getNO2GasConcentration();
        getCOGasConcentration();
        getPM10();
        getPM25();
    }

    public void getTemperature() throws IOException {
        executorService.submit(()->{
            
            TempHum temp = new TempHum();
            temp.getReading();
            try{
                Float value = getValueFromFile(Constants.TEMP_INPUT);

                smoothener.AvgCalcTemp(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void getHumidity() throws IOException {
        executorService.submit(()->{
            
            TempHum hum = new TempHum();
            hum.getReading();
            try {
                Float value = getValueFromFile(Constants.HUM_INPUT);
                smoothener.AvgCalcHum(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void getCOGasConcentration() throws IOException {
        executorService.submit(()->{
            
            CO co = new CO();
            co.getReading();
            try {
                Float value = getValueFromFile(Constants.CO_INPUT);
                smoothener.AvgCalcCO(value);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void getCO2GasConcentration() throws IOException {
        executorService.submit(()->{
            
            CO2 co2 = new CO2();
            co2.getReading();
                try {
                    Float value = getValueFromFile(Constants.CO2_INPUT);
                    smoothener.AvgCalcCO2(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    public void getNO2GasConcentration() throws IOException {
        executorService.submit(()->{
            
            NO2 no2 = new NO2();
            no2.getReading();
                try {
                    Float value = getValueFromFile(Constants.NO2_INPUT);
                    smoothener.AvgCalcNO2(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    public void getPM25() throws IOException {
        executorService.submit(()->{
            
            PM pm25 = new PM();
            pm25.getReading();
                try {
                    //read from file
                    Float value = getValueFromFile(Constants.PM25_INPUT);
                    smoothener.AvgCalcPM25(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    public void getPM10() throws IOException {
        executorService.submit(()->{
            
        PM pm10 = new PM();
        pm10.getReading();
            try {
                Float value = getValueFromFile(Constants.PM10_INPUT);
                smoothener.AvgCalcPM10(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Float getValueFromFile(String csvFileName) throws IOException {
        Path myPath = Paths.get(csvFileName);
        String reading = Files.readAllLines(myPath).get(round);
        Float value = Float.parseFloat(reading);
        return value;
    }
}
