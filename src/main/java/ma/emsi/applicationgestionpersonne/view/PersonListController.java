package ma.emsi.applicationgestionpersonne.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Cell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.emsi.applicationgestionpersonne.entities.Person;
import ma.emsi.applicationgestionpersonne.service.PersonService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PersonListController {

    @FXML
    private TableView<Person> personTable;
    PersonService personService= new PersonService();
    @FXML
    private Button importButtontxt;

    @FXML
    private Button exportButtontxt;
    @FXML
    private Button importButtonexl;


    @FXML
    private Button exportButtonexl;

    public void initialize() {

        TableColumn<Person, Integer> idColumn = (TableColumn<Person, Integer>) personTable.getColumns().get(0);
        TableColumn<Person, String> nameColumn = (TableColumn<Person, String>) personTable.getColumns().get(1);
        TableColumn<Person, Integer> ageColumn = (TableColumn<Person, Integer>) personTable.getColumns().get(2);
        TableColumn<Person, String> genderColumn = (TableColumn<Person, String>) personTable.getColumns().get(3);
        TableColumn<Person, String> addressColumn = (TableColumn<Person, String>) personTable.getColumns().get(4);
        TableColumn<Person, String> emailColumn = (TableColumn<Person, String>) personTable.getColumns().get(5);
        TableColumn<Person, String> phoneNumberColumn = (TableColumn<Person, String>) personTable.getColumns().get(6);
        TableColumn<Person, Date> dateNaissColumn = (TableColumn<Person, Date>) personTable.getColumns().get(7);
        TableColumn<Person, String> cinColumn = (TableColumn<Person, String>) personTable.getColumns().get(8);
        TableColumn<Person, Void> actions1Column = (TableColumn<Person, Void>) personTable.getColumns().get(9);
        TableColumn<Person, Void> actions2Column = (TableColumn<Person, Void>) personTable.getColumns().get(10);






        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        dateNaissColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        actions1Column.setCellFactory(column -> new TableCell<>() {

            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {

                    Person item = getTableView().getItems().get(getIndex());
                    Optional<ButtonType> option = showConfirmation("Delete","To confirm this action press on OK");
                    if (option.get() == null) {
                        showInfoAlert("Info","No Option selected");
                    } else if (option.get() == ButtonType.OK) {
                        showInfoAlert("Successeful delete", "The person had been deleted successfully");
                        // Logique pour supprimer l'élément
                        personService.remove(item);
                        System.out.println("Supprimer l'élément : " + item.getName());
                        personTable.getItems().clear();
                        personTable.getItems().addAll(personService.findAll());
                    } else if (option.get() == ButtonType.CANCEL) {
                        showInfoAlert("Info","Cancelled, The item is not deleted");
                    }

                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        actions2Column.setCellFactory(column -> new TableCell<>() {

            private final Button editButton = new Button("Modifier");

            {
                editButton.setOnAction(event -> {
                    Person item = getTableView().getItems().get(getIndex());
                    // Logique pour éditer l'élément
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateForm.fxml"));
                        Parent root = fxmlLoader.load();
                        UpdateFormController updateFormController = fxmlLoader.getController();

                        // Pass the selected person to the update form controller
                        updateFormController.setSelectedPerson(item);

                        Stage updateFormStage = new Stage();
                        updateFormStage.initModality(Modality.APPLICATION_MODAL);
                        updateFormStage.setTitle("Update Person");
                        updateFormStage.setScene(new Scene(root));
                        updateFormStage.showAndWait();

                        // Update the table data after closing the update form
                        personTable.getItems().clear();
                        personTable.getItems().addAll(personService.findAll());
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("**********************************************************************");
                        System.out.println(e);
                        System.out.println("**********************************************************************");
                    }



                    System.out.println("Modifier l'élément : " + item.getName());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });




        // Set the observable list as the table data
        personTable.getItems().addAll(personService.findAll());
    }
    @FXML
    public void openAddForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addform.fxml"));
            Parent root = fxmlLoader.load();
            AddFormController addFormController = fxmlLoader.getController();

            Stage addFormStage = new Stage();
            addFormStage.initModality(Modality.APPLICATION_MODAL);
            addFormStage.setTitle("Add Person");
            addFormStage.setScene(new Scene(root));
            addFormStage.showAndWait();


            // Update the table data after closing the add form

            personTable.getItems().clear();
            personTable.getItems().addAll(personService.findAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleImporttxt(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a text file to import");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(importButtontxt.getScene().getWindow());
        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                personTable.getItems().clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 9) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String gender = parts[3];
                        String address = parts[4];
                        String email = parts[5];
                        String phoneNumber = parts[6];
                        String date = parts[7];
                        Date dateNaiss = java.sql.Date.valueOf(date);
                        // Parse and convert dateNaiss from parts[7]
                        String cin = parts[8];

                        Person person = new Person(name, age, gender, address, email, phoneNumber, dateNaiss, cin);
                        personService.save(person);
                        personTable.getItems().clear();
                        personTable.getItems().addAll(personService.findAll());
                    }
                }
                showInfoAlert("Import Successful", "Data imported from file: " + selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                showErrorAlert("Import Error", "Error occurred while importing data from file: " + selectedFile.getName());
            }
        }
    }

    @FXML
    private void handleExporttxt(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to export");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showSaveDialog(exportButtontxt.getScene().getWindow());
        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                List<Person> persons = personTable.getItems();
                for (Person person : persons) {
                    String line = person.getId() + "," + person.getName() + "," + person.getAge() + "," +
                            person.getGender() + "," + person.getAddress() + "," + person.getEmail() + "," +
                            person.getPhoneNumber() + "," + person.getDateNaiss() + "," + person.getCin();
                    writer.write(line);
                    writer.newLine();

                }
                personTable.getItems().clear();
                personTable.getItems().addAll(personService.findAll());
                showInfoAlert("Export Successful", "Data exported to file: " + selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                showErrorAlert("Export Error", "Error occurred while exporting data to file: " + selectedFile.getName());
            }
        }
    }
    @FXML
    private void handleImportexl(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Excel file to import");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        File selectedFile = fileChooser.showOpenDialog(importButtonexl.getScene().getWindow());
        if (selectedFile != null) {
            try (Workbook workbook = WorkbookFactory.create(selectedFile)) {
                Sheet sheet = workbook.getSheetAt(0);

                List<Person> importedPersons = new ArrayList<>();
                for (Row row : sheet) {
                    String id = getStringValue(row.getCell(0));
                    String name = getStringValue(row.getCell(1));
                    double ageValue = Double.parseDouble(getStringValue(row.getCell(2)));
                    int age = (int) ageValue;
                    String gender = getStringValue(row.getCell(3));
                    String address = getStringValue(row.getCell(4));
                    String email = getStringValue(row.getCell(5));
                    String phoneNumber = getStringValue(row.getCell(6));
                    Date dateNaiss = row.getCell(7).getDateCellValue();
                    String cin = getStringValue(row.getCell(8));

                    Person person = new Person(name,age, gender, address, email, phoneNumber, dateNaiss, cin);
                    personService.save(person);

                    personTable.getItems().clear();
                    personTable.getItems().addAll(personService.findAll());
                }

                // Use the importedPersons list as needed (e.g., update UI or save to a database)

                showInfoAlert("Import Successful", "Data imported from file: " + selectedFile.getName());
            } catch (IOException e) {
                System.out.println("**************************************************");
                e.printStackTrace();
                System.out.println("**************************************************");

                showErrorAlert("Import Error", "Error occurred while importing data from file: " + selectedFile.getName());
            }
        }
    }


    @FXML
    private void handleExportexl(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to export");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        File selectedFile = fileChooser.showSaveDialog(exportButtonexl.getScene().getWindow());
        if (selectedFile != null) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Person Data");

                List<Person> persons = personTable.getItems();
                int rowNum = 0;
                for (Person person : persons) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(person.getId());
                    row.createCell(1).setCellValue(person.getName());
                    row.createCell(2).setCellValue(person.getAge());
                    row.createCell(3).setCellValue(person.getGender());
                    row.createCell(4).setCellValue(person.getAddress());
                    row.createCell(5).setCellValue(person.getEmail());
                    row.createCell(6).setCellValue(person.getPhoneNumber());
                    row.createCell(7).setCellValue(person.getDateNaiss());
                    row.createCell(8).setCellValue(person.getCin());
                }

                try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
                    workbook.write(outputStream);
                    personTable.getItems().clear();
                    personTable.getItems().addAll(personService.findAll());
                }

                showInfoAlert("Export Successful", "Data exported to file: " + selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                showErrorAlert("Export Error", "Error occurred while exporting data to file: " + selectedFile.getName());
            }
        }
    }



    private String getStringValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            // Handle other cell types as needed
            default:
                return "";
        }
    }
//    private String getStringValue(Cell cell) {
//        if (cell == null) {
//            return "";
//        }
//        cell.setCellType(CellType.STRING);
//        return cell.getStringCellValue();
//    }
    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmation(String title,String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Are you sure you wanna delete this iteam");
        alert.setContentText(message);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        return option;
    }


}
