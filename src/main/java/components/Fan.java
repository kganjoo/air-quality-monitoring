package components;

import constants.Constants;

public class Fan {
    static boolean status;
    static int speed;


    public Fan(){
        status = false;
        speed = 0;
    }
    public void FanStatus(boolean x, Boolean inc, Boolean dec) throws InterruptedException {
        if (x && !status) {
            status = true;
            Display.addToControlAction("Turing fan on ");
        }
        else if(x && status) {
            if (inc != null && inc) {
                if (speed == 0) {
                    speed = 1;
                    Display.addToControlAction("Incrementing Fan speed " );
                } else {
                    speed = 2;
                    Display.addToControlAction("Incrementing Fan speed " );
                }
            } else if (dec != null && dec) {
                if (speed == 2) {
                    speed = 1;
                    Display.addToControlAction("Decrementing Fan speed " );
                } else {
                    speed = 0;
                    Display.addToControlAction("Decrementing Fan speed " );
                }
            } else {
                Display.addToControlAction(getSpeed(speed));
            }
        }
        else if (!x && status){
            status = false;
            Display.addToControlAction("Turing fan off" );
        }
        else {
            Display.addToControlAction(Constants.EMPTY_STRING);
        }
    }

    private void printSpeed(int speed){
        if(speed == 0)
            System.out.println("Running FAN at low speed");
        if(speed == 1)
            System.out.println("Running FAN at medium speed");
        if(speed == 2)
            System.out.println("Running FAN at high speed");
    }

    private String getSpeed(int speed){
        if(speed == 0)
            return  "Running FAN at low speed";
        if(speed == 1)
            return  "Running FAN at MEDIUM speed";
        if(speed == 2)
            return  "Running FAN at HIGH speed";
        return  "";
    }
}
