package apps.authentication.signup.view;

import apps.authentication.login.view.LoginFXML;
import apps.mainpage.view.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class SignUpPage
{
    private final Scene scene;
    private final FXMLLoader loader;

    public SignUpPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../graphic/authentication/signup/SignUpPage.fxml")));
        Parent root = null;
        try
        {
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        assert root != null;
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

    public void clear()
    {
        SignUpFXML fxmlController = this.loader.getController();
        fxmlController.getMessageText().setVisible(false);
    }
}
