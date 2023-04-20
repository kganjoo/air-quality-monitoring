package components;

public class Humidifier {
    static boolean hum_status;
    static boolean dehum_status;

    public Humidifier(){
        hum_status = false;
        dehum_status = false;
    }

    public void HumidifierComponent(boolean x){
        boolean output;
        if (x && !hum_status){
            hum_status = true;
        }

        if (!x && hum_status){
            hum_status = false;
        }

        output = hum_status;

        try {
            System.out.println("Humidifier Status: " + output);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeHumidifierComponent(boolean x){
        boolean output;
        if (x && !dehum_status){
            dehum_status = true;
        }

        if (!x && dehum_status){
            dehum_status = false;
        }

        output = dehum_status;


        try {
            System.out.println("De-Humidifier Status: " + output);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
