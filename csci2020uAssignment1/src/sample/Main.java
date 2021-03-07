package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

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

        //Going through spam folders
        String spamFile = defaultDirectory + "/train/spam";
        File spam = new File(spamFile);
        WordCounter spamCounts = new WordCounter(spam);
        spamCounts.runWordCounter();

        //Going through ham folders
        String hamFile = defaultDirectory + "/train/ham";
        File ham = new File(hamFile);
        WordCounter hamCounts = new WordCounter(ham);
        hamCounts.runWordCounter();

        String hamFile2 = defaultDirectory + "/train/ham2";
        File ham2 = new File(hamFile2);
        WordCounter hamCounts2 = new WordCounter(ham2);
        hamCounts2.runWordCounter();

        //now we have the two maps

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {launch(args);}
}
