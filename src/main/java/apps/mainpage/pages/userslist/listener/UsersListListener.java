package apps.mainpage.pages.userslist.listener;

import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.userslist.event.UsersListEvent;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;

public class UsersListListener
{
    public void eventOccurred(UsersListEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        long id = eventObject.getPageEvent().getUser().getId();
        int page = eventObject.getPageEvent().getPage();
        String pageKind = eventObject.getPageEvent().getPageKind();

        if (((Button) eventObject.getSource()).getId().equals("previousButton"))
        {
            fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane(pageKind, id, page - 1).getListPane());
        }
        else if (((Button) eventObject.getSource()).getId().equals("nextButton"))
        {
            fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane(pageKind, id, page + 1).getListPane());
        }
    }
}
