package apps.mainpage.pages.timeline_bookmarks.listener;

import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.timeline_bookmarks.event.TimelineEvent;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
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
