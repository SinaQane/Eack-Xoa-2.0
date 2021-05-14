package view.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class LoginPage
{
    static LoginPage loginPage;

    private static final String LOGIN_PAGE = Config.getConfig("paths").getProperty(String.class, "login");

    private final Scene scene;
    private final FXMLLoader loader;

    private LoginPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(LOGIN_PAGE)));
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

    public static LoginPage getLoginPage()
    {
        if (loginPage == null)
        {
            loginPage = new LoginPage();
        }
        return loginPage;
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
        LoginPageFXML loginPageFXML = this.loader.getController();
        loginPageFXML.getMessageText().setVisible(false);
    }
}
