package apps.mainpage.pages.profile_viewuser.event;

import java.util.EventObject;

public class ProfileEvent extends EventObject
{
    private TweetFormEvent tweetEvent = null;
    private PageFormEvent pageEvent = null;

    public ProfileEvent(Object source)
    {
        super(source);
    }

    public ProfileEvent(Object source, TweetFormEvent tweetEvent)
    {
        super(source);
        this.tweetEvent = tweetEvent;
    }

    public ProfileEvent(Object source, PageFormEvent pageEvent)
    {
        super(source);
        this.pageEvent = pageEvent;
    }

    public TweetFormEvent getTweetEvent()
    {
        return tweetEvent;
    }

    public PageFormEvent getPageEvent()
    {
        return pageEvent;
    }
}
