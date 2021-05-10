package apps.mainpage.logic;

import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.pages.settings.view.SettingsPane;
import apps.mainpage.pages.timeline_bookmarks.view.TimelinePane;

// A singleton class to return every main pane.
public class PanesController
{
    static PanesController panesController;

    ProfilePane profilePane = new ProfilePane(MainPageAgent.getMainPageAgent().getUser());
    SettingsPane settingsPane = new SettingsPane();
    TimelinePane timelinePane = new TimelinePane("timeline");
    TimelinePane bookmarksPane = new TimelinePane("bookmarks");

    private PanesController() {}

    public static PanesController getPanesController()
    {
        if (panesController == null)
        {
            panesController = new PanesController();
        }
        return panesController;
    }

    public ProfilePane getProfilePane(int page)
    {
        this.profilePane.refresh(page);
        return this.profilePane;
    }

    public SettingsPane getSettingsPane()
    {
        this.settingsPane.refresh();
        return this.settingsPane;
    }

    public TimelinePane getTimelinePane(String pageKind, int page)
    {
        if (pageKind.equals("bookmarks"))
        {
            this.bookmarksPane.refresh(page);
            return this.bookmarksPane;
        }
        this.timelinePane.refresh(page);
        return this.timelinePane;
    }
}
