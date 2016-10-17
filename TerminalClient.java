import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Numerical Data Summary Terminal - TerminalClient
 *
 * This program does two tasks. The first is file reading,
 * that is, it reads the inputted text file and determines
 * if the content of the file can be used or if there is a
 * problem. If usable, the data is loaded into an array to
 * be manipulated according to the user's commands. The
 * second task is user interaction. The program prompts the
 * user to enter commands and then executes the users input.
 *
 * @author Vikram Pasumarti, vpasuma@purdue.edu
 * @version 17 October 2016
 */
public class TerminalClient {

    private double dataArray[];
    private boolean fileError = false;
    private boolean fileExists = false;

    /**
     * In the constructor, inputted files are read and their contents
     * are written to an Array List. They are then sorted and then
     * inputted into an array. After storing, the fileExists boolean
     * is set to true. This is to tell the fileCheck method that there
     * is a file loaded.
     */
    private TerminalClient(String fileName) {
        ArrayList<Double> dataset = new ArrayList<>();
        File f1 = new File(fileName);
        try {
            FileReader fr = new FileReader(f1);
            Scanner s = new Scanner(fr);
            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    dataset.add(s.nextDouble());
                } else {
                    s.next();
                }
            }
            dataArray = new double[dataset.size()];
            for (int i = 0; i < dataset.size(); i++) {
                this.dataArray[i] = dataset.get(i);
            }
            Arrays.sort(dataArray);
            fileExists = true;


        } catch (Exception e) {
            System.out.println("\nFile not found\n");
            fileError = true;
        }

    }

    /**
     * The fileExists boolean and the dataset array are inputted
     * into the method. The fileExists boolean determines if a file
     * exists, not if the file is valid. A file is considered valid if
     * there exists numbers in it. If there are no numbers in the file,
     * then nothing will be written to the array, which is why an array
     * with a length of zero would mean the file is invalid. A valid
     * file would return the string "Good file", while an invalid
     * file returns "Bad file".
     */
    private String fileCheck(boolean fileExists,double array[]) {
        if (!fileExists) {
            System.out.println("\nNo file loaded\n");
            return "Bad file";
        } else if (array.length == 0) {
            System.out.println("File invalid. Please check file for errors or load new file");
            return "Bad file";
        } else {
            return "Good file";
        }

    }

    public static void main(String[] args) {
        String check;
        String fileLocation = "";
        String fileName = "";
        DataFunctions d = new DataFunctions();
        Scanner s = new Scanner(System.in);

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
        System.out.println("@version 17 October 2016");
        System.out.println("---------------------------");
        System.out.println("Please select a file, then enter a command. If you need help, type 'help'");

        //Initial file choosing
        int backSlashIndex = 0;
        JFileChooser fc1 = new JFileChooser();
        int fileChoose1 = fc1.showOpenDialog(new JFrame());
        if (fileChoose1 == JFileChooser.APPROVE_OPTION) {
            fileLocation = fc1.getSelectedFile().getAbsolutePath();
            //Determines name of file from file path
            backSlashIndex = 0;
            for (int i = 0; i < fileLocation.length(); i++) {
                if (fileLocation.charAt(i) == '\\') {
                    backSlashIndex = i;
                }
            }
            fileName = fileLocation.substring(backSlashIndex + 1);
            System.out.println("\n" + fileName + " selected" + "\n");
        } else if (fileChoose1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("\nNo new file selected\n");
        }
        TerminalClient tc = new TerminalClient(fileLocation);
        //Initiates program loop
        do {
            String command = s.next();
            switch (command) {

                case "file":
                    JFileChooser fc2 = new JFileChooser();
                    int fileChoose2 = fc2.showOpenDialog(new JFrame());
                    if (fileChoose2 == JFileChooser.APPROVE_OPTION) {
                        fileLocation = fc2.getSelectedFile().getAbsolutePath();
                    } else if (fileChoose2 == JFileChooser.CANCEL_OPTION) {
                        System.out.println("\nNo new file selected\n");
                        break;
                    }
                    //Creates new instance for new file
                    tc = new TerminalClient(fileLocation);
                    if (tc.fileError) {
                        break;
                    }
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    backSlashIndex = 0;
                    for (int i = 0; i < fileLocation.length(); i++) {
                        if (fileLocation.charAt(i) == '\\') {
                            backSlashIndex = i;
                        }
                    }
                    fileName = fileLocation.substring(backSlashIndex + 1);
                    System.out.println("\n" + fileName + " selected" + "\n");
                    break;
                case "mean":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMean of dataset " + fileName + " = " + d.mean(tc.dataArray) + "\n");
                    break;
                case "median":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMedian of dataset " + fileName + " = " + d.median(tc.dataArray) + "\n");
                    break;
                case "mode":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    } else if (d.mode(tc.dataArray).isEmpty()) {
                        System.out.println("\nMode of dataset " + fileName + " = No mode" + "\n");
                    } else {
                        System.out.println("\nMode of dataset " + fileName + " = " + d.mode(tc.dataArray) + "\n");
                    }
                    break;
                case "skew":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    double medianVal = d.median(tc.dataArray);
                    double meanVal = d.mean(tc.dataArray);

                    System.out.println("\n Skew of dataset " + fileName + " is " + d.skew(meanVal, medianVal) + "\n");
                    break;
                case "stddev":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nStandard Deviation of dataset " + fileName + " = " + d.stdDev(tc.dataArray) + "\n");
                    break;
                case "range":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nRange of dataset " + fileName + " = " + d.range(tc.dataArray) + "\n");
                    break;
                case "Q1":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nFirst Quartile of dataset " + fileName + " = " + d.Q1(tc.dataArray) + "\n");
                    break;
                case "Q3":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nThird Quartile  of dataset " + fileName + " = " + d.Q3(tc.dataArray) + "\n");
                    break;
                case "iqr":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nInterquartile Range of dataset " + fileName + " = " + d.iqr(tc.dataArray) + "\n");
                    break;
                case "fivenumsum":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("Five Number Summary for " + fileName + " " + d.fiveNumSummary(tc.dataArray) + "\n");
                    break;
                case "outliers":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("\nBad file")) {
                        break;
                    }
                    System.out.println("\nOutliers for " + fileName + " " + d.outliers(tc.dataArray) + "\n");
                    break;
                case "listoutliers":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nList of Outliers for " + fileName + " " + d.listOutliers(tc.dataArray) + "\n");
                    break;
                case "listdataset":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nDataset " + fileName + ": " + d.listDataset(tc.dataArray) + "\n");
                    break;
                case "max":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMaximum of dataset " + fileName + " = " + d.max(tc.dataArray) + "\n");
                    break;
                case "min":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nMinimum of dataset " + fileName + " = " + d.min(tc.dataArray) + "\n");
                    break;
                case "sort":
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    System.out.println("\nSorted Dataset " + fileName + ": " + d.datasetSort(tc.dataArray) + "\n");
                    break;
                case "print":
                    //Builds a text file containing a full summary of the data
                    check = tc.fileCheck(tc.fileExists, tc.dataArray);
                    if (check.equals("Bad file")) {
                        break;
                    }
                    medianVal = d.median(tc.dataArray);
                    meanVal = d.mean(tc.dataArray);
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
                        pw.write("Outlier Conditions for " + fileName + ": \n " + d.outliers(tc.dataArray));
                        pw.println();
                        pw.println();
                        pw.write("List of Outliers for " + fileName + " \n" + d.listOutliers(tc.dataArray));
                        fw.close();
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid command.  If you need help, type 'help' " + "\n");
                    break;
            }
        }
        while (true);
    }


}



