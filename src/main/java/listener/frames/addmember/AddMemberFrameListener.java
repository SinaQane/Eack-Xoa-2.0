package listener.frames.addmember;

import controller.frames.addmember.AddMemberFrameLogic;
import javafx.scene.control.Button;
import model.Chat;

import java.util.EventObject;

public class AddMemberFrameListener
{
    public void eventOccurred(EventObject eventObject, Chat chat, String username)
    {
        if (((Button) eventObject.getSource()).getId().equals("addButton"))
        {
            AddMemberFrameLogic addMemberFrameLogic = new AddMemberFrameLogic();
            addMemberFrameLogic.addMember(chat, username);
        }
    }
}
