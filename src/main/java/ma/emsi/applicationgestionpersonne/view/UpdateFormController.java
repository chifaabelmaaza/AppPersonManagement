package ma.emsi.applicationgestionpersonne.view;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ma.emsi.applicationgestionpersonne.entities.Person;
import ma.emsi.applicationgestionpersonne.service.PersonService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UpdateFormController {

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

    // Add necessary fields for other person attributes

    private PersonService personService;
    private Person selectedPerson;

    public UpdateFormController() {
        personService = new PersonService();
    }

    public  LocalDate LOCAL_DATE(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    public void setSelectedPerson(Person person) {

            // Set the initial values of the fields based on the selected person's attributes
            selectedPerson = person;
            nameField.setText(person.getName());
            ageField.setText(String.valueOf(person.getAge()));
            genderChoiceBox.setValue(person.getGender());
            addressField.setText(person.getAddress());
            emailField.setText(person.getEmail());
            phoneNumberField.setText(person.getPhoneNumber());
           // LocalDate dateNaiss = person.getDateNaiss().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           // dateNaissPicker.setValue(dateNaiss);


        dateNaissPicker.setValue(LOCAL_DATE(person.getDateNaiss().toString()));
            cinField.setText(person.getCin());


    }

    @FXML
    public void updatePerson() {
        // Get the updated values from the fields
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderChoiceBox.getValue();
        String address = addressField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        LocalDate dateNaiss = dateNaissPicker.getValue();
        Date dateNaissDate = Date.from(dateNaiss.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String cin = cinField.getText();
        // Get the updated values from other fields

        // Update the selected person's attributes
        selectedPerson.setName(name);
        selectedPerson.setAge(age);
        selectedPerson.setGender(gender);
        selectedPerson.setAddress(address);
        selectedPerson.setEmail(email);
        selectedPerson.setPhoneNumber(phoneNumber);
        selectedPerson.setDateNaiss(dateNaissDate);
        selectedPerson.setCin(cin);
        // Update other person attributes

        // Call the personService.update() method to update the person's record in the database
        personService.update(selectedPerson);

        // Close the update form window
        nameField.getScene().getWindow().hide();
    }
}
