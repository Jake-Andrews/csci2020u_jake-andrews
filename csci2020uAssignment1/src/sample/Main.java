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

        String spamFile = defaultDirectory + "/test/spam";
        File spam = new File(spamFile);
        WordCounter test = new WordCounter(spam);
        test.runWordCounter();

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
