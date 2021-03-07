package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

/**
 * DataSource class returns an observableList of test files
 * that contain all the tested files and their spam probability.
 * Has functions to compute the probability each file is spam,
 * and provided acuracy and percision of the calculation
 * Has appropriate getters
 * <p>
 * This class creates a list of all files tested with their spam probability.
 * @param   spamMap     Treemap containing words and how many files contain them
 * @param   fileName    String name of the file to read
 * @param   numFiles    double value file counter
 * @return  files       ObservableList of test files, list gets file appended after its been read.
 *
 */

public class DataSource {
    public static ObservableList<TestFile> files = FXCollections.observableArrayList();
    private static Double accuracy;
    private static Double precision;
    public Map<String, Double> wordSpamProbabilities;
    public String directoryName;
    public double numberOfFiles;

    public DataSource (Map<String, Double> spamMap, String fileName, double numFiles) {
        this.directoryName = fileName;
        this.wordSpamProbabilities = spamMap;
        this.numberOfFiles = numFiles;
    }

    public static ObservableList<TestFile> getAllFiles() {return files;}

    public void calculateFileSpamProbabilities() {
        String spamPath = this.directoryName + "/test/spam";
        String hamPath = this.directoryName + "/test/ham";
        ProbabilityCounter spamFilesCounted = new ProbabilityCounter(wordSpamProbabilities, spamPath);
        spamFilesCounted.runWordCounter();
        ProbabilityCounter hamFilesCounted = new ProbabilityCounter(wordSpamProbabilities, hamPath);
        hamFilesCounted.runWordCounter();

        accuracy = 0.0;
        precision = 0.0;
        double numTruePositives = 0.0;
        double numFalsePositives = 0.0;

        for (int k = 0; k < hamFilesCounted.filesCounted; k++) {
            files.add(new TestFile(hamFilesCounted.fileNames[k], hamFilesCounted.probabilitiesPerFile[k], "Ham"));
            if (hamFilesCounted.probabilitiesPerFile[k] < 0.50) {
                numTruePositives++;
            }
            else {
                numFalsePositives++;
            }
            //System.out.println(hamFilesCounted.fileNames[k]);
            //System.out.println(hamFilesCounted.probabilitiesPerFile[k]);
        }

        for (int i = 0; i < spamFilesCounted.filesCounted; i++) {
            //System.out.println(spamFilesCounted.probabilitiesPerFile[i]);
            //System.out.println(spamFilesCounted.fileNames[i]);
            files.add(new TestFile(spamFilesCounted.fileNames[i], spamFilesCounted.probabilitiesPerFile[i], "Spam"));
            if (spamFilesCounted.probabilitiesPerFile[i] > 0.50) {
                numTruePositives++;
            }
            else {
                numFalsePositives++;
            }
        }
        accuracy = (numFalsePositives + numTruePositives) / this.numberOfFiles;
        precision = numTruePositives / (numFalsePositives + numTruePositives);
        System.out.println("Accuracy: " + accuracy);
        System.out.println("Precision: " + precision);

    }
    public static double getAccuracy(){return accuracy;}
    public static double getPrecision(){return precision;}
}

