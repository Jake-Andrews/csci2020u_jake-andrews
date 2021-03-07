package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public Controller() {
    }

    @FXML
    public void initialize() {
        //tableView.getColumns().addAll(filename, actualClass, spamProbability);

        filename.setCellValueFactory(new PropertyValueFactory<>("filename"));
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        spamProbability.setCellValueFactory(new PropertyValueFactory<>("SpamProbRounded"));
        tableView.setItems(DataSource.getAllFiles());
        //tableView.getColumns().addAll(fileName, actualClass, spamProbability);

    }
}