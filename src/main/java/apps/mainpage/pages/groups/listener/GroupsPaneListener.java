package apps.mainpage.pages.groups.listener;

import apps.groupframe.view.GroupFrame;
import apps.groupframe.view.GroupFrameFXML;
import apps.mainpage.logic.PanesController;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

import java.util.EventObject;

public class GroupsPaneListener
{
    public void eventOccurred(EventObject eventObject, int page)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "newGroupButton":
                GroupFrame groupFrame = new GroupFrame();
                ((GroupFrameFXML) groupFrame.getLoader().getController()).setGroup(null);
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(0).getProfilePane());
                break;
            case "previousButton":
                fxmlController.setMainPane(PanesController.getPanesController().getGroupsPane(page - 1).getPane());
                break;
            case "nextButton":
                fxmlController.setMainPane(PanesController.getPanesController().getGroupsPane(page + 1).getPane());
                break;
        }
    }
}
