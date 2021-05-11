package apps.mainpage.pages.explore.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class ExplorePane
{
    private Pane explorePane;
    private final FXMLLoader loader;

    public ExplorePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/explore/Explore.fxml")));
        try
        {
            explorePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.explorePane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh()
    {
        ExplorePaneFXML fxmlController = this.loader.getController();
        RandomTweetsPane randomTweetsPane = new RandomTweetsPane();
        randomTweetsPane.refresh();
        fxmlController.setExplorePane(randomTweetsPane.getPane());
    }
}
