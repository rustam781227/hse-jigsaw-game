module com.example.salpagarov_rustam_201_hw5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.salpagarov_rustam_201_hw5 to javafx.fxml;
    exports com.example.salpagarov_rustam_201_hw5;
}