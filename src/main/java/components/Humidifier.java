package components;

public class Humidifier {
    static boolean hum_status;
    static boolean dehum_status;

    public Humidifier(){
        System.out.println("Calling Humidifier Constructor");
        hum_status = false;
        dehum_status = false;
    }

    public void HumidifierComponent(boolean x){
        boolean output = false;
        if (x && !hum_status){
            hum_status = true;
        }

        if (!x && hum_status){
            hum_status = false;
        }

        output = hum_status;

        try {
            System.out.println("Humidifier Status: " + output);
            System.out.println("Calling await from thread "+Thread.currentThread().getName());
            Config.getBarrier().await();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeHumidifierComponent(boolean x){
        boolean output = false;
        if (x && !dehum_status){
            dehum_status = true;
        }

        if (!x && dehum_status){
            dehum_status = false;
        }

        output = dehum_status;


        try {
            System.out.println("De-Humidifier Status: " + output);
            System.out.println("Calling await from thread "+Thread.currentThread().getName());
            Config.getBarrier().await();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
