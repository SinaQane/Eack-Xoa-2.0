package apps.mainpage.pages.viewtweet.event;

import java.util.EventObject;

public class TweetsListEvent extends EventObject
{
    private final PageFormEvent pageEvent;

    public TweetsListEvent(Object source, PageFormEvent pageEvent)
    {
        super(source);
        this.pageEvent = pageEvent;
    }

    public PageFormEvent getPageEvent()
    {
        return pageEvent;
    }
}
