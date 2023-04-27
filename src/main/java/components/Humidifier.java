package components;

import constants.Constants;

public class Humidifier {
    static boolean hum_status;
    static boolean dehum_status;

    public Humidifier(){
        hum_status = false;
        dehum_status = false;
    }

    public void triggerHumidifier(boolean x){
        if (x && !hum_status){
            hum_status = true;
            //System.out.println("Turning on Humidifier");
            Display.addToControlAction("Turning on Humidifier");
        }

        else if (!x && hum_status){
            hum_status = false;
            //System.out.println("Turning off Humidifier");
            Display.addToControlAction("Turning off Humidifier");
        }

        else if(x && hum_status){
            //System.out.println("Humidifier is Running");
            Display.addToControlAction("Humidifier is Running");
        }
        else {
            Display.addToControlAction(Constants.EMPTY_STRING);
        }



    }

    public void triggerDehumidifier(boolean x){
        boolean output;
        if (x && !dehum_status){
            dehum_status = true;
            //System.out.println("Turning on DeHumidifier");
            Display.addToControlAction("Turning on Dehumidifier");
        }

        else if (!x && dehum_status){
            dehum_status = false;
            //System.out.println("Turning off DeHumidifier");
            Display.addToControlAction("Turning off DeHumidifier");
        }
        else if(x && dehum_status){
            //System.out.println("DeHumidifier is Running");
            Display.addToControlAction("DeHumidifier is Running");
        }

        else {
            Display.addToControlAction(Constants.EMPTY_STRING);
        }


    }
}
