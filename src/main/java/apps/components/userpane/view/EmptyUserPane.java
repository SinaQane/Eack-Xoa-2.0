package apps.components.userpane.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class EmptyUserPane
{
    private Pane emptyUserPane;
    private final FXMLLoader loader;

    public EmptyUserPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../graphic/components/userpane/EmptyUserPane.fxml")));
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