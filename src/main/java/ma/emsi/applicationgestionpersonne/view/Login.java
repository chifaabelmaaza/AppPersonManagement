package ma.emsi.applicationgestionpersonne.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
//        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 580, 400);
        stage.setTitle("Class Management!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        PersonService personService = new PersonService();
//        personService.save(new Person(1,"Aziz",40,"Male","Casablanca","aziz@gmail.com","0600304958",new Date(11/02/1989),"B345678"));
//        List<Person> personsdata= personService.findAll();
//        System.out.println( "This is our client!" );
//        personsdata.forEach(System.out::println);
        launch();
    }
}