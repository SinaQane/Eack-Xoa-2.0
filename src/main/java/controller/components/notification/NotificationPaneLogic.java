package controller.components.notification;

import controller.mainpage.MainPageController;
import db.UserDB;
import model.Notification;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotificationPaneLogic
{
    private static final Logger logger = LogManager.getLogger(NotificationPaneLogic.class);

    private final long otherUser;

    public NotificationPaneLogic(long otherUser)
    {
        this.otherUser = otherUser;
    }

    public void accept()
    {
        User ourUser = MainPageController.getMainPageController().getUser();
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
        User ourUser = MainPageController.getMainPageController().getUser();
        User otherUser = UserDB.getUserDB().get(this.otherUser);

        ourUser.getProfile().removeFromRequests(otherUser);
        otherUser.getProfile().removeFromPending(ourUser);

        logger.debug(ourUser.getId() + " rejected " + otherUser.getId() + "'s follow request.");
        UserDB.getUserDB().save(ourUser);
        UserDB.getUserDB().save(otherUser);
    }

    public void badReject()
    {
        User ourUser = MainPageController.getMainPageController().getUser();
        User otherUser = UserDB.getUserDB().get(this.otherUser);

        ourUser.getProfile().removeFromRequests(otherUser);
        otherUser.getProfile().removeFromPending(ourUser);
        otherUser.getProfile().addToNotifications(new Notification(otherUser.getId(), ourUser.getUsername() + " rejected your follow request."));

        logger.debug(ourUser.getId() + " rejected " + otherUser.getId() + "'s follow request.");
        UserDB.getUserDB().save(ourUser);
        UserDB.getUserDB().save(otherUser);
    }
}
