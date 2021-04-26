package apps.authentication.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FXMLController
{
    private final Scene scene;
    public FXMLController() throws IOException
    {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../../apps/authentication/scene.fxml")));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../../apps/authentication/styles.css")).toExternalForm());
        this.scene = scene;
    }

    public Scene getScene()
    {
        return this.scene;
    }
}
