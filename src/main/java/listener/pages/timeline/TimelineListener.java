package listener.pages.timeline;

import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

public class TimelineListener
{
    public void eventOccurred(TimelineEvent eventObject)
    {
        MainPageFXML mainPageFXML = MainPage.getMainPage().getLoader().getController();

        int page = eventObject.getPage();
        String pageKind = eventObject.getPageKind();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "previousButton":
                mainPageFXML.setMainPane(PanesController.getPanesController().getTimelinePane(pageKind, page - 1).getPane());
                break;
            case "nextButton":
                mainPageFXML.setMainPane(PanesController.getPanesController().getTimelinePane(pageKind, page + 1).getPane());
                break;
            case "refreshButton":
                mainPageFXML.setMainPane(PanesController.getPanesController().getTimelinePane(pageKind, page).getPane());
                break;
        }
    }
}
