package apps.mainpage.pages.profile.event;

import java.util.EventObject;

public class ProfileEvent extends EventObject
{
    private TweetFormEvent tweetEvent = null;

    public ProfileEvent(Object source)
    {
        super(source);
    }

    public ProfileEvent(Object source, TweetFormEvent tweetEvent)
    {
        super(source);
        this.tweetEvent = tweetEvent;
    }

    public TweetFormEvent getTweetEvent()
    {
        return tweetEvent;
    }
}
