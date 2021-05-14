package view.signup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class SignUpPage
{
    static SignUpPage signUpPage;

    private static final String SIGNUP_PAGE = Config.getConfig("paths").getProperty(String.class, "signUp");

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
        SignUpPageFXML signUpPageFXML = this.loader.getController();
        signUpPageFXML.getMessageText().setVisible(false);
    }
}
