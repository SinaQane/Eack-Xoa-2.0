package view.frames.editmessage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Message;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class EditMessageFrame
{
    private static final String EDIT_MESSAGE = Config.getConfig("paths").getProperty(String.class, "editMessageFrame");

    public EditMessageFrame(Message message)
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(EDIT_MESSAGE)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Message");
            stage.setScene(new Scene(root));
            stage.show();
            ((EditMessageFrameFXML) loader.getController()).setMessage(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
