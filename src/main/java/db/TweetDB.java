package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Tweet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TweetDB implements DBSet<Tweet>
{
    private static final Logger logger = LogManager.getLogger(TweetDB.class);

    static TweetDB tweetDB;

    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private final Gson gson = gsonBuilder.setPrettyPrinting().setDateFormat("MMM dd, yyyy  HH:mm:ss.SSS").create();

    private TweetDB() {}

    public static TweetDB getTweetDB()
    {
        if (tweetDB == null)
        {
            tweetDB = new TweetDB();
        }
        return tweetDB;
    }

    @Override
    public Tweet get(String id)
    {
        String path = "./src/main/resources/database/tweets/" + id;
        Tweet result;
        try
        {
            result = gson.fromJson(Files.readString(Paths.get(path)), Tweet.class);
        }
        catch (IOException e)
        {
            result = null;
        }
        return result;
    }

    @Override
    public List<Tweet> getALl()
    {
        List<Tweet> result = new LinkedList<>();
        String path = "./src/main/resources/database/tweets";
        File usersDirectory = new File(path);

        for (String tweetId : Objects.requireNonNull(usersDirectory.list()))
        {
            try
            {
                Tweet tempTweet = gson.fromJson(Files.readString(Paths.get(path + "/" + tweetId)), Tweet.class);
                result.add(tempTweet);
            } catch (IOException ignored) {}
        }

        return result;
    }

    @Override
    public void save(Tweet tweet)
    {
        String path = "./src/main/resources/database/tweets/" + tweet.getId();
        File file = new File(path);
        if(file.getParentFile().mkdirs())
        {
            logger.warn("tweets directory was created.");
        }
        if (!file.exists())
        {
            try
            {
                if (file.createNewFile())
                {
                    logger.debug("tweet " + tweet.getId() + "'s file was created.");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        DBUtil.saveToGson(path, gson.toJson(tweet));

        logger.debug("tweet " + tweet.getId() + "'s file was saved.");
    }

    @Override
    public boolean exists(String id)
    {
        return get(id) != null;
    }
}
