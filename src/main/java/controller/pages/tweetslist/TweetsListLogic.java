package controller.pages.tweetslist;

import controller.mainpage.MainPageController;
import db.TweetDB;
import model.Tweet;

import java.util.LinkedList;
import java.util.List;

public class TweetsListLogic
{
    private final Tweet tweet;

    public TweetsListLogic(Tweet tweet)
    {
        this.tweet = tweet;
    }

    public List<List<String>> getTweets()
    {
        List<List<String>> result = new LinkedList<>();
        List<String> comments = new LinkedList<>();
        List<String> tweets = tweet.getComments();

        for (String tweet : tweets)
        {
            if (MainPageController.getMainPageAgent().isValid(TweetDB.getTweetDB().get(tweet)))
            {
                comments.add(tweet);
            }
        }

        if (comments.size() == 0)
        {
            List<String> temp = new LinkedList<>();
            temp.add("null");
            temp.add("null");
            result.add(temp);
        }

        for (int i = 0; i < comments.size(); i = i+2)
        {
            List<String> temp = new LinkedList<>();
            temp.add(comments.get(i));

            if (i + 1 < comments.size())
            {
                temp.add(comments.get(i + 1));
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
