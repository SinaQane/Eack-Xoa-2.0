package controller.frames.addmember;

import db.ChatDB;
import db.UserDB;
import model.Chat;
import model.User;

public class AddMemberFrameLogic
{
    public void addMember(Chat chat, String username)
    {
        if (UserDB.getUserDB().exists(username))
        {
            User user = UserDB.getUserDB().get(username);
            user.getProfile().addToChats(chat);
            chat.addToUsers(user);
            UserDB.getUserDB().save(user);
            ChatDB.getChatDB().save(chat);
        }
    }
}
