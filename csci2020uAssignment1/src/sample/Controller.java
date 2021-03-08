package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.awt.Label;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import javafx.scene.image.*;
/**
 * Controller class instantiates tableview format
 * for output window in main program
 * <p>
 * This class sets up table columns
 * and writes values to each cell
 * @param   null
 * @return  null
 * @see     null
 */

public class Controller {

    @FXML private TableView tableView;
    @FXML private TableColumn<Object, Object> filename;
    @FXML private TableColumn<Object, Object> actualClass;
    @FXML private TableColumn<Object, Object> spamProbability;
    @FXML private ImageView hammerView;
    @FXML private ImageView spamView;
    private TableView<TestFile> file;

    @FXML private TextField accurText;
    @FXML private TextField precText;
    @FXML private TextField spamFilesTrained;
    @FXML private TextField hamFilesTrained;

    public Controller() {
    }

    @FXML
    public void initialize() {

        //images
        hammerView.setImage(new Image("resources/hammer.png"));
        spamView.setImage(new Image("resources/spam.jpg")); 

        //columns
        filename.setCellValueFactory(new PropertyValueFactory<>("filename"));
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        spamProbability.setCellValueFactory(new PropertyValueFactory<>("SpamProbRounded"));
        tableView.setItems(DataSource.getAllFiles());

        //textfields
        accurText.setText(String.valueOf((DataSource.getAccuracy())));
        precText.setText(String.valueOf((DataSource.getPrecision())));
        spamFilesTrained.setText(String.valueOf(DataSource.getNumberOfSpamFiles()));
        hamFilesTrained.setText(String.valueOf(DataSource.getNumberOfHamFiles()));
    }
}