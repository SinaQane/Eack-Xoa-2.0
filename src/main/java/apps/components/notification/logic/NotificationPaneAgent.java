package apps.components.notification.logic;

import apps.mainpage.logic.MainPageAgent;
import db.UserDB;
import models.Notification;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotificationPaneAgent
{
    private final Logger logger = LogManager.getLogger(NotificationPaneAgent.class);

    private final long otherUser;

    public NotificationPaneAgent(long otherUser)
    {
        this.otherUser = otherUser;
    }

    public void accept()
    {
        User ourUser = MainPageAgent.getMainPageAgent().getUser();
        User otherUser = UserDB.getUserDB().get(this.otherUser);

        ourUser.getProfile().removeFromRequests(otherUser);
        ourUser.getProfile().addToFollowers(otherUser);
        otherUser.getProfile().removeFromPending(ourUser);
        otherUser.getProfile().addToFollowings(ourUser);

        logger.debug(ourUser.getId() + " accepted " + otherUser.getId() + "'s follow request.");
        UserDB.getUserDB().save(ourUser);
        UserDB.getUserDB().save(otherUser);
    }

    public void goodReject()
    {
        User ourUser = MainPageAgent.getMainPageAgent().getUser();
        User otherUser = UserDB.getUserDB().get(this.otherUser);

        ourUser.getProfile().removeFromRequests(otherUser);
        otherUser.getProfile().removeFromPending(ourUser);

        logger.debug(ourUser.getId() + " rejected " + otherUser.getId() + "'s follow request.");
        UserDB.getUserDB().save(ourUser);
        UserDB.getUserDB().save(otherUser);
    }

    public void badReject()
    {
        User ourUser = MainPageAgent.getMainPageAgent().getUser();
        User otherUser = UserDB.getUserDB().get(this.otherUser);

        ourUser.getProfile().removeFromRequests(otherUser);
        otherUser.getProfile().removeFromPending(ourUser);
        otherUser.getProfile().addToNotifications(new Notification(otherUser.getId(), ourUser.getUsername() + " rejected your follow request."));

        logger.debug(ourUser.getId() + " rejected " + otherUser.getId() + "'s follow request.");
        UserDB.getUserDB().save(ourUser);
        UserDB.getUserDB().save(otherUser);
    }
}
