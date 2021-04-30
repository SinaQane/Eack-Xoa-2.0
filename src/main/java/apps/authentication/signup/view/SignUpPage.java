package apps.authentication.signup.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class SignUpPage
{
    private final Scene scene;
    private final FXMLLoader loader;

    public SignUpPage() throws IOException
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../fxml/authentication/signup/SignUpPage.fxml")));
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
