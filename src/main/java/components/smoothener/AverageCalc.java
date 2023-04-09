package components.smoothener;

public class AverageCalc{

    private float total;
    private int len_array;
    private float[] array;
    private int window_size;

    public AverageCalc(int window_size){
        this.window_size = window_size;
        this.array = new float[window_size];
        this.total = 0;
        this.len_array = 0;
    }

    public float CalculateAvg(float input) {
        total = total - array[len_array];
        total = total + input;
        float output = total / len_array;
        UpdateArr(input);
        CheckArr();
		return output;
	}

	public void CheckArr() {
		if (len_array >= window_size){
            len_array = 0;
            }
	}

	public void UpdateArr(float input) {
		array[len_array] = input;
        len_array = len_array + 1;
	}

}