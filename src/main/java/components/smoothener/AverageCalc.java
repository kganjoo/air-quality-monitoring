package components.smoothener;

import constants.Constants;

public class AverageCalc {

    private float total;
    private int read_index;
    private float[] array;
    private int window_size;
    private boolean overflow;

    public AverageCalc(int window_size) {
        this.window_size = window_size;
        this.array = new float[window_size];
        this.total = 0;
        this.read_index = 0;
        this.overflow = false;
    }

    public float CalculateAvg(Float input) {
        float output = 0;
        if (input != null) {
            total = total - array[read_index];
            array[read_index] = input;
            total = total + array[read_index];
            if (overflow) {
                output = total / window_size;
            } else {
                output = total / (read_index + 1);
            }
            read_index = read_index + 1;

            if (read_index == window_size) {
                overflow = true;
                read_index = 0;
            }
        }

        return output;
    }




}


