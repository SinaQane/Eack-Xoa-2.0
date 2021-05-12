package apps.mainpage.pages.explore.logic;

import apps.mainpage.logic.MainPageAgent;
import db.TweetDB;
import models.Tweet;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomAgent
{
    public List<String> getTweets()
    {
        List<Tweet> allTweets = TweetDB.getTweetDB().getALl();
        List<String> result = new LinkedList<>();
        List<Tweet> tweets = new LinkedList<>();

        for (Tweet tweet : allTweets)
        {
            if (MainPageAgent.getMainPageAgent().isValid(tweet))
            {
                tweets.add(tweet);
            }
        }

        if (tweets.size() == 0)
        {
            result.add("");
            result.add("");
            return result;
        }

        if (tweets.size() == 1)
        {
            result.add(tweets.get(0).getId());
            result.add("");
            return result;
        }

        Random random = new Random();
        int num1 = random.nextInt(tweets.size());
        int num2 = random.nextInt(tweets.size());
        while (num1 == num2)
        {
            num2 = random.nextInt(tweets.size());
        }

        result.add(tweets.get(num1).getId());
        result.add(tweets.get(num2).getId());
        return result;
    }
}
