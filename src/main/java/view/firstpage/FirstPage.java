package view.firstpage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class FirstPage
{
    private final Scene scene;
    private final FXMLLoader loader;

    public FirstPage() throws IOException
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../FirstPage.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../FirstPage.css")).toExternalForm());
        this.scene = scene;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public Scene getScene()
    {
        return this.scene;
    }
}
