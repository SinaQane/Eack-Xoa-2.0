package apps.mainpage.pages.explore.view;

import apps.mainpage.pages.explore.listener.ExplorePaneListener;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.EventObject;

public class ExplorePaneFXML
{
    private final ExplorePaneListener listener = new ExplorePaneListener();

    public Button randomTweetsButton;
    public Button searchButton;
    public TextField searchTextField;
    public Pane explorePane;

    public void setExplorePane(Pane pane)
    {
        this.explorePane.getChildren().clear();
        this.explorePane.getChildren().add(pane);
    }

    public void randomTweets()
    {
        listener.eventOccurred(new EventObject(this.randomTweetsButton), "");
    }

    public void search()
    {
        listener.eventOccurred(new EventObject(this.searchButton), this.searchTextField.getText());
    }
}
