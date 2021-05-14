package model;

import db.ChatDB;

import java.util.LinkedList;
import java.util.List;

public class Chat
{
    private final long id;
    private final boolean group;
    private String chatsName;
    private final List<Long> users = new LinkedList<>();
    private final List<Message> messages = new LinkedList<>();

    // Just a dummy constructor for easy use in ChatsListPaneFXML class
    public Chat()
    {
        this.id = -1;
        this.group = false;
    }

    // For group chats
    public Chat(User user, String chatsName)
    {
        ChatDB.getChatDB().setLastId(ChatDB.getChatDB().getLastId() + 1);
        this.id = ChatDB.getChatDB().getLastId();
        this.chatsName = chatsName;
        this.group = true;
        this.users.add(user.getId());
        ChatDB.getChatDB().save(this);
    }

    // For private chats
    public Chat(User user1, User user2)
    {
        ChatDB.getChatDB().setLastId(ChatDB.getChatDB().getLastId() + 1);
        this.id = ChatDB.getChatDB().getLastId();
        this.chatsName = user1.getUsername() + " - " + user2.getUsername();
        this.group = false;
        this.users.add(user1.getId());
        this.users.add(user2.getId());
        ChatDB.getChatDB().save(this);
    }

    public long getId()
    {
        return this.id;
    }

    public boolean isGroup()
    {
        return group;
    }

    public String getChatsName()
    {
        return chatsName;
    }

    public void setChatsName(String chatsName)
    {
        this.chatsName = chatsName;
    }

    public List<Long> getUsers()
    {
        return this.users;
    }

    public long getLastMessageTime()
    {
        if (this.messages.size() != 0)
        {
            return this.messages.get(this.messages.size() - 1).getMessageDate();
        }
        return 0;
    }

    public int getUnseenCount(User user)
    {
        int cnt = 0;
        for (Message message : this.messages)
        {
            if (!message.getSeenList().contains(user.getId()))
            {
                cnt++;
            }
        }
        return cnt;
    }

    public void addToUsers(User user)
    {
        if (!this.users.contains(user.getId()))
        {
            this.users.add(user.getId());
        }
    }

    public List<Message> getMessages()
    {
        return this.messages;
    }

    public void addToMessages(Message message)
    {
        this.messages.add(message);
    }
}
