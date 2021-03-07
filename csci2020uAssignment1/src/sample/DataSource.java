package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {
    public static ObservableList<TestFile> getAllFiles()
    {
        ObservableList<TestFile> files = FXCollections.observableArrayList();
        // Student ID, Assignments, Midterm, Final exam
        //files.add(new TestFile("100100100", 75.0f, 68.0f,54.25f));

        return files;
    }
}

