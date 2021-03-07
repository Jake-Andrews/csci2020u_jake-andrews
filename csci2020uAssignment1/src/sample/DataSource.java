package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class DataSource {
    public static ObservableList<TestFile> files = FXCollections.observableArrayList();
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

        double accuracy = 0.0;
        double precision = 0.0;
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
}

