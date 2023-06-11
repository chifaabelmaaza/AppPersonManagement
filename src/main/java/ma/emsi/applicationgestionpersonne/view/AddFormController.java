package ma.emsi.applicationgestionpersonne.view;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.emsi.applicationgestionpersonne.entities.Person;
import ma.emsi.applicationgestionpersonne.service.PersonService;

import java.util.Date;

public class AddFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private DatePicker dateNaissPicker;

    @FXML
    private TextField cinField;

    private PersonService personService;

    public AddFormController() {
        personService = new PersonService();
    }

    @FXML
    public void addPerson() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderChoiceBox.getValue();
        String address = addressField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        Date dateNaiss = java.sql.Date.valueOf(dateNaissPicker.getValue());
        String cin = cinField.getText();

  Person person = new Person(name, age, gender, address, email, phoneNumber, dateNaiss, cin);
        personService.save(person);

        // Clear the fields after adding the person
        clearFields();
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();

    }

    private void clearFields() {
        nameField.clear();
        ageField.clear();
        genderChoiceBox.setValue(null);
        addressField.clear();
        emailField.clear();
        phoneNumberField.clear();
        dateNaissPicker.setValue(null);
        cinField.clear();
    }
}
