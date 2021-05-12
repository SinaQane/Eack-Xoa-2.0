package apps.components.userpane.listener;

import apps.mainpage.event.PageMemory;
import apps.mainpage.logic.BackButtonAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

import java.util.EventObject;

public class UserPaneListener
{
    public void eventOccurred(EventObject eventObject, long id)
    {
        if (((Button) eventObject.getSource()).getId().equals("viewUserButton"))
        {
            MainPageFXML controller = MainPage.getMainPage().getLoader().getController();
            controller.setMainPane(PanesController.getPanesController().getProfilePane(id, 0).getProfilePane());
            BackButtonAgent.getBackButtonAgent().add(new PageMemory("user", id));

        }
    }
}
