package apps.mainpage.logic;

import db.UserDB;
import models.Tweet;
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
        return UserDB.getUserDB().get(user.getId());
    }

    public boolean isValid(Tweet tweet)
    {
        User owner = UserDB.getUserDB().get(tweet.getOwner());

        if (owner.isDeleted())
        {
            return false;
        }
        if (owner.isDeactivated())
        {
            return false;
        }
        if (!tweet.isVisible())
        {
            return false;
        }
        if (owner.getProfile().getBlocked().contains(user.getId()))
        {
            return false;
        }

        return (!owner.getProfile().isPrivate() || owner.getProfile().getFollowers().contains(user.getId()));
    }
}
