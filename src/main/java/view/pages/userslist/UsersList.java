package view.pages.userslist;

import listener.components.notification.NotificationPaneListener;
import view.components.notification.NotificationPane;
import view.components.notification.NotificationPaneFXML;
import listener.components.user.UserPaneListener;
import view.components.empty.emptyuser.EmptyUserPane;
import view.components.user.UserPane;
import view.components.user.UserPaneFXML;
import controller.pages.userslist.NotificationsLogic;
import controller.pages.userslist.UsersListLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Notification;
import model.User;
import util.Config;

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
            NotificationsLogic logicalAgent = new NotificationsLogic(this.user);

            List<Notification> notifications = logicalAgent.getPage(page);

            for (int i = 0; i < 5; i++)
            {
                if (notifications.get(i).getOwner() == 0)
                {
                    fxmlController.setUserPane(i, new EmptyUserPane().getPane());
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
            UsersListLogic logicalAgent = new UsersListLogic(this.user, this.pageKind);

            List<Long> users = logicalAgent.getPage(page);

            for (int i = 0; i < 5; i++)
            {
                if (users.get(i) == 0)
                {
                    fxmlController.setUserPane(i, new EmptyUserPane().getPane());
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
