package listener.pages.tweetslist;

import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

public class TweetsListListener
{
    public void eventOccurred(TweetsListEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        String id = eventObject.getTweetsListForm().getTweet().getId();
        int page = eventObject.getTweetsListForm().getPage();

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
