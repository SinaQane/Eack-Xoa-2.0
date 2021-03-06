package listener.pages.tweetslist;

import model.Tweet;

public class TweetsListForm
{
    private final Tweet tweet;
    private final int page;

    public TweetsListForm(Tweet tweet, int page)
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
