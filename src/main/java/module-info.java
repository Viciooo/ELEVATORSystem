module frontend.liftsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens frontend to javafx.fxml;
    exports frontend;
}