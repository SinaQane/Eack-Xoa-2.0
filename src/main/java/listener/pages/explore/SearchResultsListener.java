package listener.pages.explore;

import controller.mainpage.PanesController;
import view.pages.explore.ExplorePane;
import view.pages.explore.ExplorePaneFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.EventObject;

public class SearchResultsListener
{
    public void eventOccurred(EventObject eventObject, String searched, int page)
    {
        if (((Button) eventObject.getSource()).getId().equals("previousButton"))
        {
            Pane pane = PanesController.getPanesController().getSearchResultsPane(searched, page-1).getPane();
            ExplorePane explorePane = PanesController.getPanesController().getExplorePane();
            ((ExplorePaneFXML) explorePane.getLoader().getController()).setExplorePane(pane);
            ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(explorePane.getPane());
        }
        else if (((Button) eventObject.getSource()).getId().equals("nextButton"))
        {
            Pane pane = PanesController.getPanesController().getSearchResultsPane(searched, page+1).getPane();
            ExplorePane explorePane = PanesController.getPanesController().getExplorePane();
            ((ExplorePaneFXML) explorePane.getLoader().getController()).setExplorePane(pane);
            ((MainPageFXML) MainPage.getMainPage().getLoader().getController()).setMainPane(explorePane.getPane());
        }
    }
}
