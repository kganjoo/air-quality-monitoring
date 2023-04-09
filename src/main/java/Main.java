import components.aqi.IndexCalculator;

import java.io.*;
import java.util.Properties;
public class Main {
    public static void main(String[] args) {
        System.out.println("Running air quality monitoring project");
        System.out.println("Reading Config File:...");
        try {
            String configFilePath = "./config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            System.out.println(prop.getProperty("window_size"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
