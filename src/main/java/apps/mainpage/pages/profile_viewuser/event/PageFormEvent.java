package apps.mainpage.pages.profile_viewuser.event;

import models.User;

public class PageFormEvent
{
    private final User user;
    private final int page;

    public PageFormEvent(User user, int page)
    {
        this.user = user;
        this.page = page;
    }

    public User getUser()
    {
        return user;
    }

    public int getPage()
    {
        return this.page;
    }
}
