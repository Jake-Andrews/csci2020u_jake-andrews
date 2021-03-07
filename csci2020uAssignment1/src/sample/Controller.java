package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    @FXML private TableView tableView;

    @FXML private TableColumn<Object, Object> studentID;
    @FXML private TableColumn<Object, Object> assignmentMark;
    @FXML private TableColumn<Object, Object> midtermMark;
    @FXML private TableColumn<Object, Object> finalExamMark;
    @FXML private TableColumn<Object, Object> finalMark;
    @FXML private TableColumn<Object, Object> letterGrade;

    //private TableView<StudentRecord> student;

    @FXML
    public void initialize() {

        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        assignmentMark.setCellValueFactory(new PropertyValueFactory<>("assignmentMark"));
        midtermMark.setCellValueFactory(new PropertyValueFactory<>("midtermMark"));
        finalExamMark.setCellValueFactory(new PropertyValueFactory<>("finalExamMark"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        //tableView.setItems(DataSource.getAllMarks());
        //tableView.getColumns().addAll(studentID, assignmentMark, midtermMark, finalExamMark, finalMark, letterGrade);

    }
}