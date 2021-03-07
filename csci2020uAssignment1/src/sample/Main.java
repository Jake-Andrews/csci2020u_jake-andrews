package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        File mainDirectory = directoryChooser.showDialog(primaryStage);
        //mainDirectory contains the path to the folder you choose, absolute path
        String defaultDirectory = mainDirectory.getPath();
        System.out.println(defaultDirectory);

        //Should move everything below here to a class.
        //Too much coding in the main

        //Going through spam folders
        String spamFile = defaultDirectory + "/train/spam";
        File spam = new File(spamFile);
        WordCounter spamCounts = new WordCounter(spam);
        spamCounts.runWordCounter();

        //Going through ham folders
        //Ham1
        String hamFile = defaultDirectory + "/train/ham";
        File ham = new File(hamFile);
        WordCounter hamCounts = new WordCounter(ham);
        hamCounts.runWordCounter();

        //Ham2
        String hamFile2 = defaultDirectory + "/train/ham2";
        File ham2 = new File(hamFile2);
        hamCounts.setInputFileName(ham2);
        hamCounts.runWordCounter();

        //now we have the two maps
        Map<String, Double> percentageSpamWord = new TreeMap<String, Double>();
        //need to go over every word in the other two maps.
        Map<String, Double> spamWordMap = spamCounts.getWordCounts();
        Map<String, Double> hamWordMap = hamCounts.getWordCounts();
        double numberOfSpamFiles = spamCounts.getFileCounter();
        double numberOfHamFiles = hamCounts.getFileCounter();
        double numberOfFiles = numberOfHamFiles + numberOfSpamFiles;
        //System.out.println("num of spam files " + numberOfSpamFiles);
        //System.out.println("num of ham files " + numberOfHamFiles);
        System.out.println(spamWordMap.size());
        System.out.println(hamWordMap.size());
        //iterating over the TreeMap that contains spam
        for (Map.Entry<String,Double> entry : spamWordMap.entrySet()) {
            double value = entry.getValue();
            String key = entry.getKey();
            if (hamWordMap.containsKey(key)) {
                double hamValue = hamWordMap.get(key);
                percentageSpamWord.put(key, ((value/numberOfSpamFiles) / ((value/numberOfSpamFiles)+(hamValue/numberOfHamFiles))));
            }
            else {
                percentageSpamWord.put(key, 1.0); //since ((value/numberOfSpamFiles) / ((value/numberOfSpamFiles))) = 1.0
            }
            //System.out.println(percentageSpamWord.get(key));
        }
        //going over the entries that are in hamWordMap but not spamWordMap aka words unique to the ham files in train

        for (Map.Entry<String,Double> entry : hamWordMap.entrySet()) {
            double value = entry.getValue();
            String key = entry.getKey();
            if (!(spamWordMap.containsKey(key))) {
                double hamValue = hamWordMap.get(key);
                percentageSpamWord.put(key, 0.0); //if the word was not in spamMap then it's 0/# and therefore = 0.0
                //System.out.println(percentageSpamWord.get(key));
            }
        }




        //System.out.println(defaultDirectory);
        DataSource percentages = new DataSource(percentageSpamWord, defaultDirectory, numberOfFiles);
        percentages.calculateFileSpamProbabilities();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Spam Destroyer 90000");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {launch(args);}
}
