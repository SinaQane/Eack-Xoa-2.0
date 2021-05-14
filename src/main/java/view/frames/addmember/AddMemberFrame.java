package view.frames.addmember;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Chat;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class AddMemberFrame
{
    private static final String ADD_MEMBER = Config.getConfig("paths").getProperty(String.class, "addMemberFrame");

    public AddMemberFrame(Chat chat)
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(ADD_MEMBER)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Member");
            stage.setScene(new Scene(root));
            stage.show();
            ((AddMemberFrameFXML) loader.getController()).setChat(chat);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
