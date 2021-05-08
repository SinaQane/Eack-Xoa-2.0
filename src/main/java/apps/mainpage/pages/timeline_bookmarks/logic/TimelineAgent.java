package apps.mainpage.pages.timeline_bookmarks.logic;

import db.TweetDB;
import db.UserDB;
import models.Tweet;
import models.User;
import utils.HashMapUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TimelineAgent
{
    private final User user;
    private final String pageKind;

    public TimelineAgent(User user, String kind)
    {
        this.user = user;
        this.pageKind = kind;
    }

    // A list containing Triple Lists containing three tweets, each for one page.
    // Double Strings for each tweet: 1. tweet's id - 2. is it retweeted? (0 for no and id of retweet-er for yes)
    public List<List<String[]>> getTweets()
    {
        List<String[]> tweets = new LinkedList<>();

        if (this.pageKind.equals("timeline"))
        {
            HashMap<String[], Long> tweetsMap = new HashMap<>();

            for (Long userId : user.getProfile().getFollowings())
            {
                User otherUser = UserDB.getUserDB().get(userId);

                if (!user.getProfile().getMuted().contains(userId))
                {
                    for (String userTweet : otherUser.getProfile().getUserTweets())
                    {
                        Tweet tweet = TweetDB.getTweetDB().get(userTweet);
                        tweetsMap.put(new String[]{userTweet, "0"}, tweet.getTweetDate().getTime());
                    }

                    for (String retweetedTweet : otherUser.getProfile().getRetweetedTweets())
                    {
                        Tweet tweet = TweetDB.getTweetDB().get(retweetedTweet);
                        tweetsMap.put(new String[]{retweetedTweet, userId.toString()}, tweet.getTweetDate().getTime());
                    }
                }
            }

            for (Map.Entry<String[], Long> e : HashMapUtil.sortByValue(tweetsMap).entrySet())
            {
                tweets.add(0, e.getKey());
            }
        }
        else if (this.pageKind.equals("bookmarks"))
        {
            for (String id : user.getProfile().getSavedTweets())
            {
                tweets.add(new String[]{id, "0"});
            }
        }

        List<List<String[]>> result = new LinkedList<>();

        if (tweets.size() == 0)
        {
            return null;
        }

        for (int i = 0; i < tweets.size(); i = i+3)
        {
            List<String[]> temp = new LinkedList<>();

            String[] firstTweet = new String[2];
            String[] secondTweet = new String[2];
            String[] thirdTweet = new String[2];

            firstTweet[0] = tweets.get(i)[0];
            firstTweet[1] = tweets.get(i)[1];

            if (i + 1 < tweets.size())
            {
                secondTweet[0] = tweets.get(i + 1)[0];
                secondTweet[1] = tweets.get(i + 1)[1];
            }
            else
            {
                secondTweet[0] = secondTweet[1] = "null";
            }

            if (i + 2 < tweets.size())
            {
                thirdTweet[0] = tweets.get(i + 2)[0];
                thirdTweet[1] = tweets.get(i + 2)[1];
            }
            else
            {
                thirdTweet[0] = thirdTweet[1] = "null";
            }

            temp.add(firstTweet);
            temp.add(secondTweet);
            temp.add(thirdTweet);

            result.add(temp);
        }
        return result;
    }

    public int getNumberOfPages()
    {
        if (this.getTweets() == null)
        {
            return 0;
        }
        return this.getTweets().size();
    }

    public List<String[]> getPage(int page)
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
