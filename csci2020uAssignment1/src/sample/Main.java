package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;


/**
 * The main program that calls WordCounter and TreeMap to implement
 * calculating the probability an email is spam based on a given word
 * Program opens with directory chooser, then interprets specified file
 * paths to train email spam detection, data is saved to a map.
 * Data is manipulated with specific formulas to calculate probability
 * of spam. Then finally, sets and displays the stage
 * <p>
 * @param   null
 * @return  null
 * @see     TableView window
 */


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        double dontCount = 1;
        //Letting user choose directory, putting this into a String defaultDirectory
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        File mainDirectory = directoryChooser.showDialog(primaryStage);
        //mainDirectory contains the path to the folder you choose, absolute path
        String defaultDirectory = mainDirectory.getPath();
        System.out.println(defaultDirectory);

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

        //Creating probability map percentageSpamWord
        Map<String, Double> percentageSpamWord = new TreeMap<String, Double>();
        //Calling our objects to get the spam and ham wordMaps per file
        Map<String, Double> spamWordMap = spamCounts.getWordCounts();
        Map<String, Double> hamWordMap = hamCounts.getWordCounts();
        //Number of files used later on
        double numberOfSpamFiles = spamCounts.getFileCounter();
        double numberOfHamFiles = hamCounts.getFileCounter();
        double numberOfFiles = numberOfHamFiles + numberOfSpamFiles;
        System.out.println("num of spam files " + numberOfSpamFiles);
        System.out.println("num of ham files " + numberOfHamFiles);
        System.out.println(spamWordMap.size());
        System.out.println(hamWordMap.size());

        //iterating over the TreeMap that contains spam
        for (Map.Entry<String,Double> entry : spamWordMap.entrySet()) {
            double value = entry.getValue();
            String key = entry.getKey();
            //if hamWordMap and spamWordMap contain a key, then do calculation
            //Also checks to make sure the word wasn't only used once in both spam and ham folders.
            if (hamWordMap.containsKey(key) && (hamWordMap.get(key) > dontCount || spamWordMap.get(key) > dontCount)) {
                double hamValue = hamWordMap.get(key);
                percentageSpamWord.put(key, ((value/numberOfSpamFiles) / ((value/numberOfSpamFiles)+(hamValue/numberOfHamFiles))));
            }
            //hamWordMap does not contain the word therefore 1
            else if(spamWordMap.get(key) > dontCount) {
                percentageSpamWord.put(key, 1.0); //since ((value/numberOfSpamFiles) / ((value/numberOfSpamFiles))) = 1.0
            }

        }
        //going over the entries that are in hamWordMap but not spamWordMap aka words unique to the ham files in train

        for (Map.Entry<String,Double> entry : hamWordMap.entrySet()) {
            double value = entry.getValue();
            String key = entry.getKey();
            if (!(spamWordMap.containsKey(key)) && (hamWordMap.get(key) > dontCount)) {
                double hamValue = hamWordMap.get(key);
                percentageSpamWord.put(key, 0.0); //if the word was not in spamMap then it's 0/# and therefore = 0.0
            }
        }

        //create DataSource object that will iterate over every file and calculate probaiblities
        DataSource percentages = new DataSource(percentageSpamWord, defaultDirectory, numberOfHamFiles, numberOfSpamFiles);
        percentages.calculateFileSpamProbabilities();
        //setting scene, etc...
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Spam Destroyer 90000");
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
