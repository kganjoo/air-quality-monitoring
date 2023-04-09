public class DataSmoothener {
    int window_size;
    AverageCalc AverageCalc_Temp = new AverageCalc(5);
    AverageCalc AverageCalc_Hum = new AverageCalc(5);
    AverageCalc AverageCalc_CO = new AverageCalc(5);
    AverageCalc AverageCalc_CO2 = new AverageCalc(5);
    AverageCalc AverageCalc_NO2 = new AverageCalc(5);
    AverageCalc AverageCalc_PM25 = new AverageCalc(5);
    AverageCalc AverageCalc_PM10 = new AverageCalc(5);

    public DataSmoothener(int window_size){
        this.window_size = window_size;
    }

    public void AvgCalcTemp(float in){
        float output = AverageCalc_Temp.CalculateAvg(in);
    }

    public void AvgCalcHum(float in){
        float output = AverageCalc_Hum.CalculateAvg(in);
    }

    public void AvgCalcCO(float in){
        float output = AverageCalc_CO.CalculateAvg(in);
    }

    public void AvgCalcCO2(float in){
        float output = AverageCalc_CO2.CalculateAvg(in);
    }

    public void AvgCalcNO2(float in){
        float output = AverageCalc_NO2.CalculateAvg(in);
    }

    public void AvgCalcPM25(float in){
        float output = AverageCalc_PM25.CalculateAvg(in);
    }

    public void AvgCalcPM10(float in){
        float output = AverageCalc_PM10.CalculateAvg(in);
    }
}