package apps.mainpage.pages.viewtweet.listener;

import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.viewtweet.event.TweetsListEvent;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

public class TweetsListListener
{
    public void eventOccurred(TweetsListEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        String id = eventObject.getPageEvent().getTweet().getId();
        int page = eventObject.getPageEvent().getPage();

        if (((Button) eventObject.getSource()).getId().equals("previousButton"))
        {
            fxmlController.setMainPane(PanesController.getPanesController().getTweetsListPane(id, page - 1).getListPane());
        }
        else if (((Button) eventObject.getSource()).getId().equals("nextButton"))
        {
            fxmlController.setMainPane(PanesController.getPanesController().getTweetsListPane(id, page + 1).getListPane());
        }
    }
}
