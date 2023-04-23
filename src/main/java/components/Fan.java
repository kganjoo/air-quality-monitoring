package components;

import constants.Constants;

import java.util.concurrent.BrokenBarrierException;

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
            //System.out.println("Turing Fan On");
            //Thread.sleep(1000);

            //printSpeed(speed);
            Display.addToControlAction("Turing fan on ");
        }
        else if(x && status) {
            if (inc != null && inc) {
                if (speed == 0) {
                    speed = 1;
                    //System.out.println("Incrementing Fan speed");
                    //Thread.sleep(1000);

                    //printSpeed(speed);
                    Display.addToControlAction("Incrementing Fan speed " );
                } else {
                    speed = 2;
                    //System.out.println("Incrementing Fan speed");
                    //Thread.sleep(1000);

                    //printSpeed(speed);
                    Display.addToControlAction("Incrementing Fan speed " );
                }
            } else if (dec != null && dec) {
                if (speed == 2) {
                    speed = 1;
                    //System.out.println("Decrementing Fan speed");
                    //Thread.sleep(1000);

                    //printSpeed(speed);
                    Display.addToControlAction("Decrementing Fan speed " );
                } else {
                    speed = 0;
                    //System.out.println("Decrementing Fan speed");
                    //Thread.sleep(1000);

                    //printSpeed(speed);

                    Display.addToControlAction("Decrementing Fan speed " );
                }
            } else {
                // Fan keeps running at constant speed in this case
                //printSpeed(speed);

                Display.addToControlAction(getSpeed(speed));
            }
        }
        else if (!x && status){
            //System.out.println("Turing fan off");
            //Thread.sleep(1000);
            status = false;
            //System.out.println("Fan turned off");

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
