package listener.frames.newchat;

import controller.mainpage.MainPageController;
import controller.mainpage.PanesController;
import db.UserDB;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import controller.frames.newchat.NewChatFrameLogic;
import javafx.scene.control.Button;
import model.User;

import java.util.EventObject;

public class NewChatFrameListener
{
    public void eventOccurred(EventObject eventObject, String groupName, String username)
    {
        NewChatFrameLogic newChatFrameLogic = new NewChatFrameLogic();

        User user = UserDB.getUserDB().get(MainPageController.getMainPageController().getUser().getId());

        if (((Button) eventObject.getSource()).getId().equals("doneButton"))
        {
            if (groupName.equals(""))
            {
                newChatFrameLogic.newPrivateChat(user, username);
            }
            else if (username.equals(""))
            {
                newChatFrameLogic.newGroup(user, groupName);
            }
            MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();
            mainPageController.setMainPane(PanesController.getPanesController().getMessagesPane().getPane());
        }
    }
}
