package components.aqi;

import com.opencsv.CSVReader;
import javafx.util.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class IndexCalculator {

    private final String pollutantName;
    private Map<Pair<Float,Float>,Pair<Integer,Integer>> rangeMappings;

    public IndexCalculator(String pollutantName, String fileName) {
        this.pollutantName = pollutantName;
        this.rangeMappings = new HashMap<Pair<Float, Float>, Pair<Integer, Integer>>();
        init(fileName);
    }


    public  Float getPollutantIndex(Float concentration) {

        for(Pair<Float,Float> concentrationRange : rangeMappings.keySet()){

            Float concentrationLow = concentrationRange.getKey();
            Float concentrationHigh = concentrationRange.getValue();

            if(concentration >= concentrationLow &&
                    concentration<=concentrationHigh){

                Pair<Integer,Integer> aqiRange = rangeMappings.get(concentrationRange);

                Integer aqiLow = aqiRange.getKey();
                Integer aqiHigh = aqiRange.getValue();

                Float pollutantIndex  = (aqiHigh - aqiLow )/(concentrationHigh - concentrationLow)
                                        * (concentration - concentrationLow) + aqiLow ;

                return pollutantIndex;
            }


        }




        return null;
    }

    void init(String fileName) {
        try {

            FileReader filereader = new FileReader(fileName);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            int row = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (row > 0) {
                    int col = 0;
                    Integer aqiLow = null;
                    Integer aqiHigh = null;
                    Float rangeLow = null;
                    Float rangeHigh = null;

                    for (String cell : nextRecord) {
                        if (col == 0) {
                            aqiLow = Integer.parseInt(cell);
                        } else if (col == 1) {
                            aqiHigh = Integer.parseInt(cell);
                        } else if (col == 2) {
                            rangeLow = Float.valueOf(cell);
                        } else if (col == 3) {
                            rangeHigh = Float.valueOf(cell);
                        }
                        col++;
                    }
                    Pair<Float,Float> concentrationRange = new Pair<Float, Float>(rangeLow,rangeHigh);
                    Pair<Integer,Integer> aqiRange = new Pair<Integer, Integer>(aqiLow,aqiHigh);
                    rangeMappings.put(concentrationRange,aqiRange);

                }
                row++;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            // TODO: 4/8/23  handle exception
        }


    }


}
