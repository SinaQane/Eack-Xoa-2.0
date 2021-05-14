package view.mainpage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class MainPage
{
    private static final String MAIN_PAGE = Config.getConfig("paths").getProperty(String.class, "mainPage");

    static MainPage mainPage;

    private final Scene scene;
    private final FXMLLoader loader;

    private MainPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(MAIN_PAGE)));
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
