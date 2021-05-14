package controller.mainpage;

import view.pages.explore.ExplorePane;
import view.pages.explore.SearchResultsPane;
import view.pages.groups.GroupsPane;
import view.pages.messages.MessagesPane;
import view.pages.profile.ProfilePane;
import view.pages.settings.SettingsPane;
import view.pages.timeline.TimelinePane;
import view.pages.userslist.UsersList;
import view.pages.tweetslist.TweetsList;
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
        this.profilePane = new ProfilePane(MainPageController.getMainPageController().getUser());
        this.profilePane.refresh(page);
        return this.profilePane;
    }

    // Other users' profilePane
    public ProfilePane getProfilePane(long id, int page)
    {
        ProfilePane userProfilePane = new ProfilePane(UserDB.getUserDB().get(id));
        userProfilePane.refresh(page);
        return userProfilePane;
    }

    public UsersList getUserslistPane(String pageKind, long id, int page)
    {
        UsersList usersList = new UsersList(pageKind, UserDB.getUserDB().get(id));
        usersList.refresh(page);
        return usersList;
    }

    public TweetsList getTweetsListPane(String id, int page)
    {
        TweetsList tweetsList = new TweetsList(TweetDB.getTweetDB().get(id));
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
            this.bookmarksPane.refresh(page);
            return this.bookmarksPane;
        }
        this.timelinePane = new TimelinePane("timeline");
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