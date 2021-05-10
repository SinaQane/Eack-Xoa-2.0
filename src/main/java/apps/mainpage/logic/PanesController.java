package apps.mainpage.logic;

import apps.mainpage.pages.profile_viewuser.listener.ProfileListener;
import apps.mainpage.pages.profile_viewuser.listener.UserViewListener;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.pages.profile_viewuser.view.ProfilePaneFXML;
import apps.mainpage.pages.settings.view.SettingsPane;
import apps.mainpage.pages.timeline_bookmarks.listener.TimelineListener;
import apps.mainpage.pages.timeline_bookmarks.view.TimelinePane;
import apps.mainpage.pages.timeline_bookmarks.view.TimelinePaneFXML;
import apps.mainpage.pages.userslist.listener.UsersListListener;
import apps.mainpage.pages.userslist.view.UsersList;
import apps.mainpage.pages.userslist.view.UsersListFXML;
import apps.mainpage.pages.viewtweet.listener.TweetsListListener;
import apps.mainpage.pages.viewtweet.view.TweetsList;
import apps.mainpage.pages.viewtweet.view.TweetsListFXML;
import db.TweetDB;
import db.UserDB;

// A singleton class to return every main pane.
public class PanesController
{
    static PanesController panesController;

    ProfilePane profilePane;
    SettingsPane settingsPane;
    TimelinePane timelinePane;
    TimelinePane bookmarksPane;

    private PanesController() {}

    public static PanesController getPanesController()
    {
        if (panesController == null)
        {
            panesController = new PanesController();
        }
        return panesController;
    }

    // Our user's profilePane
    public ProfilePane getProfilePane(int page)
    {
        this.profilePane = new ProfilePane(MainPageAgent.getMainPageAgent().getUser());
        ((ProfilePaneFXML) this.profilePane.getLoader().getController()).setUserViewListener(new UserViewListener());
        ((ProfilePaneFXML) this.profilePane.getLoader().getController()).setProfileListener(new ProfileListener());
        this.profilePane.refresh(page);
        return this.profilePane;
    }

    // Other users' profilePane
    public ProfilePane getProfilePane(long id, int page)
    {
        ProfilePane userProfilePane = new ProfilePane(UserDB.getUserDB().get(id));
        ((ProfilePaneFXML) userProfilePane.getLoader().getController()).setUserViewListener(new UserViewListener());
        ((ProfilePaneFXML) userProfilePane.getLoader().getController()).setProfileListener(new ProfileListener());
        userProfilePane.refresh(page);
        return userProfilePane;
    }

    public UsersList getUserslistPane(String pageKind, long id, int page)
    {
        UsersList usersList = new UsersList(pageKind,UserDB.getUserDB().get(id));
        ((UsersListFXML) usersList.getLoader().getController()).setListener(new UsersListListener());
        usersList.refresh(page);
        return usersList;
    }

    public TweetsList getTweetsListPane(String id, int page)
    {
        TweetsList tweetsList = new TweetsList(TweetDB.getTweetDB().get(id));
        ((TweetsListFXML) tweetsList.getLoader().getController()).setListener(new TweetsListListener());
        tweetsList.refresh(page);
        return tweetsList;
    }

    public SettingsPane getSettingsPane()
    {
        this.settingsPane = new SettingsPane();
        this.settingsPane.refresh();
        return this.settingsPane;
    }

    public TimelinePane getTimelinePane(String pageKind, int page)
    {
        if (pageKind.equals("bookmarks"))
        {
            this.bookmarksPane = new TimelinePane("bookmarks");
            ((TimelinePaneFXML) this.bookmarksPane.getLoader().getController()).setListener(new TimelineListener());
            this.bookmarksPane.refresh(page);
            return this.bookmarksPane;
        }
        this.timelinePane = new TimelinePane("timeline");
        ((TimelinePaneFXML) this.timelinePane.getLoader().getController()).setListener(new TimelineListener());
        this.timelinePane.refresh(page);
        return this.timelinePane;
    }
}