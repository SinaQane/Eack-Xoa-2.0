package apps.mainpage.pages.viewtweet.event;

import models.Tweet;

public class PageFormEvent
{
    private final Tweet tweet;
    private final int page;

    public PageFormEvent(Tweet tweet, int page)
    {
        this.tweet = tweet;
        this.page = page;
    }

    public Tweet getTweet()
    {
        return this.tweet;
    }

    public int getPage()
    {
        return this.page;
    }
}
