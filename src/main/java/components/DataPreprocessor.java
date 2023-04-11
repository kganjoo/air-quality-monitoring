package main.java.components;

import components.smoothener.DataSmoothener;
import main.java.sensors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataPreprocessor {
    private int round = 0;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private DataSmoothener smoothener;


    public void getTemperature() throws IOException {
        executorService.submit(()->{
            TempHum temp = new TempHum();
            temp.getReading();
            try{
                Float value = getValueFromFile("TempInput.csv");
                smoothener.AvgCalcTemp(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void getHumidity() throws IOException {
        executorService.submit(()->{
            TempHum hum = new TempHum();
            hum.getReading();
            try {
                Float value = getValueFromFile("HumInput.csv");
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
                Float value = getValueFromFile("COInput.csv");
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
                    Float value = getValueFromFile("CO2Input.csv");
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
                    Float value = getValueFromFile("NO2Input.csv");
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
                    Float value = getValueFromFile("PM25Input.csv");
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
                Float value = getValueFromFile("PM10Input.csv");
                smoothener.AvgCalcPM10(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Float getValueFromFile(String csvFileName) throws IOException {
        String path = System.getProperty("user.dir");
        Path myPath = Paths.get(path + "/src/sensors/" + csvFileName);
        String reading = Files.readAllLines(myPath).get(round);
        Float value = Float.parseFloat(reading);
        return value;
    }

    public void incrementRound()
    {
        round++;
    }
}
