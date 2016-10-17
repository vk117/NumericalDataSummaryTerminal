import java.util.ArrayList;
import java.util.Arrays;

/**
 * Numerical Data Summary Terminal - Data Functions
 *
 * This program contains methods which are all the valid
 * commands that a user can enter into the terminal.
 *
 * @author Vikram Pasumarti, vpasuma@purdue.edu
 * @version 17 October 2016
 */
public class DataFunctions {

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the mean, also known as the average, of
     * the dataset.
     */
    public double mean(double array[]) {
        double sum = 0;
        double average = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        average = sum / (array.length);
        return average;
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * returns the median, or middle value of the data. Depending on if
     * the number of values in the dataset are odd or even, the method
     * uses a different technique to calculate the median.
     */
    public double median(double array[]) {
        Arrays.sort(array);
        int index = 0;
        if (array.length % 2.0 != 0.0) {
            index = ((array.length - 1) / 2);
            return array[index];
        } else {
            int index2 = 0;
            index2 = (array.length / 2);
            index = index2 - 1;
            double evenMedian = (array[index] + array[index2]) / 2;
            return evenMedian;
        }
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * returns an Array List of all mode values in the dataset. The
     * mode is defined as the most frequently occuring value in a
     * dataset. If multiple values occur the same number of times, the
     * method will return all of the recurring values. However, if all
     * values occur the same number of times, the method will return
     * an empty Array List.
     */
    public ArrayList mode(double array[]) {
        ArrayList<Double> dataValues = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();
        for(int i = 0; i < array.length; i++) {
            if (!(dataValues.contains(array[i]))) {
                dataValues.add(array[i]);
            }
        }
        double[] modeCount = new double[dataValues.size()];
        int count = 0;
        for(int j = 0; j < dataValues.size(); j++) {
            count = 0;
            for (int k = 0; k < array.length; k++) {
                if (dataValues.get(j) == array[k]) {
                    count = count + 1;
                    modeCount[j] = count;
                }
            }
        }
        double maxCount = max(modeCount);
        double check = 0;
        for (int i = 0; i < modeCount.length; i++) {
            check = check + (modeCount[i] - modeCount[0]);
        }
        for (int i = 0; i < modeCount.length; i++) {
            if (modeCount[i] == maxCount && check != 0) {
                results.add(dataValues.get(i));
            }
        }
        return results;
    }

    /**
     * The mean and median are inputted into this method. To determine
     * the skew of the dataset, the method simply compares the values
     * of the mean and median to see which is greater, or if they
     * are equal.
     */
    public String skew(double meanVal, double medianVal) {

        if (meanVal == medianVal) {
            return "Symmetrical";
        } else if (meanVal > medianVal) {
            return "Right Skewed";
        } else if (meanVal < medianVal) {
            return "Left Skewed";
        } else {
            return "Error";
        }
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the standard deviation of the dataset.
     * The standard deviation shows how close the data is to the mean.
     */
    public double stdDev(double array[]) {
        double sum = 0.0;
        double variance = 0.0;
        double meanVal = mean(array);
        for (int i = 0; i < array.length; i++) {
            sum = sum + Math.pow((array[i] - meanVal), 2);
        }
        variance = ((1.0 / (array.length - 1.0)) * (sum));
        return Math.sqrt(variance);
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the range, which is simply the difference
     * between the maximum value and minimum value of the dataset.
     */
    public double range(double array[]) {
        return max(array) - min(array);
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the first quartile, also known as the
     * median of the first quarter of the dataset. The method checks
     * if the result of the length of the dataset divided by four is
     * an integer or not, because the technique to determine the first
     * quartile differs based on this fact.
     */
    public double Q1(double array[]) {
        Arrays.sort(array);
        int d1 = array.length / 4;
        String integerCheck = Double.toString(d1);
        if (integerCheck.contains(".0")) {
            return array[d1];
        } else {
            return (array[d1] + array[d1 + 1]) / 2;
        }
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the third quartile, also known as the
     * median of the third quarter of the dataset. The method checks
     * if the result of three times the length of the dataset divided by four is
     * an integer or not, because the technique to determine the third
     * quartile differs based on this fact.
     */
    public double Q3(double array[]) {
        Arrays.sort(array);
        int d3 = (3 * array.length) / 4;
        String integerCheck = Double.toString(d3);
        if (integerCheck.contains(".0")) {
            return array[d3];
        } else {
            return (array[d3] + array[d3 + 1]) / 2;
        }
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the interquartile range which is simply
     * the difference between the first and third quartiles.
     */
    public double iqr(double array[]) {
        double Q1val = Q1(array);
        double Q3val = Q3(array);
        return Q3val - Q1val;
    }

    /**
     * The dataset is inputted as an array of doubles. The method returns
     * the five number summary of the dataset. The five number summary
     * is the all of the information needed to draw a box plot, the minimum,
     * maximum, first quartile, median, and third quartile.
     */
    public String fiveNumSummary(double array[]) {
        double max = max(array);
        double min = min(array);
        double Q1val = Q1(array);
        double Q3val = Q3(array);
        double medianVal = median(array);
        return "Minimum = " + min + ", Q1 = " + Q1val + ", Median = " + medianVal + ", Q3 = " + Q3val + ", Maximum = " + max;
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the conditions for which outliers
     * in the dataset exist. It calculuates inner and outer fences
     * to determine at what intervals would mild or extreme outliers
     * exist.
     */
    public String outliers(double array[]) {
        double Q1val = Q1(array);
        double Q3val = Q3(array);
        double iqrVal = iqr(array);
        double ifl = Q1val - (1.5 * iqrVal);
        double ifh = Q3val + (1.5 * iqrVal);
        double ofl = Q1val - (3 * iqrVal);
        double ofh = Q3val + (3 * iqrVal);
        return "Values between " + ofl + " and " + ifl + " are mild outliers on the lower end \n" +
                "Values below " + ofl + " are extreme outliers on the lower end \n" +
                "Values between " + ifh + " and " + ofh + " are mild outliers on the higher end \n" +
                "Values above " + ofh + " are extreme outliers on the higher end";
    }
    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the values of the outliers in the dataset
     * as well as what kind of outlier it is. If no outliers exist, the
     * method returns "None".
     */
    public String listOutliers(double array[]) {
        String mildLower = "";
        String mildHigher = "";
        String extremeLower = "";
        String extremeHigher = "";
        double Q1val = Q1(array);
        double Q3val = Q3(array);
        double iqrVal = iqr(array);
        double ifl = Q1val - (1.5 * iqrVal);
        double ifh = Q3val + (1.5 * iqrVal);
        double ofl = Q1val - (3 * iqrVal);
        double ofh = Q3val + (3 * iqrVal);
        for (int i = 0; i < array.length; i++) {
            if (array[i] < ifl && array[i] > ofl) {
                mildLower = mildLower + " " + array[i];
            } else if (array[i] <= ofl) {
                extremeLower = extremeLower + " " + array[i];
            } else if (array[i] > ifh && array[i] < ofh) {
                mildHigher = mildHigher + " " + array[i];
            } else if (array[i] >= ofh) {
                extremeHigher = extremeHigher + " " + array[i];
            }
        }
        if (mildLower.equals("")) {
            mildLower = "None";
        }
        if (extremeLower.equals("")) {
            extremeLower = "None";
        }
        if (mildHigher.equals("")) {
            mildHigher = "None";
        }
        if (extremeHigher.equals("")) {
            extremeHigher = "None";
        }
        return "Mild Lower: " + mildLower + "\n" +
                "Extreme Lower: " + extremeLower + "\n" +
                "Mild Higher: " + mildHigher + "\n" +
                "Extreme Higher: " + extremeHigher;

    }

    /**
     * The dataset is inputted as an array of doubles. This method
     * returns the dataset itself.
     */
    public String listDataset(double array[]) {
        String dataset = "";
        for (int i = 0; i < array.length; i++) {
            dataset = dataset + " " + array[i];
        }
        return dataset;
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the maximum value of the dataset.
     */
    public double max(double array[]) {
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;

    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns the minimum value of the dataset.
     */
    public double min(double array[]) {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }

        }
        return min;
    }

    /**
     * The dataset is inputted as an array of doubles. The method
     * calculates and returns a sorted version of the dataset.
     */
    public String datasetSort(double array[]) {
        Arrays.sort(array);
        String dataset = "";
        for (int i = 0; i < array.length; i++) {
            dataset = dataset + " " + array[i];
        }
        return dataset;
    }


}
