package controller.frames.addmember;

import db.ChatDB;
import db.UserDB;
import model.Chat;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddMemberFrameLogic
{
    private static final Logger logger = LogManager.getLogger(AddMemberFrameLogic.class);

    public void addMember(Chat chat, String username)
    {
        if (UserDB.getUserDB().exists(username))
        {
            User user = UserDB.getUserDB().get(username);
            user.getProfile().addToChats(chat);
            chat.addToUsers(user);
            UserDB.getUserDB().save(user);
            ChatDB.getChatDB().save(chat);
            logger.debug(user.getId() + " was added to group " + chat.getId());
        }
    }
}
