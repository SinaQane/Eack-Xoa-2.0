package apps.groupframe.event;

import java.util.EventObject;

public class GroupEvent extends EventObject
{
    private final GroupForm groupForm;

    public GroupEvent(Object source, GroupForm groupForm)
    {
        super(source);
        this.groupForm = groupForm;
    }

    public GroupForm getGroupForm()
    {
        return groupForm;
    }
}
