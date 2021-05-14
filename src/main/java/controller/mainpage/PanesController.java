package controller.mainpage;

import view.pages.explore.ExplorePane;
import view.pages.explore.SearchResultsPane;
import view.pages.groups.GroupsPane;
import view.pages.messages.MessagesPane;
import listener.pages.profile.ProfileListener;
import listener.pages.profile.ViewUserListener;
import view.pages.profile.ProfilePane;
import view.pages.profile.ProfilePaneFXML;
import view.pages.settings.SettingsPane;
import listener.pages.timeline.TimelineListener;
import view.pages.timeline.TimelinePane;
import view.pages.timeline.TimelinePaneFXML;
import listener.pages.userslist.UsersListListener;
import view.pages.userslist.UsersList;
import view.pages.userslist.UsersListFXML;
import listener.pages.tweetslist.TweetsListListener;
import view.pages.tweetslist.TweetsList;
import view.pages.tweetslist.TweetsListFXML;
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
        this.profilePane = new ProfilePane(MainPageController.getMainPageAgent().getUser());
        ((ProfilePaneFXML) this.profilePane.getLoader().getController()).setUserViewListener(new ViewUserListener());
        ((ProfilePaneFXML) this.profilePane.getLoader().getController()).setProfileListener(new ProfileListener());
        this.profilePane.refresh(page);
        return this.profilePane;
    }

    // Other users' profilePane
    public ProfilePane getProfilePane(long id, int page)
    {
        ProfilePane userProfilePane = new ProfilePane(UserDB.getUserDB().get(id));
        ((ProfilePaneFXML) userProfilePane.getLoader().getController()).setUserViewListener(new ViewUserListener());
        ((ProfilePaneFXML) userProfilePane.getLoader().getController()).setProfileListener(new ProfileListener());
        userProfilePane.refresh(page);
        return userProfilePane;
    }

    public UsersList getUserslistPane(String pageKind, long id, int page)
    {
        UsersList usersList = new UsersList(pageKind, UserDB.getUserDB().get(id));
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

    public GroupsPane getGroupsPane(int page)
    {
        GroupsPane groupsPane = new GroupsPane();
        groupsPane.refresh(page);
        return groupsPane;
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

    public ExplorePane getExplorePane()
    {
        ExplorePane explorePane = new ExplorePane();
        explorePane.refresh();
        return explorePane;
    }

    public SearchResultsPane getSearchResultsPane(String searched, int page)
    {
        SearchResultsPane searchPane = new SearchResultsPane(searched);
        searchPane.refresh(page);
        return searchPane;
    }

    public MessagesPane getMessagesPane()
    {
        MessagesPane messagesPane = new MessagesPane();
        messagesPane.refresh();
        return messagesPane;
    }
}