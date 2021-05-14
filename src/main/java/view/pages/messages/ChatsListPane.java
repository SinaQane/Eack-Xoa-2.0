package view.pages.messages;

import controller.pages.messages.MessagesLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Chat;
import util.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChatsListPane
{
    private static final String CHATS_LIST = Config.getConfig("paths").getProperty(String.class, "chatsList");

    private Pane chatsListPane;
    private final FXMLLoader loader;

    public ChatsListPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(CHATS_LIST)));
        try
        {
            chatsListPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getListPane()
    {
        return this.chatsListPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {
        ChatsListPaneFXML fxmlController = this.loader.getController();
        fxmlController.setPage(page);

        MessagesLogic messagesLogic = new MessagesLogic();
        List<Chat> chats = messagesLogic.getChatsListPage(page);

        for (int i = 0; i < 7; i++)
        {
            if (chats.get(i) == null)
            {
                fxmlController.getButton(i).setVisible(false);
            }
            else
            {
                fxmlController.setButton(i, chats.get(i));
            }
        }

        fxmlController.getPreviousButton().setDisable(!messagesLogic.chatsListHasPreviousPage(page));
        fxmlController.getNextButton().setDisable(!messagesLogic.chatsListHasNextPage(page));
    }
}
