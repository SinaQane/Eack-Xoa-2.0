package listener.frames.sharetweet;

import controller.frames.sharetweet.ShareTweetFrameLogic;
import javafx.scene.control.Button;
import model.Tweet;

import java.util.EventObject;

public class ShareTweetFrameListener
{
    public void eventOccurred(EventObject eventObject, Tweet tweet, String usernames, String groups)
    {
        if (((Button) eventObject.getSource()).getId().equals("sendButton"))
        {
            ShareTweetFrameLogic logic = new ShareTweetFrameLogic();
            logic.shareTweet(tweet, usernames, groups);
        }
    }
}
