import sensors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataPreprocessor {
    private int round = 0;


    public Float getTemperature() throws IOException {
        TempHum temp = new TempHum();
        temp.getReading();
        return getValueFromFile("TempInput.csv");
    }

    public Float getHumidity() throws IOException {
        TempHum hum = new TempHum();
        hum.getReading();
        return getValueFromFile("HumInput.csv");
    }

    public Float getCOGasConcentration() throws IOException {
        CO co = new CO();
        co.getReading();
        return getValueFromFile("COInput.csv");
    }

    public Float getCO2GasConcentration() throws IOException {
        CO2 co2 = new CO2();
        co2.getReading();
        return getValueFromFile("CO2Input.csv");
    }

    public Float getNO2GasConcentration() throws IOException {
        NO2 no2 = new NO2();
        no2.getReading();
        return getValueFromFile("NO2Input.csv");
    }

    public Float getPM25() throws IOException {
        PM pm25 = new PM();
        pm25.getReading();
        //read from file
        return getValueFromFile("PM25Input.csv");
    }

    public Float getPM10() throws IOException {
        PM pm10 = new PM();
        pm10.getReading();
        return getValueFromFile("PM10Input.csv");
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
