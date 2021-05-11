package apps.components.notification.listener;

import apps.components.notification.logic.NotificationPaneAgent;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

import java.util.EventObject;

public class NotificationPaneListener
{
    public void eventOccurred(EventObject eventObject, long otherUser)
    {
        NotificationPaneAgent logicalAgent = new NotificationPaneAgent(otherUser);
        MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "acceptButton":
                logicalAgent.accept();
                break;
            case "goodRejectButton":
                logicalAgent.goodReject();
                break;
            case "badRejectButton":
                logicalAgent.badReject();
                break;
        }

        mainPageController.setMainPane(PanesController.getPanesController().getUserslistPane("notifications", MainPageAgent.getMainPageAgent().getUser().getId(), 0).getListPane());
    }
}
