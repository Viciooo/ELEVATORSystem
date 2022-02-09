package frontend;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {
    private static Stage stg;

    public void loadSceneFromFXML(String title,String pathToFXML,boolean resizable) throws IOException {
        Parent root = new FXMLLoader().load(new FileInputStream("src/main/resources/"+pathToFXML));
        Scene scene = new Scene(root);
        stg.setTitle(title);
        stg.setResizable(resizable);
        stg.setScene(scene);
        stg.show();
    }

    public void lunchMain() throws IOException {
        loadSceneFromFXML("Main","Main.fxml",false);
        stg.setMaximized(false);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stg.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        lunchMain();
    }

    public static void main(String[] args) {
        launch();
    }
}