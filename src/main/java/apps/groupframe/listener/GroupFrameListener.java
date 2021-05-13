package apps.groupframe.listener;

import apps.groupframe.event.GroupEvent;
import apps.groupframe.logic.GroupFrameAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

import java.util.List;

public class GroupFrameListener
{
    public void eventOccurred(GroupEvent eventObject)
    {
        GroupFrameAgent logicalAgent = new GroupFrameAgent();

        if (((Button) eventObject.getSource()).getId().equals("doneButton"))
        {
            String title = eventObject.getGroupForm().getTitle();
            List<String> toAdd = eventObject.getGroupForm().getToAdd();
            List<String> toRemove = eventObject.getGroupForm().getRoRemove();
            if (eventObject.getGroupForm().getGroup() == null)
            {
                logicalAgent.create(title, toAdd);
            }
            else
            {
                logicalAgent.edit(eventObject.getGroupForm().getGroup(), title, toAdd, toRemove);
            }
            MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();
            mainPageController.setMainPane(PanesController.getPanesController().getGroupsPane(0).getPane());
        }
    }
}
