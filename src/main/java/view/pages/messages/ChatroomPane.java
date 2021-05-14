package view.pages.messages;

import view.components.empty.emptymessage.EmptyMessagePane;
import view.components.message.MessagePane;
import view.components.message.MessagePaneFXML;
import controller.pages.messages.MessagesLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Chat;
import model.Message;
import util.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChatroomPane
{
    private static final String CHATROOM = Config.getConfig("paths").getProperty(String.class, "chatroom");

    private Pane chatroomPane;
    private final FXMLLoader loader;

    public ChatroomPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(CHATROOM)));
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

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(Chat chatroom, int page)
    {
        ChatroomPaneFXML fxmlController = this.loader.getController();
        fxmlController.setChat(chatroom);
        fxmlController.setPage(page);

        MessagesLogic messagesLogic = new MessagesLogic();
        List<Message> messages = messagesLogic.getChatroomPage(chatroom, page);

        for (int i = 0; i < 5; i++)
        {
            if (messages.get(i) == null)
            {
                fxmlController.setMessagePane(i, new EmptyMessagePane().getMessagePane());
            }
            else
            {
                MessagePane messagePane = new MessagePane();
                ((MessagePaneFXML) messagePane.getLoader().getController()).setData(messages.get(i));
                fxmlController.setMessagePane(i, messagePane.getMessagePane());
            }
        }

        fxmlController.getPreviousButton().setDisable(!messagesLogic.chatroomHasPreviousPage(chatroom, page));
        fxmlController.getNextButton().setDisable(!messagesLogic.chatroomHasNextPage(chatroom, page));
        fxmlController.getAddMemberButton().setVisible(chatroom.isGroup());
    }
}
