package controller.frames.sharetweet;

import controller.mainpage.MainPageController;
import db.ChatDB;
import db.UserDB;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ArrayListUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShareTweetFrameLogic
{
    private static final Logger logger = LogManager.getLogger(ShareTweetFrameLogic.class);

    public void shareTweet(Tweet tweet, String usernames, String groups)
    {
        User ourUser = MainPageController.getMainPageController().getUser();

        String[] usernamesList = usernames.split(" ");
        String[] groupsList = groups.split(" ");
        ArrayList<Long> destinations = new ArrayList<>();

        for (String destination : groupsList)
        {
            if (ourUser.getProfile().getGroup(destination) != null)
            {
                Group group = ourUser.getProfile().getGroup(destination);
                List<Long> membersId = group.getMembersId();
                destinations = ArrayListUtil.arrayListsUnion(destinations, membersId);
            }
        }

        for (String destination : usernamesList)
        {
            if (UserDB.getUserDB().exists(destination))
            {
                User user = UserDB.getUserDB().get(destination);
                List<Long> userId = new LinkedList<>();
                userId.add(user.getId());
                destinations = ArrayListUtil.arrayListsUnion(destinations, userId);
            }
        }

        for (long id : destinations)
        {
            Chat pv = ChatDB.getChatDB().getPv(ourUser, id);
            new Message(pv, ourUser, tweet);
            ChatDB.getChatDB().save(pv);
        }

        logger.debug(tweet.getId() + " was shared with several users by " + ourUser.getId());
    }
}
