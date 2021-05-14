package apps.mainpage.pages.profile_viewuser.event;

import models.User;

public class ProfileForm
{
    private final User user;
    private final int page;

    public ProfileForm(User user, int page)
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
