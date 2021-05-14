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
        NotificationPaneLogic logic = new NotificationPaneLogic(otherUser);
        MainPageFXML mainPageFXML = MainPage.getMainPage().getLoader().getController();

        switch (((Button) eventObject.getSource()).getId())
        {
            case "acceptButton":
                logic.accept();
                break;
            case "goodRejectButton":
                logic.goodReject();
                break;
            case "badRejectButton":
                logic.badReject();
                break;
        }

        mainPageFXML.setMainPane(PanesController.getPanesController().getUserslistPane("notifications", MainPageController.getMainPageController().getUser().getId(), 0).getPane());
    }
}
