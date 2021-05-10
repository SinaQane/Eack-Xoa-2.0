package apps.mainpage.pages.viewlist.view;

import apps.mainpage.pages.viewlist.event.PageFormEvent;
import apps.mainpage.pages.viewlist.event.UsersListEvent;
import apps.mainpage.pages.viewlist.listener.ListListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import models.User;

public class UsersListFXML
{
    ListListener listener;

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

    public void setListener(ListListener listener)
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

    public void previous()
    {
        PageFormEvent pageEvent = new PageFormEvent(this.user, this.page, this.pageKind);
        listener.eventOccurred(new UsersListEvent(previousButton, pageEvent));
    }

    public void next()
    {
        PageFormEvent pageEvent = new PageFormEvent(this.user, this.page, this.pageKind);
        listener.eventOccurred(new UsersListEvent(nextButton, pageEvent));
    }
}
