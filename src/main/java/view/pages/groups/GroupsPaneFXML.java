package view.pages.groups;

import listener.pages.groups.GroupsListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.EventObject;

public class GroupsPaneFXML
{
    private final GroupsListener listener = new GroupsListener();

    private int page;

    public Button newGroupButton;
    public Button previousButton;
    public Button nextButton;

    public Pane groupPane1;
    public Pane groupPane2;
    public Pane groupPane3;
    public Pane groupPane4;
    public Pane groupPane5;

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setGroupPane(int i, Pane userPane)
    {
        if (i == 0)
        {
            this.groupPane1.getChildren().clear();
            this.groupPane1.getChildren().add(userPane);
        }
        if (i == 1)
        {
            this.groupPane2.getChildren().clear();
            this.groupPane2.getChildren().add(userPane);
        }
        if (i == 2)
        {
            this.groupPane3.getChildren().clear();
            this.groupPane3.getChildren().add(userPane);
        }
        if (i == 3)
        {
            this.groupPane4.getChildren().clear();
            this.groupPane4.getChildren().add(userPane);
        }
        if (i == 4)
        {
            this.groupPane5.getChildren().clear();
            this.groupPane5.getChildren().add(userPane);
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

    public void newGroup()
    {
        listener.eventOccurred(new EventObject(newGroupButton), page);
    }

    public void previous()
    {
        listener.eventOccurred(new EventObject(previousButton), page);
    }

    public void next()
    {
        listener.eventOccurred(new EventObject(nextButton), page);
    }
}
