package view.pages.empty;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class EmptyChatroomPane
{
    private static final String CHATROOM = Config.getConfig("paths").getProperty(String.class, "emptyChatroom");

    private Pane chatroomPane;

    public EmptyChatroomPane()
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(CHATROOM)));
        try
        {
            chatroomPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.chatroomPane;
    }
}
