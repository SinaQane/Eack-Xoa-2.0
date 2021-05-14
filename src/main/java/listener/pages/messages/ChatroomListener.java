package listener.pages.messages;

import view.pages.messages.ChatroomPane;
import view.pages.messages.ChatsListPane;
import view.frames.addmember.AddMemberFrame;
import controller.mainpage.MainPageController;
import controller.mainpage.PanesController;
import view.pages.messages.MessagesPane;
import view.pages.messages.MessagesPaneFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import db.ChatDB;
import javafx.scene.control.Button;
import model.Chat;
import model.Message;

import java.util.EventObject;

public class ChatroomListener
{
    public void eventOccurred(EventObject eventObject, Chat chat, int page, String message, String picture)
    {
        MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();

        MessagesPane messagesPane = PanesController.getPanesController().getMessagesPane();
        MessagesPaneFXML messagesController = messagesPane.getLoader().getController();
        ChatsListPane chatsListPane = new ChatsListPane();
        chatsListPane.refresh(0);

        ChatroomPane chatroomPane = new ChatroomPane();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "addMemberButton":
                new AddMemberFrame(chat);
                break;
            case "previousButton":
                chatroomPane.refresh(chat, page-1);

                messagesController.setChatsListPane(chatsListPane.getListPane());
                messagesController.setChatroomPane(chatroomPane.getPane());
                mainPageController.setMainPane(messagesPane.getPane());
                break;
            case "nextButton":
                chatroomPane.refresh(chat, page+1);

                messagesController.setChatsListPane(chatsListPane.getListPane());
                messagesController.setChatroomPane(chatroomPane.getPane());
                mainPageController.setMainPane(messagesPane.getPane());
                break;
            case "sendButton":
                new Message(chat, MainPageController.getMainPageAgent().getUser(), message, picture);

                ChatDB.getChatDB().save(chat);

                chatroomPane.refresh(chat, 0);

                messagesController.setChatsListPane(chatsListPane.getListPane());
                messagesController.setChatroomPane(chatroomPane.getPane());
                mainPageController.setMainPane(messagesPane.getPane());
                break;
        }
    }
}
