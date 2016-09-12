import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Numerical Data Summary Terminal - TerminalClient
 * <p>
 * This program does two tasks. The first is file reading,
 * that is, it reads the inputted text file and determines
 * if the content of the file can be used or if there is a
 * problem. If usable, the data is loaded into an array to
 * be manipulated according to the user's commands. The
 * second task is user interaction. The program prompts the
 * user to enter commands and then executes the users input.
 *
 * @author Vikram Pasumarti, vpasuma@purdue.edu
 * @version 10 September 2016
 */
public class TerminalClient {
    ArrayList<Double> dataset = new ArrayList<>();
    double dataArray[];
    String fileName;
    boolean fileError = false;

    public TerminalClient(String fileName) {
        File f1 = new File(fileName);
        this.fileName = fileName;
        try {
            FileReader fr = new FileReader(f1);
            Scanner s = new Scanner(fr);
            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    this.dataset.add(s.nextDouble());
                } else {
                    s.next();
                }
            }
            this.dataArray = new double[this.dataset.size()];
            for (int i = 0; i < this.dataset.size(); i++) {
                this.dataArray[i] = this.dataset.get(i);
            }


        } catch (Exception e) {
            System.out.println("\nFile not found\n");
            fileError = true;
        }

    }

    public String fileCheck(String fileLocation, double array[]) {
        if (fileLocation == null) {
            System.out.println("\nNo file loaded\n");
            return "Bad file";
        } else if (array.length == 0) {
            System.out.println("File invalid. Please check " + fileLocation + " for errors or load new file");
            return "Bad file";
        } else {
            return "Good file";
        }

    }

    public static void main(String[] args) {
        String check = "";
        String fileLocation = null;
        boolean repeat = true;
        String fileName = null;
        DataFunctions d = new DataFunctions();
        Scanner s = new Scanner(System.in);
        TerminalClient tc = new TerminalClient("null.txt");
        System.out.println("\n" +
                "\n" +
                "      _   _                                     _                  _     ____            _           \n" +
                "     | \\ | |  _   _   _ __ ___     ___   _ __  (_)   ___    __ _  | |   |  _ \\    __ _  | |_    __ _ \n" +
                "     |  \\| | | | | | | '_ ` _ \\   / _ \\ | '__| | |  / __|  / _` | | |   | | | |  / _` | | __|  / _` |\n" +
                "     | |\\  | | |_| | | | | | | | |  __/ | |    | | | (__  | (_| | | |   | |_| | | (_| | | |_  | (_| |\n" +
                "     |_| \\_|  \\__,_| |_| |_| |_|  \\___| |_|    |_|  \\___|  \\__,_| |_|   |____/   \\__,_|  \\__|  \\__,_|\n");
        System.out.println("      ____                                                             _____                             _                   _ \n" +
                "     / ___|   _   _   _ __ ___    _ __ ___     __ _   _ __   _   _    |_   _|   ___   _ __   _ __ ___   (_)  _ __     __ _  | |\n" +
                "     \\___ \\  | | | | | '_ ` _ \\  | '_ ` _ \\   / _` | | '__| | | | |     | |    / _ \\ | '__| | '_ ` _ \\  | | | '_ \\   / _` | | |\n" +
                "      ___) | | |_| | | | | | | | | | | | | | | (_| | | |    | |_| |     | |   |  __/ | |    | | | | | | | | | | | | | (_| | | |\n" +
                "     |____/   \\__,_| |_| |_| |_| |_| |_| |_|  \\__,_| |_|     \\__, |     |_|    \\___| |_|    |_| |_| |_| |_| |_| |_|  \\__,_| |_|\n" +
                "                                                             |___/                                                             \n" +
                "\n");
        System.out.println("@author Vikram Pasumarti");
        System.out.println("@version 10 September 2016");
        System.out.println("---------------------------");
        System.out.println("Please Enter a Command. If you need help, type 'help'");
        do {
            String command = s.next();
            switch (command) {

                case "file":
                    //System.out.println("\nEnter file name\n");
                    JFileChooser jfc = new JFileChooser();
                    int fileChoose = jfc.showOpenDialog(new JFrame());
                    if (fileChoose == JFileChooser.APPROVE_OPTION) {
                        fileLocation = jfc.getSelectedFile().getAbsolutePath();
                    } else if (fileChoose == JFileChooser.CANCEL_OPTION) {
                        System.out.println("\nNo new file selected\n");
                        break;
                    }
                    tc = new TerminalClient(fileLocation);
                    if (tc.fileError) {
                        break;
                    }
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    int backSlashIndex = 0;
                    for (int i = 0; i < fileLocation.length(); i++) {
                        if (fileLocation.charAt(i) == '\\') {
                            backSlashIndex = i;
                        }
                    }
                    fileName = fileLocation.substring(backSlashIndex + 1);
                    System.out.println("\n" + fileName + " selected" + "\n");
                    break;
                case "mean":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMean of dataset " + fileName + " = " + d.mean(tc.dataArray) + "\n");
                    break;
                case "median":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMedian of dataset " + fileName + " = " + d.median(tc.dataArray) + "\n");
                    break;
                case "mode":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMode of dataset " + fileName + " = " + d.mode(tc.dataArray) + "\n");
                    break;
                case "skew":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    double medianVal = Double.parseDouble(d.median(tc.dataArray));
                    double meanVal = Double.parseDouble(d.mean(tc.dataArray));

                    System.out.println("\n Skew of dataset " + fileName + " is " + d.skew(meanVal, medianVal) + "\n");
                    break;
                case "stddev":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nStandard Deviation of dataset " + fileName + " = " + d.stdDev(tc.dataArray) + "\n");
                    break;
                case "range":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nRange of dataset " + fileName + " = " + d.range(tc.dataArray) + "\n");
                    break;
                case "Q1":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nFirst Quartile of dataset " + fileName + " = " + d.Q1(tc.dataArray) + "\n");
                    break;
                case "Q3":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nThird Quartile  of dataset " + fileName + " = " + d.Q3(tc.dataArray) + "\n");
                    break;
                case "iqr":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nInterquartile Range of dataset " + fileName + " = " + d.iqr(tc.dataArray) + "\n");
                    break;
                case "fivenumsum":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("Five Number Summary for " + fileName + " " + d.fiveNumSummary(tc.dataArray) + "\n");
                    break;
                case "outliers":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("\nBad file")) {
                        break;
                    }
                    System.out.println("\nOutliers for " + fileName + " " + d.outliers(tc.dataArray) + "\n");
                    break;
                case "listoutliers":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nList of Outliers for " + fileName + " " + d.listOutliers(tc.dataArray) + "\n");
                    break;
                case "listdataset":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nDataset " + fileName + ": " + d.listDataset(tc.dataArray) + "\n");
                    break;
                case "max":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMaximum of dataset " + fileName + " = " + d.max(tc.dataArray) + "\n");
                    break;
                case "min":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMinimum of dataset " + fileName + " = " + d.min(tc.dataArray) + "\n");
                    break;
                case "sort":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nSorted Dataset " + fileName + ": " + d.datasetSort(tc.dataArray) + "\n");
                    break;
                case "print":
                    check = tc.fileCheck(fileLocation, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    medianVal = Double.parseDouble(d.median(tc.dataArray));
                    meanVal = Double.parseDouble(d.mean(tc.dataArray));
                    File f2 = new File(fileName + "_numerical_summary.txt");
                    try {
                        FileWriter fw = new FileWriter(f2);
                        PrintWriter pw = new PrintWriter(fw);
                        pw.write("Numerical Summary for Dataset " + fileName);
                        pw.println();
                        pw.write("------------------------------------------");
                        pw.println();
                        pw.println();
                        pw.write("Dataset " + fileName + ": " + d.listDataset(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.print("Sorted Dataset " + fileName + ": " + d.datasetSort(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Mean of dataset " + fileName + " = " + d.mean(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Median of dataset " + fileName + " = " + d.median(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Mode of dataset " + fileName + " = " + d.mode(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Skew of dataset " + fileName + " is " + d.skew(meanVal, medianVal));
                        pw.println();
                        pw.println();
                        pw.write("Standard Deviation of dataset " + fileName + " = " + d.stdDev(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Maximum of dataset " + fileName + " = " + d.max(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Minimum of dataset " + fileName + " = " + d.min(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Range of dataset " + fileName + " = " + d.range(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("First Quartile of dataset " + fileName + " = " + d.Q1(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Third Quartile of dataset " + fileName + " = " + d.Q3(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Interquartile Range of dataset " + fileName + " = " + d.iqr(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Five Number Summary for " + fileName + ": " + d.fiveNumSummary(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("Outliers for " + fileName + ": \n " + d.outliers(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("List of Outliers for " + fileName + " \n" + d.listOutliers(tc.dataArray));
                        pw.close();
                        System.out.println("\nText file made successfully. It will be visible once this" +
                                " program is exited\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "help":
                    System.out.println("\nCommand List");
                    System.out.println("------------");
                    System.out.println("file: Load a dataset text file into program");
                    System.out.println("listdataset: Returns a list of all data in the file");
                    System.out.println("sort: Returns a sorted list of all data in the file from lowest to highest");
                    System.out.println("mean: Returns the average (mean) of the data");
                    System.out.println("median: Returns the median of the data");
                    System.out.println("mode: Returns the mode of the data");
                    System.out.println("skew: Returns how skewed the data is");
                    System.out.println("stddev: Returns the standard deviation of th data");
                    System.out.println("max: Returns the maximum of the data");
                    System.out.println("min: Returns the minimum of the data");
                    System.out.println("range: Returns the rang of the data");
                    System.out.println("Q1: Returns the First Quartile of the data");
                    System.out.println("Q3: Returns the Third Quartile of the data");
                    System.out.println("iqr: Returns the Interquartile Range of the data");
                    System.out.println("fivenumsum: Returns the Five Number Summary of the data");
                    System.out.println("outliers: Returns a summary of the outliers based on inner and outer fences");
                    System.out.println("listoutliers: Returns a list of all outliers in the file");
                    System.out.println("print: Writes a full numerical data summary to a text file");
                    System.out.println("help: Returns a list of commands");
                    System.out.println("\n");
                    break;
                case "exit":
                    System.out.println("\nGoodbye \n");
                    repeat = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid command.  If you need help, type 'help' " + "\n");
                    break;
            }
        }
        while (repeat);


    }

}

