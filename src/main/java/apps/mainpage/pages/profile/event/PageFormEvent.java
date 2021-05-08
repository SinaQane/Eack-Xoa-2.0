package apps.mainpage.pages.profile.event;

public class PageFormEvent
{
    private final int page;

    public PageFormEvent(int page)
    {
        this.page = page;
    }

    public int getPage()
    {
        return this.page;
    }
}
