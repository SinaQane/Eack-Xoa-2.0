package apps.groupframe.event;

import models.Group;

import java.util.List;

public class GroupForm
{
    private final Group group;
    private final String title;
    private final List<String> toAdd;
    private final List<String> roRemove;

    public GroupForm(Group group, String title, List<String> toAdd, List<String> roRemove)
    {
        this.group = group;
        this.title = title;
        this.toAdd = toAdd;
        this.roRemove = roRemove;
    }

    public Group getGroup()
    {
        return group;
    }

    public String getTitle()
    {
        return title;
    }

    public List<String> getToAdd()
    {
        return toAdd;
    }

    public List<String> getRoRemove()
    {
        return roRemove;
    }
}
