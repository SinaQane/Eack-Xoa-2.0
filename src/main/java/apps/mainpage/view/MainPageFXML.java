package apps.mainpage.view;

import apps.mainpage.listener.MainPageListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainPageFXML
{
    private MainPageListener listener;

    public Text mainPageLabel;
    public Pane mainPane;

    public Button homeButton;
    public Button exploreButton;
    public Button notificationsButton;
    public Button messagesButton;
    public Button bookmarksButton;
    public Button groupsButton;
    public Button profileButton;
    public Button settingsButton;
    public Button logoutButton;

    public void home()
    {
        listener.eventOccurred(homeButton);
    }

    public void explore()
    {
        listener.eventOccurred(exploreButton);
    }

    public void notifications()
    {
        listener.eventOccurred(notificationsButton);
    }

    public void messages()
    {
        listener.eventOccurred(messagesButton);
    }

    public void bookmarks()
    {
        listener.eventOccurred(bookmarksButton);
    }

    public void groups()
    {
        listener.eventOccurred(groupsButton);
    }

    public void profile()
    {
        listener.eventOccurred(profileButton);
    }

    public void settings()
    {
        listener.eventOccurred(settingsButton);
    }

    public void logout()
    {
        listener.eventOccurred(logoutButton);
    }

    public void setListener(MainPageListener mainPageListener)
    {
        this.listener = mainPageListener;
    }

    public Pane getMainPane()
    {
        return this.mainPane;
    }

    public void setMainPane(Pane mainPane)
    {
        this.mainPane = mainPane;
    }
}
