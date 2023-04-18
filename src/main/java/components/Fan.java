package components;

import java.util.concurrent.BrokenBarrierException;

public class Fan {
    static boolean status;
    static int speed;

    public Fan(){
        status = false;
        speed = 0;
    }
    public void FanStatus(boolean x, Boolean inc, Boolean dec) {
        System.out.println("Entered Fan");

        int output=0;
        if (x && !status) {
            status = true;
            output = speed;
        } 
        else   {
            if (x && status){
                if (inc){
                    if (speed==0){
                    speed = 1;
                    }
                    else{
                        speed = 2;
                    }
                }
                if (dec){
                    if (speed==2){
                    speed = 1;
                    }
                    else{
                        speed = 0;
                    }
                }
                output = speed;
            }
        }
        if (!x){
            status = true;
        }

        // return output;
        try {
            System.out.println("Fan Speed: " + output);
            Config.getBarrier().await();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
