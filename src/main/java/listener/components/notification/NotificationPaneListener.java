package listener.components.notification;

import controller.components.notification.NotificationPaneLogic;
import controller.mainpage.MainPageController;
import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

import java.util.EventObject;

public class NotificationPaneListener
{
    public void eventOccurred(EventObject eventObject, long otherUser)
    {
        NotificationPaneLogic logicalAgent = new NotificationPaneLogic(otherUser);
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

        mainPageController.setMainPane(PanesController.getPanesController().getUserslistPane("notifications", MainPageController.getMainPageAgent().getUser().getId(), 0).getListPane());
    }
}
