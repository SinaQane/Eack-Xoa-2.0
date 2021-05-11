package apps.mainpage.pages.explore.listener;

import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.explore.view.ExplorePane;
import apps.mainpage.pages.explore.view.ExplorePaneFXML;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.EventObject;

public class SearchResultsPaneListener
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
