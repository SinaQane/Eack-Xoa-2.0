package controller.pages.profile;

import controller.mainpage.MainPageController;
import db.TweetDB;
import model.User;

import java.util.LinkedList;
import java.util.List;

public class ProfileLogic
{
    private final User user;

    public ProfileLogic(User user)
    {
        this.user = user;
    }

    public List<List<String[]>> getTweets()
    {
        List<String[]> allTweets = user.getProfile().getHomePageTweets();

        List<String[]> userTweets = new LinkedList<>();
        List<List<String[]>> result = new LinkedList<>();

        for (String[] tweet : allTweets)
        {
            if (MainPageController.getMainPageAgent().isValid(TweetDB.getTweetDB().get(tweet[0])))
            {
                userTweets.add(tweet);
            }
        }

        if (userTweets.size() == 0)
        {
            return null;
        }

        for (int i = 0; i < userTweets.size(); i = i+2)
        {
            List<String[]> temp = new LinkedList<>();

            String[] firstTweet = new String[2];
            String[] secondTweet = new String[2];

            firstTweet[0] = userTweets.get(i)[0];
            firstTweet[1] = userTweets.get(i)[1].equals("0") ? "0" : this.user.getId().toString();

            if (i + 1 != userTweets.size())
            {
                secondTweet[0] = userTweets.get(i + 1)[0];
                secondTweet[1] = userTweets.get(i + 1)[1].equals("0") ? "0" : this.user.getId().toString();
            }
            else
            {
                secondTweet[0] = secondTweet[1] = "null";
            }

            temp.add(firstTweet);
            temp.add(secondTweet);

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
