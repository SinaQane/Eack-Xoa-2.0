package listener.pages.explore;

import controller.mainpage.PanesController;
import view.pages.explore.ExplorePane;
import view.pages.explore.ExplorePaneFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.EventObject;

public class ExploreListener
{
    public void eventOccurred(EventObject eventObject, String searched)
    {
        if (((Button) eventObject.getSource()).getId().equals("randomTweetsButton"))
        {
            Pane pane = PanesController.getPanesController().getExplorePane().getPane();
            ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(pane);
        }
        else if (((Button) eventObject.getSource()).getId().equals("searchButton"))
        {
            Pane pane = PanesController.getPanesController().getSearchResultsPane(searched, 0).getPane();
            ExplorePane explorePane = PanesController.getPanesController().getExplorePane();
            ((ExplorePaneFXML) explorePane.getLoader().getController()).setExplorePane(pane);
            ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(explorePane.getPane());
        }
    }
}
