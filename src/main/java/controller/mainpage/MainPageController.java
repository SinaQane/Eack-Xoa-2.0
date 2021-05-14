package controller.mainpage;

import db.UserDB;
import model.Tweet;
import model.User;

public class MainPageController
{
    static MainPageController mainPageController;

    private User user;

    private MainPageController()
    {
        this.user = null;
    }

    public static MainPageController getMainPageAgent()
    {
        if (mainPageController == null)
        {
            mainPageController = new MainPageController();
        }
        return mainPageController;
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
