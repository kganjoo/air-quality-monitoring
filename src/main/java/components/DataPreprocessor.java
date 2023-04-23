package components;

import components.smoothener.DataSmoothener;
import constants.Constants;
import sensors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataPreprocessor {
    private int round;

    private DataSmoothener smoothener;
    private ExecutorService executorService;

    private void Init(){
        this.executorService = Config.getExecutorService();
        this.smoothener = new DataSmoothener(Constants.window_size);

    }

    public void startRound(int round) throws IOException {
        System.out.println("Starting round "+ round);
        if(round==1){
            Init();
        }
        this.round = round;
//        getTemperature();
        getHumidity();
        getCO2GasConcentration();
        getNO2GasConcentration();
        getCOGasConcentration();
        getPM10();
        getPM25();


    }

//    private void getTemperature() throws IOException {
//        executorService.submit(()->{
//
//            TempHum temp = new TempHum();
//            temp.getReading();
//            try{
//                Float value = getValueFromFile(Constants.TEMP_INPUT);
//
//                smoothener.AvgCalcTemp(value);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    private void getHumidity() throws IOException {
        executorService.submit(()->{
            TempHum hum = new TempHum();
            hum.getReading();
            try {
                printHelper("DHT22");
                Thread.sleep(1000);
                Float value = getValueFromFile(Constants.HUM_INPUT);
                smoothener.AvgCalcHum(value);
            } catch (IOException | BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void getCOGasConcentration() throws IOException {
        executorService.submit(()->{
            
            CO co = new CO();
            co.getReading();
            try {
                printHelper("MQ-7 SENSOR");
                Thread.sleep(1000);
                Float value = getValueFromFile(Constants.CO_INPUT);
                smoothener.AvgCalcCO(value);

            } catch (IOException | BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void getCO2GasConcentration() throws IOException {
        executorService.submit(()->{
            
            CO2 co2 = new CO2();
            co2.getReading();
                try {
                    printHelper("MG-811");
                    Thread.sleep(1000);
                    Float value = getValueFromFile(Constants.CO2_INPUT);
                    smoothener.AvgCalcCO2(value);
                } catch (IOException | BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
        });
    }

    private void getNO2GasConcentration() throws IOException {
        executorService.submit(()->{
            
            NO2 no2 = new NO2();
            no2.getReading();
                try {
                    printHelper("NO2-D4");
                    Thread.sleep(1000);
                    Float value = getValueFromFile(Constants.NO2_INPUT);
                    smoothener.AvgCalcNO2(value);
                } catch (IOException | BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
        });
    }

    private void getPM25() throws IOException {
        executorService.submit(()->{
            
            PM pm25 = new PM();
            pm25.getReading();
                try {
                    //read from file

                    printHelper("PM SDS011");
                    Thread.sleep(1000);
                    Float value = getValueFromFile(Constants.PM25_INPUT);
                    smoothener.AvgCalcPM25(value);
                } catch (IOException | BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
        });
    }

    private void getPM10() throws IOException {
        executorService.submit(()->{
            
        PM pm10 = new PM();
        pm10.getReading();
            try {
                printHelper("PM SDS011");
                Thread.sleep(1000);
                Float value = getValueFromFile(Constants.PM10_INPUT);
                smoothener.AvgCalcPM10(value);
            } catch (IOException | BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private Float getValueFromFile(String csvFileName) throws IOException {
        Path myPath = Paths.get(csvFileName);
        String reading = Files.readAllLines(myPath).get(round-1);
        Float value = Float.parseFloat(reading);
        return value;
    }

    private void printHelper(String sensorName){
        System.out.println("Fetching readings from sensor -- "+sensorName);
    }
}
