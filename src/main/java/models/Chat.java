package models;

import db.ChatDB;

import java.util.LinkedList;
import java.util.List;

public class Chat
{
    private final long id;
    private final List<Long> users = new LinkedList<>();
    private final List<Message> messages = new LinkedList<>();

    // For group chats
    public Chat(User user)
    {
        ChatDB.getChatDB().setLastId(ChatDB.getChatDB().getLastId() + 1);
        this.id = ChatDB.getChatDB().getLastId();
        this.users.add(user.getId());
    }

    // For private chats
    public Chat(User user1, User user2)
    {
        ChatDB.getChatDB().setLastId(ChatDB.getChatDB().getLastId() + 1);
        this.id = ChatDB.getChatDB().getLastId();
        this.users.add(user1.getId());
        this.users.add(user2.getId());
    }

    public long getId()
    {
        return this.id;
    }

    public List<Long> getUsers()
    {
        return this.users;
    }

    public void addToUsers(User user)
    {
        if (!this.users.contains(user.getId()))
        {
            this.users.add(user.getId());
        }
    }

    public void removeFromUsers(User user)
    {
        this.users.remove(user.getId());
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
