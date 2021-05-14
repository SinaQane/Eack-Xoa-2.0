package apps.mainpage.pages.userslist_notifications.view;

import apps.components.notification.listener.NotificationPaneListener;
import apps.components.notification.view.NotificationPane;
import apps.components.notification.view.NotificationPaneFXML;
import apps.components.userpane.listener.UserPaneListener;
import apps.components.emptypanes.emptypane.EmptyPane;
import apps.components.userpane.view.UserPane;
import apps.components.userpane.view.UserPaneFXML;
import apps.mainpage.pages.userslist_notifications.logic.NotificationsAgent;
import apps.mainpage.pages.userslist_notifications.logic.UsersListAgent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import models.Notification;
import models.User;
import utils.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UsersList
{
    private static final String USERS_LIST = Config.getConfig("paths").getProperty(String.class, "usersList");

    private Pane listPane;
    private final FXMLLoader loader;
    private final User user;
    private final String pageKind;

    public UsersList(String pageKind, User user)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(USERS_LIST)));
        try
        {
            listPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.user = user;
        this.pageKind = pageKind;
    }

    public Pane getListPane()
    {
        return this.listPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {
        UsersListFXML fxmlController = this.loader.getController();

        fxmlController.setPage(page);
        fxmlController.setUser(this.user);
        fxmlController.setPageKind(this.pageKind);

        if (this.pageKind.equals("notifications"))
        {
            NotificationsAgent logicalAgent = new NotificationsAgent(this.user);

            List<Notification> notifications = logicalAgent.getPage(page);

            for (int i = 0; i < 5; i++)
            {
                if (notifications.get(i).getOwner() == 0)
                {
                    fxmlController.setUserPane(i, new EmptyPane().getPane());
                }
                else
                {
                    NotificationPane notificationPane = new NotificationPane();
                    NotificationPaneFXML notificationPaneFXML = notificationPane.getLoader().getController();
                    notificationPaneFXML.setListener(new NotificationPaneListener());
                    notificationPaneFXML.setData(notifications.get(i));
                    fxmlController.setUserPane(i, notificationPane.getNotificationPane());
                }
            }

            fxmlController.getPreviousButton().setDisable(!logicalAgent.hasPreviousPage(page));
            fxmlController.getNextButton().setDisable(!logicalAgent.hasNextPage(page));
        }
        else
        {
            UsersListAgent logicalAgent = new UsersListAgent(this.user, this.pageKind);

            List<Long> users = logicalAgent.getPage(page);

            for (int i = 0; i < 5; i++)
            {
                if (users.get(i) == 0)
                {
                    fxmlController.setUserPane(i, new EmptyPane().getPane());
                }
                else
                {
                    UserPane userPane = new UserPane();
                    UserPaneFXML userPaneFXML = userPane.getLoader().getController();
                    userPaneFXML.setListener(new UserPaneListener());
                    userPaneFXML.setData(users.get(i));
                    fxmlController.setUserPane(i, userPane.getUserPane());
                }
            }

            fxmlController.getPreviousButton().setDisable(!logicalAgent.hasPreviousPage(page));
            fxmlController.getNextButton().setDisable(!logicalAgent.hasNextPage(page));
        }
    }
}
