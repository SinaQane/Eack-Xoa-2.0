package apps.mainpage.pages.timeline_bookmarks.event;

import java.util.EventObject;

public class TimelineEvent extends EventObject
{
    private final int page;
    private final String pageKind;

    public TimelineEvent(Object source, String kind, int page)
    {
        super(source);
        this.page = page;
        this.pageKind = kind;
    }

    public int getPage()
    {
        return this.page;
    }

    public String getPageKind()
    {
        return this.pageKind;
    }
}
