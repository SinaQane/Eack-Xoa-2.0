package apps.mainpage.pages.userslist_notifications.view;

import apps.mainpage.pages.userslist_notifications.event.UsersListForm;
import apps.mainpage.pages.userslist_notifications.event.UsersListEvent;
import apps.mainpage.pages.userslist_notifications.listener.UsersListListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import models.User;

public class UsersListFXML
{
    private UsersListListener listener;

    private User user;
    private int page;
    private String pageKind;

    public Pane userPane1;
    public Pane userPane2;
    public Pane userPane3;
    public Pane userPane4;
    public Pane userPane5;

    public Button previousButton;
    public Button nextButton;

    public void setListener(UsersListListener listener)
    {
        this.listener = listener;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setPageKind(String pageKind)
    {
        this.pageKind = pageKind;
    }

    public void setUserPane(int i, Pane userPane)
    {
        if (i == 0)
        {
            this.userPane1.getChildren().clear();
            this.userPane1.getChildren().add(userPane);
        }
        if (i == 1)
        {
            this.userPane2.getChildren().clear();
            this.userPane2.getChildren().add(userPane);
        }
        if (i == 2)
        {
            this.userPane3.getChildren().clear();
            this.userPane3.getChildren().add(userPane);
        }
        if (i == 3)
        {
            this.userPane4.getChildren().clear();
            this.userPane4.getChildren().add(userPane);
        }
        if (i == 4)
        {
            this.userPane5.getChildren().clear();
            this.userPane5.getChildren().add(userPane);
        }
    }

    public Button getPreviousButton()
    {
        return previousButton;
    }

    public Button getNextButton()
    {
        return nextButton;
    }

    public void previous()
    {
        UsersListForm pageEvent = new UsersListForm(this.user, this.page, this.pageKind);
        listener.eventOccurred(new UsersListEvent(previousButton, pageEvent));
    }

    public void next()
    {
        UsersListForm pageEvent = new UsersListForm(this.user, this.page, this.pageKind);
        listener.eventOccurred(new UsersListEvent(nextButton, pageEvent));
    }
}
