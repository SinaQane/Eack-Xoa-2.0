package model;

import db.ChatDB;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Message
{
    private final String chatId;
    private final long ownerId;
    private final int index;

    private String text;
    private final String picturePath;
    private final String tweetId;
    private final long messageDate;
    private final List<Long> seenList = new LinkedList<>();

    public Message(Chat chat, User user, String text, String picPath)
    {
        this.chatId = String.valueOf(chat.getId());
        this.ownerId = user.getId();
        this.index = chat.getMessages().size();
        this.text = text;
        this.picturePath = picPath;
        this.tweetId = "";
        this.messageDate = new Date().getTime();
        chat.addToMessages(this);
    }

    public Message(Chat chat, User user, Tweet tweet)
    {
        this.chatId = String.valueOf(chat.getId());
        this.ownerId = user.getId();
        this.index = chat.getMessages().size();
        this.text = tweet.getText();
        this.picturePath = tweet.getPicturePath();
        this.tweetId = tweet.getId();
        this.messageDate = new Date().getTime();
        chat.addToMessages(this);
    }

    public String getText()
    {
        return text;
    }

    public String getPicturePath()
    {
        return picturePath;
    }

    public String getChatId()
    {
        return chatId;
    }

    public long getOwnerId()
    {
        return ownerId;
    }

    public int getIndex()
    {
        return index;
    }

    public String getTweetId()
    {
        return tweetId;
    }

    public long getMessageDate()
    {
        return messageDate;
    }

    public List<Long> getSeenList()
    {
        return seenList;
    }

    public void addToSeen(User user)
    {
        if (!this.seenList.contains(user.getId()))
        {
            this.seenList.add(user.getId());
        }
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
