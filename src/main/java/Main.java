import components.Config;
import components.DataPreprocessor;
import components.aqi.IndexCalculator;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running air quality monitoring project");
        System.out.println("Reading Config File:...");
        try {
            DataPreprocessor data = new DataPreprocessor();



            while (Config.getRoundValue()<=4) {
                data.startRound(Config.getRoundValue());
                Config.getBarrier().await();
                Config.incrementRound();
                System.out.println("Entering next round -------------------------");
                System.out.println("\n");
            }

            Config.getExecutorService().shutdown();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


    }
}
