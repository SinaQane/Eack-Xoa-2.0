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

    private Pane messagesPane;
    private final FXMLLoader loader;

    public MessagesPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(MESSAGES)));
        try
        {
            messagesPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.messagesPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh()
    {
        MessagesPaneFXML messagesController = this.loader.getController();

        ChatsListPane chatsListPane = new ChatsListPane();
        chatsListPane.refresh(0);

        messagesController.setChatsListPane(chatsListPane.getListPane());
        messagesController.setChatroomPane(new EmptyChatroomPane().getPane());
    }
}
