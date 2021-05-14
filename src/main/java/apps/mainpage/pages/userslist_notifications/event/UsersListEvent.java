package apps.mainpage.pages.userslist_notifications.event;

import java.util.EventObject;

public class UsersListEvent extends EventObject
{
    private final UsersListForm usersListForm;

    public UsersListEvent(Object source, UsersListForm usersListForm)
    {
        super(source);
        this.usersListForm = usersListForm;
    }

    public UsersListForm getUsersListForm()
    {
        return usersListForm;
    }
}
