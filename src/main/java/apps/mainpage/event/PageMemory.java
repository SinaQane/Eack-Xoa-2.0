package apps.mainpage.event;

public class PageMemory
{
    private final String page;
    private final String tweetId;
    private final Long userId;

    public PageMemory(String page)
    {
        this.page = page;
        this.tweetId = "";
        this.userId = 0L;
    }

    public PageMemory(String page, String tweetId)
    {
        this.page = page;
        this.tweetId = tweetId;
        this.userId = 0L;
    }

    public PageMemory(String page, Long userId)
    {
        this.page = page;
        this.tweetId = "";
        this.userId = userId;
    }

    public String getPage()
    {
        return page;
    }

    public String getTweetId()
    {
        return tweetId;
    }

    public Long getUserId()
    {
        return userId;
    }
}
