package components;
import constants.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Display implements Runnable {

    private static BlockingQueue<DisplayValue> displayValuesQueue =new ArrayBlockingQueue<>(Constants.DISPLAY_POLLUTANT_QUEUE_SIZE);
    private static BlockingQueue<String> controlActionsQueue = new ArrayBlockingQueue<>(Constants.DISPLAY_CONTROL_ACTIONS_QUEUE_SIZE);
    public static String CO_MARKER = "CO";
    public static String CO2_MARKER = "CO2";
    public static String NO2_MARKER = "NO2";
    public static String PM25_MARKER = "PM25";
    public static String PM10_MARKER = "PM10";
    public static String TEMP_MARKER = "TEMP";
    public static String RH_MARKER = "RH";
    public static String AQI_MARKER = "AQI";
    private static DecimalFormat df = new DecimalFormat("##.##");




    @Override
    public void run() {
     display();

    }


    public static void addToDisplay(DisplayValue value){
        displayValuesQueue.add(value);
    }

    public static void addToControlAction(String action){
        controlActionsQueue.add(action);
    }


    private void display(){
        try {
            while(true) {
                if(displayValuesQueue.size()==Constants.DISPLAY_POLLUTANT_QUEUE_SIZE
                        && controlActionsQueue.size()==Constants.DISPLAY_CONTROL_ACTIONS_QUEUE_SIZE) {
                    Float co,co2,no2,pm25,pm10,temp,rh,aqi;
                    co = co2 =no2 = pm25 = pm10 =temp=rh =aqi = -1f;
                    while (displayValuesQueue.size() > 0) {
                        DisplayValue curr = displayValuesQueue.remove();
                        String valueType = curr.type;
                        if(valueType.equals(CO_MARKER)){
                            co = curr.value;
                        }
                        else if(valueType.equals(CO2_MARKER)){
                            co2 = curr.value;
                        }
                        else if(valueType.equals(NO2_MARKER)){
                            no2 = curr.value;
                        }
                        else if(valueType.equals(PM25_MARKER)){
                            pm25 = curr.value;
                        }
                        else if(valueType.equals(PM10_MARKER)){
                            pm10 = curr.value;
                        }
                        else if(valueType.equals(RH_MARKER)){
                            rh = curr.value;
                        }
                        else if(valueType.equals(TEMP_MARKER)){
                            temp = curr.value;
                        }
                        else if(valueType.equals(AQI_MARKER)) {
                            aqi = curr.value;
                        }

                    }


                    displayOutput(co,co2,no2,pm25,pm10,temp,rh,aqi);
                    displayControlActions(controlActionsQueue);

                    try {
                        Config.getBarrier().await();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
                else {
                    if(Config.getRoundValue()>72)
                        break;
                    Thread.sleep(1000);
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayControlActions(BlockingQueue<String> controlActionsQueue) {
     List<String> actions = new ArrayList<>();
     while (controlActionsQueue.size()>0){
         String action = controlActionsQueue.remove();
         if(!action.equals(Constants.EMPTY_STRING)){
             actions.add(action);
         }
     }
        if(!actions.isEmpty()){
            System.out.printf(" FAN AND HUMIDIFIER/DEHUMIDIFIER UPDATES FOR AQI MONITOR     %n");

            for(String action : actions){
                System.out.println(ConsoleColors.CYAN+action+ConsoleColors.RESET);
            }

        }

    }

    private void displayOutput(Float co,
                               Float co2,
                               Float no2,
                               Float pm25,
                               Float pm10,
                               Float temp,
                               Float rh,
                               Float aqi) {


        System.out.printf("--------------------------------%n");
        System.out.printf(" DISPLAY OUT FOR AQI MONITOR       %n");

        System.out.printf("-------------------------------------------------------------------------------------%n");
        System.out.printf(ConsoleColors.BLACK_BOLD+" %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s %n",
                "CO2 (ppm)",
                "CO (ppm)",
                "PM10 (ug/m3)",
                "PM2.5 (ug/m3)",
                "NO2 (ppb)",
                "TEMP ( Celsius )",
                "RH (%)",
                "AQI" +ConsoleColors.RESET);
        System.out.printf("----------------------------------------------------------------------------------------%n");

        System.out.printf(ConsoleColors.BLUE+" %-9s | %-9s | %-10s | %-13s | %-11s | %-13s | %-10s | %-11s  %n",
                df.format(co2),
                df.format(co),
                df.format(pm10),
                df.format(pm25),
                df.format(no2),
                df.format(temp),
                df.format(rh),
                getColorFormattedAQI(Float.valueOf(df.format(aqi))));

        System.out.printf(ConsoleColors.RESET+"-----------------------------------------------------------------------%n");

    }


    private String getColorFormattedAQI(Float aqiIndex){
        if (aqiIndex>=0 && aqiIndex<=50)
            return ""+ConsoleColors.GREEN_BOLD_BRIGHT+aqiIndex;
        else if (aqiIndex>50 && aqiIndex<=100)
            return ""+ConsoleColors.YELLOW+aqiIndex;
        else if (aqiIndex>100 && aqiIndex<=200)
            return ""+ConsoleColors.YELLOW_BOLD_BRIGHT+aqiIndex;
        else
            return ""+ConsoleColors.RED_BOLD_BRIGHT+aqiIndex;
    }




}
