package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

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

    private TableView<TestFile> file;

    @FXML private Label accuracy;
    @FXML private Label precision;
    @FXML private TextField accurText;
    @FXML private TextField precText;

    public Controller() {
    }

    @FXML
    public void initialize() {
        //tableView.getColumns().addAll(filename, actualClass, spamProbability);

        filename.setCellValueFactory(new PropertyValueFactory<>("filename"));
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        spamProbability.setCellValueFactory(new PropertyValueFactory<>("SpamProbRounded"));
        tableView.setItems(DataSource.getAllFiles());
        accurText =new TextField((String)(DataSource.getAccuracy()));
        precText =new TextField((String)(DataSource.getPrecision()));
        //tableView.getColumns().addAll(fileName, actualClass, spamProbability);

    }
}