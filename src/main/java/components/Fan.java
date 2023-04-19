package components;

import java.util.concurrent.BrokenBarrierException;

public class Fan {
    static boolean status;
    static int speed;

    public Fan(){
        System.out.println("calling Fan constructor");
        status = false;
        speed = 0;
    }
    public void FanStatus(boolean x, Boolean inc, Boolean dec) {
        try {
            System.out.println("Entered Fan");
            System.out.println("x is " + x);
            System.out.println(inc);
            System.out.println(dec);
            System.out.println(" first status is " + status);
        }
        catch (Exception e){
            e.printStackTrace();
        }


        int output=0;
        if (x && !status) {
            status = true;
            output = speed;
        } 
        else   {
            if (x && status){
                System.out.println("TURNING FAN ON");
                if (inc!=null && inc){
                    if (speed==0){
                    speed = 1;
                    }
                    else{
                        speed = 2;
                    }
                }
                if (dec!=null && dec){
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
            status = false;
        }

        // return output;
        try {
            System.out.println("status is "+status);
            System.out.println("Fan Speed: " + output);
            System.out.println("Calling await from thread "+Thread.currentThread().getName());
            Config.getBarrier().await();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
