module com.example.weeklyschedule {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens com.example.weeklyschedule to javafx.fxml;
    exports com.example.weeklyschedule;
}