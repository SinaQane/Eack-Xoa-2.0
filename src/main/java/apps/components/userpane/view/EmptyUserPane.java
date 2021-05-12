package apps.components.userpane.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class EmptyUserPane
{
    private static final String EMPTY_USER = "../../../../graphic/components/userpane/EmptyUserPane.fxml"; // TODO config

    private Pane emptyUserPane;
    private final FXMLLoader loader;

    public EmptyUserPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(EMPTY_USER)));
        try
        {
            emptyUserPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getUserPane()
    {
        return this.emptyUserPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }
}