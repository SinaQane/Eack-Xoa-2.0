package apps.newtweet.event;

import java.util.EventObject;

public class NewTweetEvent extends EventObject
{
    private final NewTweetForm tweetForm;

    public NewTweetEvent(Object source, NewTweetForm tweetForm)
    {
        super(source);
        this.tweetForm = tweetForm;
    }

    public NewTweetForm getTweetForm()
    {
        return tweetForm;
    }
}
