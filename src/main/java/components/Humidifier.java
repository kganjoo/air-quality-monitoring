package components;

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
            System.out.println("Turning on Humidifier");
        }

        else if (!x && hum_status){
            hum_status = false;
            System.out.println("Turning off Humidifier");
        }

        else if(x && hum_status){
            System.out.println("Humidifier is Running");
        }



    }

    public void triggerDehumidifier(boolean x){
        boolean output;
        if (x && !dehum_status){
            dehum_status = true;
            System.out.println("Turning on DeHumidifier");
        }

        else if (!x && dehum_status){
            dehum_status = false;
            System.out.println("Turning off DeHumidifier");
        }
        else if(x && dehum_status){
            System.out.println("DeHumidifier is Running");
        }


    }
}
