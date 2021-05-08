package apps.mainpage.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class MainPage
{
    static MainPage mainPage;

    private final Scene scene;
    private final FXMLLoader loader;

    private MainPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../graphic/mainpage/MainPage.fxml")));
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

    public static MainPage getMainPage()
    {
        if (mainPage == null)
        {
            mainPage = new MainPage();
        }
        return mainPage;
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
