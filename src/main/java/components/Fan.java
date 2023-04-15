package components;

public class Fan {
    static boolean status;
    static int speed;

    public Fan(){
        status = false;
        speed = 0;
    }
    public void FanStatus(boolean x, Boolean inc, Boolean dec){
        // if x=1 and status=0: {
        //     status:= 1
        //     output:=speed }
        //     elif x=1 and status = 1 {
        //     if inc = 1 and speed = 0: {
        //     speed:=1 }
        //     elif inc = 1 and speed=1: {
        //     speed:=2 }
        //     elif dec=1 and speed:=1 :{
        //     speed:=0 }
        //     elif dec=1 and speed=2: {
        //     speed:=1 }
        //     output:=speed
        //     }
        //     if x=0 {
        //     status:=0 }
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
        System.out.println("Fan Speed: "+output);
    }
}
