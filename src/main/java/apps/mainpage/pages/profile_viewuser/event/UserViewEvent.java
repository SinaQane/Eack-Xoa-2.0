package apps.mainpage.pages.profile_viewuser.event;

import models.User;

import java.util.EventObject;

public class UserViewEvent extends EventObject
{
    private final User ourUser;
    private final User otherUser;

    public UserViewEvent(Object source, User ourUser, User otherUser)
    {
        super(source);
        this.ourUser = ourUser;
        this.otherUser = otherUser;
    }

    public User getOurUser()
    {
        return this.ourUser;
    }

    public User getOtherUser()
    {
        return this.otherUser;
    }
}
