package apps.mainpage.pages.profile.event;

public class TweetFormEvent
{
    private final String tweetText;
    private final String picPath;

    public TweetFormEvent(String tweetText, String picPath)
    {
        this.tweetText = tweetText;
        this.picPath = picPath;
    }

    public String getTweetText()
    {
        return tweetText;
    }

    public String getPicPath()
    {
        return picPath;
    }
}
