package model;

public class Notification
{
    private final long owner;
    private final long requestFrom;
    private final String text;

    public Notification(long owner, long request, String text)
    {
        this.owner = owner;
        this.requestFrom = request;
        this.text = text;
    }

    public Notification(long owner, String text)
    {
        this.owner = owner;
        this.requestFrom = 0L;
        this.text = text;
    }

    public long getOwner()
    {
        return owner;
    }

    public long getRequestFrom()
    {
        return requestFrom;
    }

    public String getText()
    {
        return text;
    }
}
