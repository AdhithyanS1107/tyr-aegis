module com.chrono.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.chrono.desktop to javafx.fxml;
    exports com.chrono.desktop;
}
