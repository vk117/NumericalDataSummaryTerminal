import java.util.ArrayList;
import java.util.Arrays;

/**
 * Numerical Data Summary Terminal - Data Functions
 * 
 * This program contains methods which are all the valid
 * commands that a user can enter into the terminal.
 *
 * @author Vikram Pasumarti, vpasuma@purdue.edu
 * @version 6 September 2016
 */
public class DataFunctions {

    public String mean(double array[]) {
        double sum = 0;
        double average = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        average = sum / (array.length);
        return Double.toString(average);
    }

    public String median(double array[]) {
        Arrays.sort(array);
        int index = 0;
        if (array.length % 2.0 != 0.0) {
            index = ((array.length - 1) / 2);
            return Double.toString(array[index]);
        } else {
            int index2 = 0;
            index2 = (array.length / 2);
            index = index2 - 1;
            double evenMedian = (array[index] + array[index2]) / 2;
            return Double.toString(evenMedian);
        }
    }

    public String mode(double array[]) {
        int count = 0;
        String modeString = "";
        int maxCount = 2;
        if (array.length == 1) {
            return Double.toString(array[0]);
        }
        ArrayList<Double> dataValues = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (!(dataValues.contains(array[i]))) {
                dataValues.add(array[i]);
            }
        }
        for (int j = 0; j < dataValues.size(); j++) {
            count = 0;
            for (int k = 0; k < array.length; k++) {
                if (dataValues.get(j) == array[k]) {
                    count = count + 1;
                }
                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }
        for (int j = 0; j < dataValues.size(); j++) {
            if (count == maxCount) {
                modeString = modeString + " " + dataValues.get(j - 1);
            }
            count = 0;
            for (int k = 0; k < array.length; k++) {
                if (dataValues.get(j) == array[k]) {
                    count = count + 1;
                }

            }
        }
        if (modeString.equals("")) {
            return "No mode";
        }
        return modeString;
    }

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

    public String stdDev(double array[]) {
        double sum = 0.0;
        double variance = 0.0;
        double meanVal = Double.parseDouble(mean(array));
        for (int i = 0; i < array.length; i++) {
            sum = sum + Math.pow((array[i] - meanVal), 2);
        }
        variance = ((1.0 / (array.length - 1.0)) * (sum));
        return Double.toString(Math.sqrt(variance));
    }

    public String range(double array[]) {
        double max = array[0];
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        return Double.toString(max - min);
    }

    public String Q1(double array[]) {
        Arrays.sort(array);
        int d1 = array.length / 4;
        String integerCheck = Double.toString(d1);
        if (integerCheck.contains(".0")) {
            return Double.toString(array[d1]);
        } else {
            return Double.toString((array[d1] + array[d1 + 1]) / 2);
        }
    }

    public String Q3(double array[]) {
        Arrays.sort(array);
        int d3 = (3 * array.length) / 4;
        String integerCheck = Double.toString(d3);
        if (integerCheck.contains(".0")) {
            return Double.toString(array[d3]);
        } else {
            return Double.toString((array[d3] + array[d3 + 1]) / 2);
        }
    }

    public String iqr(double array[]) {
        double Q1val = Double.parseDouble(Q1(array));
        double Q3val = Double.parseDouble(Q3(array));
        return Double.toString(Q3val - Q1val);
    }

    public String fiveNumSummary(double array[]) {
        double max = array[0];
        double min = array[0];
        double Q1val = Double.parseDouble(Q1(array));
        double Q3val = Double.parseDouble(Q3(array));
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        double medianVal = Double.parseDouble(median(array));
        return "Minimum = " + min + ", Q1 = " + Q1val + ", Median = " + medianVal + ", Q3 = " + Q3val + ", Maximum = " + max;
    }

    public String outliers(double array[]) {
        double Q1val = Double.parseDouble(Q1(array));
        double Q3val = Double.parseDouble(Q3(array));
        double iqrVal = Double.parseDouble(iqr(array));
        double ifl = Q1val - (1.5 * iqrVal);
        double ifh = Q3val + (1.5 * iqrVal);
        double ofl = Q1val - (3 * iqrVal);
        double ofh = Q3val + (3 * iqrVal);
        return "Values between " + ofl + " and " + ifl + " are mild outliers on the lower end \n" +
                "Values below " + ofl + " are extreme outliers on the lower end \n" +
                "Values between " + ifh + " and " + ofh + " are mild outliers on the higher end \n" +
                "Values above " + ofh + " are extreme outliers on the higher end";
    }

    public String listOutliers(double array[]) {
        String mildLower = "";
        String mildHigher = "";
        String extremeLower = "";
        String extremeHigher = "";
        double Q1val = Double.parseDouble(Q1(array));
        double Q3val = Double.parseDouble(Q3(array));
        double iqrVal = Double.parseDouble(iqr(array));
        double ifl = Q1val - (1.5 * iqrVal);
        double ifh = Q3val + (1.5 * iqrVal);
        double ofl = Q1val - (3 * iqrVal);
        double ofh = Q3val + (3 * iqrVal);
        for (int i = 0; i < array.length; i++) {
            if (array[i] < ifl && array[i] > ofl) {
                mildLower = mildLower + " " + Double.toString(array[i]);
            } else if (array[i] <= ofl) {
                extremeLower = extremeLower + " " + Double.toString(array[i]);
            } else if (array[i] > ifh && array[i] < ofh) {
                mildHigher = mildHigher + " " + Double.toString(array[i]);
            } else if (array[i] >= ofh) {
                extremeHigher = extremeHigher + " " + Double.toString(array[i]);
            }
        }
        return "Mild Lower: " + mildLower + "\n" +
                "Extreme Lower: " + extremeLower + "\n" +
                "Mild Higher: " + mildHigher + "\n" +
                "Extreme Higher: " + extremeHigher;

    }

    public String listDataset(double array[]) {
        String dataset = "";
        for (int i = 0; i < array.length; i++) {
            dataset = dataset + " " + array[i];
        }
        return dataset;
    }

    public String max(double array[]) {
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return Double.toString(max);

    }

    public String min(double array[]) {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }

        }
        return Double.toString(min);
    }

    public String datasetSort(double array[]) {
        Arrays.sort(array);
        String dataset = "";
        for (int i = 0; i < array.length; i++) {
            dataset = dataset + " " + array[i];
        }
        return dataset;
    }


}
