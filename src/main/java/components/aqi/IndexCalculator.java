package components.aqi;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class IndexCalculator {

    private final String pollutantName;
    private Map<Pair<Float,Float>,Pair<Integer,Integer>> rangeMappings;

    public IndexCalculator(String pollutantName, String fileName) {
        this.pollutantName = pollutantName;
        this.rangeMappings = new HashMap<>();
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
                    ImmutablePair<Float,Float> concentrationRange = new ImmutablePair<>(rangeLow,rangeHigh);
                    ImmutablePair<Integer,Integer> aqiRange = new ImmutablePair<>(aqiLow,aqiHigh);
                    rangeMappings.put(concentrationRange,aqiRange);

                }
                row++;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
