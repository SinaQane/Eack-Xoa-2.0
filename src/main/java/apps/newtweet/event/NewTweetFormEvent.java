package apps.newtweet.event;

public class NewTweetFormEvent
{
    private final String upperTweet;
    private final String tweetText;
    private final String picPath;

    public NewTweetFormEvent(String upperTweet, String tweetText, String picPath)
    {
        this.upperTweet = upperTweet;
        this.tweetText = tweetText;
        this.picPath = picPath;
    }

    public String getUpperTweet()
    {
        return upperTweet;
    }

    public String getTweetText()
    {
        return this.tweetText;
    }

    public String getPicPath()
    {
        return this.picPath;
    }
}