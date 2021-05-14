package apps.mainpage.pages.explore.view;

import apps.mainpage.pages.explore.listener.SearchResultsPaneListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.EventObject;

public class SearchResultsPaneFXML
{
    private final SearchResultsPaneListener listener = new SearchResultsPaneListener();

    private String searched;
    private int page;

    public Pane userPane1;
    public Pane userPane2;
    public Pane userPane3;
    public Pane userPane4;
    public Button previousButton;
    public Button nextButton;

    public void setUserPane(int pane, Pane userPane)
    {
        if (pane == 0)
        {
            this.userPane1.getChildren().clear();
            this.userPane1.getChildren().add(userPane);
        }
        else if (pane == 1)
        {
            this.userPane2.getChildren().clear();
            this.userPane2.getChildren().add(userPane);
        }
        else if (pane == 2)
        {
            this.userPane3.getChildren().clear();
            this.userPane3.getChildren().add(userPane);
        }
        else if (pane == 3)
        {
            this.userPane4.getChildren().clear();
            this.userPane4.getChildren().add(userPane);
        }
    }

    public void setSearched(String searched)
    {
        this.searched = searched;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public Button getPreviousButton()
    {
        return this.previousButton;
    }

    public Button getNextButton()
    {
        return this.nextButton;
    }

    public void previous()
    {
        listener.eventOccurred(new EventObject(previousButton), this.searched, this.page);
    }

    public void next()
    {
        listener.eventOccurred(new EventObject(nextButton), this.searched, this.page);
    }
}
