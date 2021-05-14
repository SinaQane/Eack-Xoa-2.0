package view.pages.messages;

import controller.pages.messages.MessagesLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Chat;
import config.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChatsListPane
{
    private static final String CHATS_LIST = Config.getConfig("paths").getProperty(String.class, "chatsList");

    private Pane pane;
    private final FXMLLoader loader;

    public ChatsListPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(CHATS_LIST)));
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

    public void refresh(int page)
    {
        ChatsListPaneFXML chatsListPaneFXML = this.loader.getController();
        chatsListPaneFXML.setPage(page);

        MessagesLogic logic = new MessagesLogic();
        List<Chat> chats = logic.getChatsListPage(page);

        for (int i = 0; i < 7; i++)
        {
            if (chats.get(i) == null)
            {
                chatsListPaneFXML.getButton(i).setVisible(false);
            }
            else
            {
                chatsListPaneFXML.setButton(i, chats.get(i));
            }
        }

        chatsListPaneFXML.getPreviousButton().setDisable(!logic.chatsListHasPreviousPage(page));
        chatsListPaneFXML.getNextButton().setDisable(!logic.chatsListHasNextPage(page));
    }
}
