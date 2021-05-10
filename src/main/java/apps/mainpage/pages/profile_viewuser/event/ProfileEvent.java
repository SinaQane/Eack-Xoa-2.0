package apps.mainpage.pages.profile_viewuser.event;

import java.util.EventObject;

public class ProfileEvent extends EventObject
{
    private final PageFormEvent pageEvent;

    public ProfileEvent(Object source, PageFormEvent pageEvent)
    {
        super(source);
        this.pageEvent = pageEvent;
    }

    public PageFormEvent getPageEvent()
    {
        return pageEvent;
    }
}
