package listener.pages.timeline;

import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

public class TimelineListener
{
    public void eventOccurred(TimelineEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        int page = eventObject.getPage();
        String pageKind = eventObject.getPageKind();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "previousButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane(pageKind, page - 1).getTimelinePane());
                break;
            case "nextButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane(pageKind, page + 1).getTimelinePane());
                break;
            case "refreshButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane(pageKind, page).getTimelinePane());
                break;
        }
    }
}
