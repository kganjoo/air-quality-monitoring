package components.smoothener;

import components.Config;
import components.ControlUnit;
import components.Display;
import components.DisplayValue;
import components.aqi.AQICalculator;
import java.util.concurrent.BrokenBarrierException;

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

    public void AvgCalcTemp(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_Temp.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.TEMP_MARKER,output));
        Config.getBarrier().await();
    }

    public void AvgCalcHum(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_Hum.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.RH_MARKER,output));
        this.controlUnit.triggerHumidDeHumid(output);
        Config.getBarrier().await();
    }

    public void AvgCalcCO(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_CO.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.CO_MARKER,output));
        aqiCalculator.calculateCOIndex(output);
        Config.getBarrier().await();
    }

    public void AvgCalcCO2(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_CO2.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.CO2_MARKER,output));
        aqiCalculator.calculateCO2Index(output);
        Config.getBarrier().await();
    }

    public void AvgCalcNO2(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_NO2.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.NO2_MARKER,output));
        aqiCalculator.calculateNO2Index(output);
        Config.getBarrier().await();
    }

    public void AvgCalcPM25(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_PM25.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.PM25_MARKER,output));
        aqiCalculator.calculatePM25Index(output);
        Config.getBarrier().await();
    }

    public void AvgCalcPM10(Float in) throws BrokenBarrierException, InterruptedException {
        float output = AverageCalc_PM10.CalculateAvg(in);
        Display.addToDisplay(new DisplayValue(Display.PM10_MARKER,output));
        aqiCalculator.calculatePM10Index(output);
        Config.getBarrier().await();
    }
}