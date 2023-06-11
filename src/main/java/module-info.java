module ma.emsi.applicationgestionpersonne {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.apache.poi.ooxml;
    requires java.sql;
    requires org.apache.poi.poi;

    opens ma.emsi.applicationgestionpersonne to javafx.fxml;
    opens ma.emsi.applicationgestionpersonne.dao to javafx.base;
    opens ma.emsi.applicationgestionpersonne.entities to javafx.base;
    opens ma.emsi.applicationgestionpersonne.service to javafx.base;
    opens ma.emsi.applicationgestionpersonne.view to javafx.fxml, javafx.base;


    exports ma.emsi.applicationgestionpersonne;
    exports ma.emsi.applicationgestionpersonne.dao.impl;
    exports ma.emsi.applicationgestionpersonne.service;
    exports ma.emsi.applicationgestionpersonne.entities;
    exports ma.emsi.applicationgestionpersonne.view;
}