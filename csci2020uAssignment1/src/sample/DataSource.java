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
    private static Double accuracy = 0.0;
    private static Double precision = 0.0;
    public Map<String, Double> wordSpamProbabilities;
    public String directoryName;
    public static double numberOfFiles;
    public static double numberOfHamFiles;
    public static double numberOfSpamFiles;

    public DataSource (Map<String, Double> spamMap, String fileName, double numHamFiles, double numSpamFiles) {
        this.directoryName = fileName;
        this.wordSpamProbabilities = spamMap;
        numberOfHamFiles = numHamFiles;
        numberOfSpamFiles = numSpamFiles;
        numberOfFiles = numHamFiles + numSpamFiles;
    }

    public static ObservableList<TestFile> getAllFiles() {return files;}

    //loops through every file in both directories, uses ProbabilityCounter.
    public void calculateFileSpamProbabilities() {
        String spamPath = this.directoryName + "/test/spam";
        String hamPath = this.directoryName + "/test/ham";

        //.runWordCounter goes through every file in the directory given to it and assigns probabiliy the file is spam
        ProbabilityCounter spamFilesCounted = new ProbabilityCounter(wordSpamProbabilities, spamPath);
        spamFilesCounted.runWordCounter();
        ProbabilityCounter hamFilesCounted = new ProbabilityCounter(wordSpamProbabilities, hamPath);
        hamFilesCounted.runWordCounter();

        double numTruePositives = 0.0;
        double numFalsePositives = 0.0;

        //Since .runWordCounter contains the probabilities and filenames in an array
        //need to loop through the array's to add each probability and filename to the
        //observable list<TestFile> which is used in Controller to set table
        for (int k = 0; k < hamFilesCounted.filesCounted; k++) {
            files.add(new TestFile(hamFilesCounted.fileNames[k], hamFilesCounted.probabilitiesPerFile[k], "Ham"));
            if (hamFilesCounted.probabilitiesPerFile[k] < 0.50) {
                numTruePositives++;
            }
            else {
                numFalsePositives++;
            }
        }
        //same as above just for spam
        for (int i = 0; i < spamFilesCounted.filesCounted; i++) {
            files.add(new TestFile(spamFilesCounted.fileNames[i], spamFilesCounted.probabilitiesPerFile[i], "Spam"));
            if (spamFilesCounted.probabilitiesPerFile[i] > 0.50) {
                numTruePositives++;
            }
            else {
                numFalsePositives++;
            }
        }
        accuracy = (numFalsePositives + numTruePositives) / numberOfFiles;
        precision = numTruePositives / (numFalsePositives + numTruePositives);
        System.out.println("Accuracy: " + accuracy);
        System.out.println("Precision: " + precision);

    }
    public static double getAccuracy(){return accuracy;}
    public static double getPrecision(){return precision;}
    public static double getNumberOfHamFiles(){return numberOfHamFiles;}
    public static double getNumberOfSpamFiles(){return numberOfSpamFiles;}
}

