package apps.firstpage.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class FirstPage
{
    private static final String FIRST_PAGE = Config.getConfig("paths").getProperty(String.class, "firstPage");

    private final Scene scene;
    private final FXMLLoader loader;

    public FirstPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(FIRST_PAGE)));
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
}
