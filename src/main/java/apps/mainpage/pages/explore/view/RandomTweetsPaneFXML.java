package apps.mainpage.pages.explore.view;

import javafx.scene.layout.Pane;

public class RandomTweetsPaneFXML
{
    public Pane tweetPane1;
    public Pane tweetPane2;

    public void setTweetPane(int pane, Pane tweetPane)
    {
        if (pane == 0)
        {
            this.tweetPane1.getChildren().clear();
            this.tweetPane1.getChildren().add(tweetPane);
        }
        else if (pane == 1)
        {
            this.tweetPane2.getChildren().clear();
            this.tweetPane2.getChildren().add(tweetPane);
        }
    }
}
