package controller.mainpage;

import db.UserDB;
import model.Tweet;
import model.User;
import util.TimeTask;

import java.util.Date;

public class MainPageController
{
    static MainPageController mainPageController;

    private User user;

    private MainPageController()
    {
        this.user = null;
    }

    public static MainPageController getMainPageController()
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

    public void startTimer()
    {
        TimeTask timeTask = new TimeTask(10, () ->
        {
            if (user != null)
            {
                user.getProfile().setLastSeen(new Date());
            }
        });
        timeTask.start();
    }
}
