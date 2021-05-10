package apps.mainpage.pages.userslist.event;

import java.util.EventObject;

public class UsersListEvent extends EventObject
{
    private final PageFormEvent pageEvent;

    public UsersListEvent(Object source, PageFormEvent pageEvent)
    {
        super(source);
        this.pageEvent = pageEvent;
    }

    public PageFormEvent getPageEvent()
    {
        return pageEvent;
    }
}
