package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Message
{
    private String text;
    private final long ownerId;
    private final long sentTime;
    private final List<Long> seenList = new LinkedList<>();

    public Message(User user, String text)
    {
        this.text = text;
        this.ownerId = user.getId();
        this.sentTime = new Date().getTime();
    }

    public String getText()
    {
        return text;
    }

    public long getOwnerId()
    {
        return ownerId;
    }

    public long getSentTime()
    {
        return sentTime;
    }

    public List<Long> getSeenList()
    {
        return seenList;
    }

    public void edit(String text)
    {
        this.text = "(edited) " + text;
    }

    public void delete()
    {
        this.text = "*this message was deleted*";
    }
}
