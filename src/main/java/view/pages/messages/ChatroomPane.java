package view.pages.messages;

import view.components.empty.emptymessage.EmptyMessagePane;
import view.components.message.MessagePane;
import view.components.message.MessagePaneFXML;
import controller.pages.messages.MessagesLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Chat;
import model.Message;
import config.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChatroomPane
{
    private static final String CHATROOM = Config.getConfig("paths").getProperty(String.class, "chatroom");

    private Pane pane;
    private final FXMLLoader loader;

    public ChatroomPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(CHATROOM)));
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


    public void refresh(Chat chatroom, int page)
    {
        ChatroomPaneFXML chatroomPaneFXML = this.loader.getController();
        chatroomPaneFXML.setChat(chatroom);
        chatroomPaneFXML.setPage(page);

        MessagesLogic logic = new MessagesLogic();
        List<Message> messages = logic.getChatroomPage(chatroom, page);

        for (int i = 0; i < 5; i++)
        {
            if (messages.get(i) == null)
            {
                chatroomPaneFXML.setMessagePane(i, new EmptyMessagePane().getMessagePane());
            }
            else
            {
                MessagePane messagePane = new MessagePane();
                ((MessagePaneFXML) messagePane.getLoader().getController()).setData(messages.get(i));
                chatroomPaneFXML.setMessagePane(i, messagePane.getPane());
            }
        }

        chatroomPaneFXML.getPreviousButton().setDisable(!logic.chatroomHasPreviousPage(chatroom, page));
        chatroomPaneFXML.getNextButton().setDisable(!logic.chatroomHasNextPage(chatroom, page));
        chatroomPaneFXML.getAddMemberButton().setVisible(chatroom.isGroup());
    }
}
