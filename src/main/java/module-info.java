module com.example.salpagarov_rustam_201_hw5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.example.salpagarov_rustam_201_hw5 to javafx.fxml;
    exports com.example.salpagarov_rustam_201_hw5;
    exports server;
    opens server to javafx.fxml;
    exports server.fugure;
    opens server.fugure to javafx.fxml;
    exports api;
    opens api to javafx.fxml;
    exports com.example.salpagarov_rustam_201_hw5.navigation;
    opens com.example.salpagarov_rustam_201_hw5.navigation to javafx.fxml;
    exports server.db;
    opens server.db to javafx.fxml, org.hibernate.orm.core;
}