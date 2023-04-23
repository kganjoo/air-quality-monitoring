package components;

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
            System.out.println("Turing Fan On");
            Thread.sleep(1000);

            printSpeed(speed);
        }
        else   {
            if (x && status){
                if (inc!=null && inc){
                    if (speed==0){
                        speed = 1;
                        System.out.println("Incrementing Fan speed");
                        Thread.sleep(1000);

                        printSpeed(speed);
                    }
                    else{
                        speed = 2;
                        System.out.println("Incrementing Fan speed");
                        Thread.sleep(1000);

                        printSpeed(speed);
                    }
                }
                else if (dec!=null && dec){
                    if (speed==2){
                        speed = 1;
                        System.out.println("Decrementing Fan speed");
                        Thread.sleep(1000);

                        printSpeed(speed);
                    }
                    else{
                        speed = 0;
                        System.out.println("Decrementing Fan speed");
                        Thread.sleep(1000);

                        printSpeed(speed);
                    }
                }
                else {
                    // Fan keeps running at constant speed in this case
                    printSpeed(speed);
                }
            }
        }
        if (!x){
            System.out.println("Turing fan off");
            Thread.sleep(1000);
            status = false;
            System.out.println("Fan turned off");
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
}
