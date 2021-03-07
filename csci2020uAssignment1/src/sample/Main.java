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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Spam Destroyer 90000");
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
        Map<String, Float> percentageSpamWord = new TreeMap<String, Float>();
        //need to go over every word in the other two maps.
        Map<String, Float> spamWordMap = spamCounts.getWordCounts();
        Map<String, Float> hamWordMap = hamCounts.getWordCounts();
        float numberOfSpamFiles = spamCounts.getFileCounter();
        float numberOfHamFiles = hamCounts.getFileCounter();

        System.out.println("num of spam files " + numberOfSpamFiles);
        System.out.println("num of ham files " + numberOfHamFiles);
        //iterating over the TreeMap that contains spam
        for (Map.Entry<String,Float> entry : spamWordMap.entrySet()) {
            Float value = entry.getValue();
            String key = entry.getKey();
            if (hamWordMap.containsKey(key)) {
                float hamValue = hamWordMap.get(key);
                percentageSpamWord.put(key, ((hamValue/numberOfHamFiles) / ((value/numberOfSpamFiles)+(hamValue/numberOfHamFiles))));
            }
        }


        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {launch(args);}
}
