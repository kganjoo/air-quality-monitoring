import components.DataPreprocessor;
import components.aqi.IndexCalculator;

import java.io.*;
import java.util.Properties;
public class Main {
    public static void main(String[] args) {
        System.out.println("Running air quality monitoring project");
        System.out.println("Reading Config File:...");
        try {
            DataPreprocessor data = new DataPreprocessor();
            data.Init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
