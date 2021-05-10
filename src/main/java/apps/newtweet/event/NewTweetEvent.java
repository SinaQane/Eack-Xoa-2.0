package apps.newtweet.event;

import java.util.EventObject;

public class NewTweetEvent extends EventObject
{
    private final NewTweetFormEvent tweetFormEvent;

    public NewTweetEvent(Object source, NewTweetFormEvent tweetFormEvent)
    {
        super(source);
        this.tweetFormEvent = tweetFormEvent;
    }

    public NewTweetFormEvent getTweetFormEvent()
    {
        return tweetFormEvent;
    }
}
