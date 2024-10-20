module application.pulselytics {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires jdk.compiler;

    opens application.pulselytics to javafx.fxml;
    exports application.pulselytics;
    exports application.pulselytics.controller;
    opens application.pulselytics.controller to javafx.fxml;
}