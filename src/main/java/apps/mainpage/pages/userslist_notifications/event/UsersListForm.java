package apps.mainpage.pages.userslist_notifications.event;

import models.User;

public class UsersListForm
{
    private final User user;
    private final int page;
    private final String pageKind;

    public UsersListForm(User user, int page, String pageKind)
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
