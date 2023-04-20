package components.smoothener;

import components.ControlUnit;
import components.aqi.AQICalculator;
import components.smoothener.AverageCalc;
import constants.Constants;

import java.util.concurrent.ExecutionException;

public class DataSmoothener {
    private AQICalculator aqiCalculator;

    int window_size;
    private AverageCalc AverageCalc_Temp;
    private AverageCalc AverageCalc_Hum;
    private AverageCalc AverageCalc_CO;
    private AverageCalc AverageCalc_CO2;
    private AverageCalc AverageCalc_NO2;
    private AverageCalc AverageCalc_PM25;
    private AverageCalc AverageCalc_PM10;
    private ControlUnit controlUnit;


    public DataSmoothener(int window_size){
        this.window_size = window_size;
        this.AverageCalc_Temp = new AverageCalc(this.window_size);
        this.AverageCalc_Hum = new AverageCalc(this.window_size);
        this.AverageCalc_CO = new AverageCalc(this.window_size);
        this.AverageCalc_CO2 = new AverageCalc(this.window_size);
        this.AverageCalc_NO2 = new AverageCalc(this.window_size);
        this.AverageCalc_PM25 = new AverageCalc(this.window_size);
        this.AverageCalc_PM10 = new AverageCalc(this.window_size);
        this.controlUnit = new ControlUnit();
        this.aqiCalculator = new AQICalculator(this.controlUnit);
    }

    public void AvgCalcTemp(float in){
        float output = AverageCalc_Temp.CalculateAvg(in);
        System.out.println(String.format("Value of Temperature is %f",
               output));

    }

    public void AvgCalcHum(float in){
        float output = AverageCalc_Hum.CalculateAvg(in);
        System.out.println(String.format("Value of humidity is %f",
                output));
        this.controlUnit.triggerHumidDeHumid(output);

    }

    public void AvgCalcCO(float in) {
        float output = AverageCalc_CO.CalculateAvg(in);
        System.out.println(String.format("Value of CO is %f",
                output));
        aqiCalculator.calculateCOIndex(output);

    }

    public void AvgCalcCO2(float in){
        float output = AverageCalc_CO2.CalculateAvg(in);
        System.out.println(String.format("Value of CO2 is %f",
                output));
        aqiCalculator.calculateCO2Index(output);
    }

    public void AvgCalcNO2(float in){
        float output = AverageCalc_NO2.CalculateAvg(in);
        System.out.println(String.format("Value of NO2 is %f",
             output));
        aqiCalculator.calculateNO2Index(output);
    }

    public void AvgCalcPM25(float in){
        float output = AverageCalc_PM25.CalculateAvg(in);
        System.out.println(String.format("Value of PM25 is %f",
             output));
        aqiCalculator.calculatePM25Index(output);
    }

    public void AvgCalcPM10(float in){
        float output = AverageCalc_PM10.CalculateAvg(in);
        System.out.println(String.format("Value of PM10 is %f",
               output));
        aqiCalculator.calculatePM10Index(output);
    }
}