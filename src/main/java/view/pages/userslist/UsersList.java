package view.pages.userslist;

import view.components.notification.NotificationPane;
import view.components.notification.NotificationPaneFXML;
import view.components.empty.emptyuser.EmptyUserPane;
import view.components.user.UserPane;
import view.components.user.UserPaneFXML;
import controller.pages.userslist.NotificationsLogic;
import controller.pages.userslist.UsersListLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Notification;
import model.User;
import config.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UsersList
{
    private static final String USERS_LIST = Config.getConfig("paths").getProperty(String.class, "usersList");

    private Pane pane;
    private final FXMLLoader loader;

    private final User user;
    private final String pageKind;

    public UsersList(String pageKind, User user)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(USERS_LIST)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.user = user;
        this.pageKind = pageKind;
    }

    public Pane getPane()
    {
        return this.pane;
    }

    public void refresh(int page)
    {
        UsersListFXML usersListFXML = this.loader.getController();

        usersListFXML.setPage(page);
        usersListFXML.setUser(this.user);
        usersListFXML.setPageKind(this.pageKind);

        if (this.pageKind.equals("notifications"))
        {
            NotificationsLogic logic = new NotificationsLogic(this.user);

            List<Notification> notifications = logic.getPage(page);

            for (int i = 0; i < 5; i++)
            {
                if (notifications.get(i).getOwner() == 0)
                {
                    usersListFXML.setUserPane(i, new EmptyUserPane().getPane());
                }
                else
                {
                    NotificationPane notificationPane = new NotificationPane();
                    NotificationPaneFXML notificationPaneFXML = notificationPane.getLoader().getController();
                    notificationPaneFXML.setData(notifications.get(i));
                    usersListFXML.setUserPane(i, notificationPane.getPane());
                }
            }

            usersListFXML.getPreviousButton().setDisable(!logic.hasPreviousPage(page));
            usersListFXML.getNextButton().setDisable(!logic.hasNextPage(page));
        }
        else
        {
            UsersListLogic logic = new UsersListLogic(this.user, this.pageKind);

            List<Long> users = logic.getPage(page);

            for (int i = 0; i < 5; i++)
            {
                if (users.get(i) == 0)
                {
                    usersListFXML.setUserPane(i, new EmptyUserPane().getPane());
                }
                else
                {
                    UserPane userPane = new UserPane();
                    UserPaneFXML userPaneFXML = userPane.getLoader().getController();
                    userPaneFXML.setData(users.get(i));
                    usersListFXML.setUserPane(i, userPane.getPane());
                }
            }

            usersListFXML.getPreviousButton().setDisable(!logic.hasPreviousPage(page));
            usersListFXML.getNextButton().setDisable(!logic.hasNextPage(page));
        }
    }
}
