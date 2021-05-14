package apps.groupframe.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class GroupFrame
{
    private static final String GROUP_FRAME = Config.getConfig("paths").getProperty(String.class, "groupFrame");

    private final FXMLLoader loader;

    public GroupFrame()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(GROUP_FRAME)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public FXMLLoader getLoader()
    {
        return loader;
    }
}
