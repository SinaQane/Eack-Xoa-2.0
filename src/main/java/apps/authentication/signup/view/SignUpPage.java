package apps.authentication.signup.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class SignUpPage
{
    static SignUpPage signUpPage;

    private static final String SIGNUP_PAGE = "../../../../graphic/authentication/signup/SignUpPage.fxml"; // TODO config

    private final Scene scene;
    private final FXMLLoader loader;

    private SignUpPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SIGNUP_PAGE)));
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

    public static SignUpPage getSignUpPage()
    {
        if (signUpPage == null)
        {
            signUpPage = new SignUpPage();
        }
        return signUpPage;
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
