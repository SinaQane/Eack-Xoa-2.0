package apps.mainpage.logic;

import db.UserDB;
import models.User;

public class MainPageAgent
{
    static MainPageAgent mainPageAgent;

    private User user;

    private MainPageAgent()
    {
        this.user = null;
    }

    public static MainPageAgent getMainPageAgent()
    {
        if (mainPageAgent == null)
        {
            mainPageAgent = new MainPageAgent();
        }
        return mainPageAgent;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return this.user;
    }

    public void refresh()
    {
        this.user = UserDB.getUserDB().get(user.getId());
    }

}
