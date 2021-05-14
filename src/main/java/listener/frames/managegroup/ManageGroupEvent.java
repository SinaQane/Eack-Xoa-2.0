package listener.frames.managegroup;

import java.util.EventObject;

public class ManageGroupEvent extends EventObject
{
    private final ManageGroupForm manageGroupForm;

    public ManageGroupEvent(Object source, ManageGroupForm manageGroupForm)
    {
        super(source);
        this.manageGroupForm = manageGroupForm;
    }

    public ManageGroupForm getGroupForm()
    {
        return manageGroupForm;
    }
}
