package view.pages.messages;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;
import view.pages.empty.EmptyChatroomPane;

import java.io.IOException;
import java.util.Objects;

public class MessagesPane
{
    private static final String MESSAGES = Config.getConfig("paths").getProperty(String.class, "messages");

    private Pane pane;
    private final FXMLLoader loader;

    public MessagesPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(MESSAGES)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.pane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh()
    {
        MessagesPaneFXML messagesPaneFXML = this.loader.getController();

        ChatsListPane chatsListPane = new ChatsListPane();
        chatsListPane.refresh(0);

        messagesPaneFXML.setChatsListPane(chatsListPane.getPane());
        messagesPaneFXML.setChatroomPane(new EmptyChatroomPane().getPane());
    }
}
