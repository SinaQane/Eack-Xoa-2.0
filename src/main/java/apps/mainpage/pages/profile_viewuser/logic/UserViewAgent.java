package apps.mainpage.pages.profile_viewuser.logic;

import db.UserDB;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserViewAgent
{
    private final Logger logger = LogManager.getLogger(UserViewAgent.class);

    private final User ourUser;
    private final User otherUser;

    public UserViewAgent(User ourUser, User otherUser)
    {
        this.ourUser = ourUser;
        this.otherUser = otherUser;
    }

    public void changeStatus()
    {
        if (ourUser.getProfile().getFollowings().contains(otherUser.getId()))
        {
            ourUser.getProfile().removeFromFollowings(otherUser);
            otherUser.getProfile().removeFromFollowers(ourUser);
            logger.info(ourUser.getId() + " unfollowed " + otherUser.getId());
        }
        else
        {
            if (otherUser.getProfile().isPrivate())
            {
                if (ourUser.getProfile().getPending().contains(otherUser.getId()))
                {
                    ourUser.getProfile().removeFromPending(otherUser);
                    otherUser.getProfile().removeFromRequests(ourUser);
                    logger.info(ourUser.getId() + " removed their follow request to " + otherUser.getId());
                }
                else
                {
                    ourUser.getProfile().addToPending(otherUser);
                    otherUser.getProfile().addToRequests(ourUser);
                    logger.info(ourUser.getId() + " sent a follow request to " + otherUser.getId());
                }
            }
            else
            {
                ourUser.getProfile().addToFollowings(otherUser);
                otherUser.getProfile().addToFollowers(ourUser);
                logger.info(ourUser.getId() + " followed " + otherUser.getId());
            }
        }
        UserDB.getUserDB().save(ourUser);
        UserDB.getUserDB().save(otherUser);
    }

    public void block()
    {
        if (ourUser.getProfile().getBlocked().contains(otherUser.getId()))
        {
            ourUser.getProfile().removeFromBlocked(otherUser);
            logger.warn(ourUser.getId() + " unblocked " + otherUser.getId());
        }
        else
        {
            ourUser.getProfile().removeFromFollowings(otherUser);
            ourUser.getProfile().removeFromFollowers(otherUser);
            otherUser.getProfile().removeFromFollowings(ourUser);
            ourUser.getProfile().addToBlocked(otherUser);
            logger.warn(ourUser.getId() + " blocked " + otherUser.getId());
        }
        UserDB.getUserDB().save(ourUser);
    }

    public void mute()
    {
        if (ourUser.getProfile().getMuted().contains(otherUser.getId()))
        {
            ourUser.getProfile().removeFromMuted(otherUser);
            logger.warn(ourUser.getId() + " unmuted " + otherUser.getId());
        }
        else
        {
            ourUser.getProfile().addToMuted(otherUser);
            logger.warn(ourUser.getId() + " muted " + otherUser.getId());
        }
        UserDB.getUserDB().save(ourUser);
    }
}
