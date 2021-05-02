package models;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Profile
{
    static private final Logger logger = LogManager.getLogger(Profile.class);

    private long userId;
    private long lastTweetId = 0;

    // Interactions with other users
    private List<String> followers = new LinkedList<>();
    private List<String> followings = new LinkedList<>();
    private List<String> blocked = new LinkedList<>();
    private List<String> muted = new LinkedList<>();
    private List<String> reported = new LinkedList<>();
    private List<String> requests = new LinkedList<>();
    private List<String> pending = new LinkedList<>();

    // Tweets
    private List<String> userTweets = new LinkedList<>();
    private List<String> retweetedTweets = new LinkedList<>();
    private List<String> upvotedTweets = new LinkedList<>();
    private List<String> downvotedTweets = new LinkedList<>();
    private List<String> reportedTweets = new LinkedList<>();

    // Privacy
    private boolean privateState; // "true" if the page is private, "false" if if it's public.
    private boolean infoState; // For Email, Phone number and Birthdate. "true" for public and "false" for private.
    private int lastSeenState; // "0" for no one, "1" for followings only and "2" for everyone.

    Profile(long userId)
    {
        this.userId = userId;
        this.privateState = false;
        this.infoState = false;
        this.lastSeenState = 1;
    }
}
