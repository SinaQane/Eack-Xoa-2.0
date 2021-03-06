package view.frames.managegroup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class ManageGroupFrame
{
    private static final String GROUP_FRAME = Config.getConfig("paths").getProperty(String.class, "manageGroupFrame");

    private final FXMLLoader loader;

    public ManageGroupFrame()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(GROUP_FRAME)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Manage Group");
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
