package apps.mainpage.pages.userslist_notifications.event;

import models.User;

public class PageFormEvent
{
    private final User user;
    private final int page;
    private final String pageKind;

    public PageFormEvent(User user, int page, String pageKind)
    {
        this.user = user;
        this.page = page;
        this.pageKind = pageKind;
    }

    public User getUser()
    {
        return user;
    }

    public int getPage()
    {
        return this.page;
    }

    public String getPageKind()
    {
        return pageKind;
    }
}
