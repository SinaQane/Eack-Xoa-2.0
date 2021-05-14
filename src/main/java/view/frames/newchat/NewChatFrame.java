package view.frames.newchat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class NewChatFrame
{
    private static final String NEW_CHAT = Config.getConfig("paths").getProperty(String.class, "newChatFrame");

    public NewChatFrame()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(NEW_CHAT)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("New Chat");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
