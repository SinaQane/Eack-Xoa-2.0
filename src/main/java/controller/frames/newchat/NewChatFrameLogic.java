package controller.frames.newchat;

import db.UserDB;
import model.Chat;
import model.User;

public class NewChatFrameLogic
{
    public void newPrivateChat(User ourUser, String username)
    {
        if (UserDB.getUserDB().exists(username))
        {
            User otherUser = UserDB.getUserDB().get(username);

            if (!otherUser.isDeactivated() && !otherUser.isDeleted() && !otherUser.getProfile().getBlocked().contains(otherUser.getId()) &&
                    (otherUser.getProfile().getFollowings().contains(ourUser.getId()) || otherUser.getProfile().getFollowers().contains(ourUser.getId())))
            {
                Chat chat = new Chat(ourUser, otherUser);
                ourUser.getProfile().addToChats(chat);
                otherUser.getProfile().addToChats(chat);
                UserDB.getUserDB().save(ourUser);
                UserDB.getUserDB().save(otherUser);
            }
        }
    }

    public void newGroup(User ourUser, String groupName)
    {
        Chat chat = new Chat(ourUser, groupName);
        ourUser.getProfile().addToChats(chat);
        UserDB.getUserDB().save(ourUser);
    }
}
