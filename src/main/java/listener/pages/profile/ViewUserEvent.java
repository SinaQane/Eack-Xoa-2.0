package listener.pages.profile;

import model.User;

import java.util.EventObject;

public class ViewUserEvent extends EventObject
{
    private final User ourUser;
    private final User otherUser;

    public ViewUserEvent(Object source, User ourUser, User otherUser)
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
