package apps.components.userpane.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class UserPane
{
    private Pane userPane;
    private final FXMLLoader loader;

    public UserPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../graphic/components/userpane/UserPane.fxml")));
        try
        {
            userPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getUserPane()
    {
        return this.userPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}
