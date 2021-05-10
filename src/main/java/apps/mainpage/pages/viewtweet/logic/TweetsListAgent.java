package apps.mainpage.pages.viewtweet.logic;

import models.Tweet;

import java.util.LinkedList;
import java.util.List;

public class TweetsListAgent
{
    private final Tweet tweet;

    public TweetsListAgent(Tweet tweet)
    {
        this.tweet = tweet;
    }

    public List<List<String>> getTweets()
    {
        List<List<String>> result = new LinkedList<>();
        List<String> tweets = tweet.getComments();

        if (tweets.size() == 0)
        {
            List<String> temp = new LinkedList<>();
            temp.add("null");
            temp.add("null");
            result.add(temp);
        }

        for (int i = 0; i < tweets.size(); i = i+2)
        {
            List<String> temp = new LinkedList<>();
            temp.add(tweets.get(i));

            if (i + 1 < tweets.size())
            {
                temp.add(tweets.get(i + 1));
            }
            else
            {
                temp.add("null");
            }
            result.add(temp);
        }
        return result;
    }

    public List<String> getPage(int page)
    {
        if (this.getTweets() == null)
        {
            return null;
        }
        if (page < 0 || page > this.getNumberOfPages())
        {
            return null;
        }

        return this.getTweets().get(page);
    }

    public int getNumberOfPages()
    {
        if (this.getTweets() == null)
        {
            return 0;
        }
        return this.getTweets().size();
    }

    public boolean hasNextPage(int page)
    {
        if (this.getTweets() == null)
        {
            return false;
        }
        return page != this.getNumberOfPages() - 1;
    }

    public boolean hasPreviousPage(int page)
    {
        if (this.getTweets() == null)
        {
            return false;
        }
        return page != 0;
    }
}
