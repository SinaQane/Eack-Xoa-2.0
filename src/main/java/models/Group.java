package models;

import java.util.LinkedList;
import java.util.List;

public class Group
{
    private String title; // Group's title is a unique string, but not constant.
    private final List<Long> membersId = new LinkedList<>();

    public Group(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public List<Long> getMembersId()
    {
        return membersId;
    }

    public void editTitle(String newTitle)
    {
        this.title = newTitle;
    }

    public void addToGroup(User user)
    {
        if (!this.membersId.contains(user.getId()))
        {
            this.membersId.add(user.getId());
        }
    }

    public void removeFromGroup(User user)
    {
        this.membersId.remove(user.getId());
    }
}
