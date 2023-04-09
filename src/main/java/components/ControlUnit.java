package components;

public class ControlUnit {
    enum Level{
        OK,
        Moderate,
        Severe,
        Hazardous;
    }

    public class Output{
        boolean out = false;
        Boolean inc = null;
        Boolean dec = null;
    }

    private Level level;
    ControlUnit(){
        this.level = Level.OK;
    }

    public Level getLatestLevel(float aqiIndex)
    {
        if (aqiIndex>=0 && aqiIndex<=100)
            return Level.OK;
        else if (aqiIndex>100 && aqiIndex<=200)
            return Level.Moderate;
        else if (aqiIndex>200 && aqiIndex<=310)
            return Level.Severe;
        else
            return Level.Hazardous;
    }

    public void getControlAction(float aqiIndex){
        Output output = new Output();
        Level latestLevel = getLatestLevel(aqiIndex);
        if(level==Level.OK && (latestLevel==Level.Moderate || latestLevel == Level.Severe || latestLevel==Level.Hazardous))
        {
            level = Level.Moderate;
            output.out = true;
        }

        else if (level==Level.Moderate)
        {
            if(latestLevel == Level.Severe || latestLevel==Level.Hazardous)
            {
                level = Level.Severe;
                output.inc = true;
                output.out = true;
            }
            else if(latestLevel==Level.Moderate)
            {
                output.out = true;
            }

            else if (latestLevel==Level.OK) {
                output.out = false;
            }
        }

        else if (level == Level.Severe) {
            if(latestLevel==Level.Hazardous)
            {
                level = Level.Hazardous;
                output.inc = true;
                output.out = true;
            }

            else if(latestLevel == Level.Severe)
            {
                output.out = true;
            }

            else if (latestLevel==Level.Moderate)
            {
                level = Level.Moderate;
                output.dec = true;
                output.out = true;
            }
        }

        else if(level==Level.Hazardous){
            if(latestLevel==Level.Hazardous)
            {
                output.out = true;
            }

            else if(latestLevel==Level.Moderate || latestLevel == Level.Severe)
            {
                level = Level.Severe;
                output.dec = true;
                output.out = true;
            }
        }
    }
}
