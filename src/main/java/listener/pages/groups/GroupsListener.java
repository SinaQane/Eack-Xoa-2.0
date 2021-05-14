package listener.pages.groups;

import view.frames.managegroup.ManageGroupFrame;
import view.frames.managegroup.ManageGroupFrameFXML;
import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

import java.util.EventObject;

public class GroupsListener
{
    public void eventOccurred(EventObject eventObject, int page)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "newGroupButton":
                ManageGroupFrame manageGroupFrame = new ManageGroupFrame();
                ((ManageGroupFrameFXML) manageGroupFrame.getLoader().getController()).setGroup(null);
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
