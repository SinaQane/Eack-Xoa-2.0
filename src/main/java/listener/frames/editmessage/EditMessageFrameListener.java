package listener.frames.editmessage;

import db.ChatDB;
import javafx.scene.control.Button;
import model.Chat;
import model.Message;

import java.util.EventObject;

public class EditMessageFrameListener
{
    public void eventOccurred(EventObject eventObject, Message message, String editedMessage)
    {
        if (((Button) eventObject.getSource()).getId().equals("doneButton"))
        {
            Chat chat = ChatDB.getChatDB().get(message.getChatId());
            chat.getMessages().get(message.getIndex()).edit(editedMessage);
            ChatDB.getChatDB().save(chat);
        }
    }
}
