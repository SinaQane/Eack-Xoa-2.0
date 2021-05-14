package listener.components.user;

import controller.mainpage.BackButtonMemory;
import controller.mainpage.BackButtonHandler;
import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
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
            BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("user", id));

        }
    }
}
