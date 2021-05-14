package listener.pages.tweetslist;

import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

public class TweetsListListener
{
    public void eventOccurred(TweetsListEvent eventObject)
    {
        MainPageFXML mainPageFXML = MainPage.getMainPage().getLoader().getController();

        String id = eventObject.getTweetsListForm().getTweet().getId();
        int page = eventObject.getTweetsListForm().getPage();

        if (((Button) eventObject.getSource()).getId().equals("previousButton"))
        {
            mainPageFXML.setMainPane(PanesController.getPanesController().getTweetsListPane(id, page - 1).getPane());
        }
        else if (((Button) eventObject.getSource()).getId().equals("nextButton"))
        {
            mainPageFXML.setMainPane(PanesController.getPanesController().getTweetsListPane(id, page + 1).getPane());
        }
    }
}
