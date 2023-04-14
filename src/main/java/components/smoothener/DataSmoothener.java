package components.smoothener;

import components.aqi.AQICalculator;
import components.smoothener.AverageCalc;
import constants.Constants;

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

    public DataSmoothener(int window_size){
        this.window_size = window_size;
        this.AverageCalc_Temp = new AverageCalc(this.window_size);
        this.AverageCalc_Hum = new AverageCalc(this.window_size);
        this.AverageCalc_CO = new AverageCalc(this.window_size);
        this.AverageCalc_CO2 = new AverageCalc(this.window_size);
        this.AverageCalc_NO2 = new AverageCalc(this.window_size);
        this.AverageCalc_PM25 = new AverageCalc(this.window_size);
        this.AverageCalc_PM10 = new AverageCalc(this.window_size);
    }

    public void AvgCalcTemp(float in){
        float output = AverageCalc_Temp.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));
    }

    public void AvgCalcHum(float in){
        float output = AverageCalc_Hum.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));
    }

    public void AvgCalcCO(float in){
        float output = AverageCalc_CO.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));

    }

    public void AvgCalcCO2(float in){
        float output = AverageCalc_CO2.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));
    }

    public void AvgCalcNO2(float in){
        float output = AverageCalc_NO2.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));
    }

    public void AvgCalcPM25(float in){
        float output = AverageCalc_PM25.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));
    }

    public void AvgCalcPM10(float in){
        float output = AverageCalc_PM10.CalculateAvg(in);
        System.out.println(String.format("Value of output generated by thread %s is %f",
                Thread.currentThread().getName(),output));
    }
}