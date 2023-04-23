package components;

public class ControlUnit {
    enum Level{
        OK,
        Moderate,
        Severe,
        Hazardous;
    }





    private Level level;
    private Fan fan;
    private Humidifier humidifier;
    public ControlUnit(){
        this.level = Level.OK;
        this.fan = new Fan();
        this.humidifier = new Humidifier();
    }

    public Level getLatestLevel(float aqiIndex)
    {
        if (aqiIndex>=0 && aqiIndex<=50)
            return Level.OK;
        else if (aqiIndex>50 && aqiIndex<=100)
            return Level.Moderate;
        else if (aqiIndex>100 && aqiIndex<=200)
            return Level.Severe;
        else
            return Level.Hazardous;
    }

    public void triggerFan(float aqiIndex) throws InterruptedException {
        boolean out = false;
        Boolean inc = null;
        Boolean dec = null;

        Level latestLevel = getLatestLevel(aqiIndex);
        if(level==Level.OK && (latestLevel==Level.Moderate || latestLevel == Level.Severe || latestLevel==Level.Hazardous))
        {
            level = Level.Moderate;
            out = true;
        }

        else if (level==Level.Moderate)
        {
            if(latestLevel == Level.Severe || latestLevel==Level.Hazardous)
            {
                level = Level.Severe;
                inc = true;
                out = true;
            }
            else if(latestLevel==Level.Moderate)
            {
                out = true;
            }

            else if (latestLevel==Level.OK) {
                out = false;
            }
        }

        else if (level == Level.Severe) {
            if(latestLevel==Level.Hazardous)
            {
                level = Level.Hazardous;
                inc = true;
                out = true;
            }

            else if(latestLevel == Level.Severe)
            {
                out = true;
            }

            else if (latestLevel==Level.Moderate)
            {
                level = Level.Moderate;
                dec = true;
                out = true;
            }
        }

        else if(level==Level.Hazardous){
            if(latestLevel==Level.Hazardous)
            {
                out = true;
            }

            else if(latestLevel==Level.Moderate || latestLevel == Level.Severe)
            {
                level = Level.Severe;
                dec = true;
                out = true;
            }
        }

        System.out.println("Based on AQI, out inc and dec for fan are "+out+getString(inc,dec));
        Thread.sleep(1000);
        fan.FanStatus(out, inc, dec);
    }


    public void triggerHumidDeHumid(float humidityLevel) throws InterruptedException {
        boolean humidOut =false;
        boolean deHumidOut = false;

            if (humidityLevel < 40) {
                humidOut = true;


            }
            if (humidityLevel > 60) {
                deHumidOut = true;
            }
            Thread.sleep(1000);
            humidifier.triggerHumidifier(humidOut);
            humidifier.triggerDehumidifier(deHumidOut);


    }

    //todo remove
    private String getString(Boolean inc, Boolean dec){
        String s = " ";
        if(inc==null)
            s+="null ";
        else
            s+=inc.toString()+" ";

        if(dec==null)
            s+="null ";
        else
            s+=dec.toString()+" ";

        return s;

    }





}
