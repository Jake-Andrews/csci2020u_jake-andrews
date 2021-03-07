package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    @FXML private TableView tableView;

    @FXML private TableColumn<Object, Object> fileName;
    @FXML private TableColumn<Object, Object> actualClass;
    @FXML private TableColumn<Object, Object> spamProbability;

    private TableView<TestFile> file;

    @FXML
    public void initialize() {
        //doesn't work yet, need to make another class I think?
        fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        spamProbability.setCellValueFactory(new PropertyValueFactory<>("spamProbability"));
        //tableView.setItems(DataSource.getAllFiles());
        //tableView.getColumns().addAll(fileName, actualClass, spamProbability);

    }
}