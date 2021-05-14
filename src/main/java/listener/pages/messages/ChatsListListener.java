package listener.pages.messages;

import view.pages.messages.ChatsListPane;
import controller.mainpage.MainPageController;
import controller.mainpage.PanesController;
import view.pages.messages.ChatroomPane;
import view.pages.messages.MessagesPane;
import view.pages.messages.MessagesPaneFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import view.frames.newchat.NewChatFrame;
import javafx.scene.control.Button;
import model.Chat;
import model.Message;

import java.util.EventObject;

public class ChatsListListener
{
    public void eventOccurred(EventObject eventObject, Chat chat, int page)
    {
        MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();

        MessagesPane messagesPane = PanesController.getPanesController().getMessagesPane();
        MessagesPaneFXML messagesController = messagesPane.getLoader().getController();

        ChatsListPane chatsListPane = new ChatsListPane();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "newChatButton":
                new NewChatFrame();
                break;
            case "previousButton":
                chatsListPane.refresh(page-1);
                messagesController.setChatsListPane(chatsListPane.getListPane());
                mainPageController.setMainPane(messagesPane.getPane());
                break;
            case "nextButton":
                chatsListPane.refresh(page+1);
                messagesController.setChatsListPane(chatsListPane.getListPane());
                mainPageController.setMainPane(messagesPane.getPane());
                break;
            case "firstChatButton":
            case "secondChatButton":
            case "thirdChatButton":
            case "fourthChatButton":
            case "fifthChatButton":
            case "sixthChatButton":
            case "seventhChatButton":
                chatsListPane.refresh(page);

                ChatroomPane chatroomPane = new ChatroomPane();
                chatroomPane.refresh(chat, 0);

                for (Message message : chat.getMessages())
                {
                    message.addToSeen(MainPageController.getMainPageAgent().getUser());
                }

                messagesController.setChatsListPane(chatsListPane.getListPane());
                messagesController.setChatroomPane(chatroomPane.getPane());
                mainPageController.setMainPane(messagesPane.getPane());
                break;
        }
    }
}
