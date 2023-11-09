module com.example.elevatorsimulator3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.elevatorsimulator3 to javafx.fxml;
    exports com.example.elevatorsimulator3;
}