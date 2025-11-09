import java.util.*;

public class FractionalKnapsack {

    public static void main(String[] args) {
        int[] value = { 60, 100, 120 };
        int[] weight = { 10, 20, 30 };
        int capacity = 50;

        int n = value.length;
        long start = System.nanoTime(); // start time
        // value/weight ratio array
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = (double) value[i] / weight[i];
        }

        // Sort items by ratio (descending)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ratio[j] > ratio[i]) {
                    // swap ratio
                    double temp = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = temp;

                    // swap values
                    int tempVal = value[i];
                    value[i] = value[j];
                    value[j] = tempVal;

                    // swap weights
                    int tempW = weight[i];
                    weight[i] = weight[j];
                    weight[j] = tempW;
                }
            }
        }

        double maxValue = 0.0;

        for (int i = 0; i < n; i++) {
            if (capacity == 0) break;

            if (weight[i] <= capacity) {
                maxValue += value[i];
                capacity -= weight[i];
            } else {
                double fraction = (double) capacity / weight[i];
                maxValue += value[i] * fraction;
                capacity = 0;
            }
        }

        System.out.println("Maximum value in knapsack = " + maxValue);

        long end = System.nanoTime(); // end time
        long time = end - start;
        System.out.println("Execution Time: " + time + " nanoseconds");
        System.out.println("Execution Time: " + (time / 1e6) + " milliseconds");
    }
}
