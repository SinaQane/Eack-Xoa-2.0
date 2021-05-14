package listener.frames.managegroup;

import controller.frames.managegroup.ManageGroupFrameAgent;
import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

import java.util.List;

public class ManageGroupFrameListener
{
    public void eventOccurred(ManageGroupEvent eventObject)
    {
        ManageGroupFrameAgent logicalAgent = new ManageGroupFrameAgent();

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
