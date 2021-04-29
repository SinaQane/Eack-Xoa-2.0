package apps.authentication.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class LoginPage
{
    private final Scene scene;
    private final FXMLLoader loader;

    public LoginPage() throws IOException
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../apps/authentication/LoginPage.fxml")));
        Parent root = loader.load();
        this.scene = new Scene(root);
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
