package sample;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.time.LocalDate;

public class Controller {
    public Button button;
    public TextField username;
    public PasswordField password;
    public TextField fullName;
    public TextField eMail;
    public TextField phoneNumber;
    public DatePicker dateOfBirth;

    public void register(){
        String usersUsername = username.getText();
        String usersPassword = password.getText();
        String usersFullName = fullName.getText();
        String usersEMail = eMail.getText();
        String usersPhoneNumber = phoneNumber.getText();
        LocalDate usersDateOfBirth = dateOfBirth.getValue();

        System.out.println("Username: " + usersUsername);
        System.out.println("Password: " + usersPassword);
        System.out.println("Full Name: " + usersFullName);
        System.out.println("E-Mail: " + usersEMail);
        System.out.println("Phone Number: " + usersPhoneNumber);
        System.out.println("Date of Birth: " + usersDateOfBirth);
    }
}
