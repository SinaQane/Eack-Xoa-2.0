package listener.pages.userslist;

import controller.mainpage.PanesController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;

public class UsersListListener
{
    public void eventOccurred(UsersListEvent eventObject)
    {
        MainPageFXML mainPageFXML = MainPage.getMainPage().getLoader().getController();

        long id = eventObject.getUsersListForm().getUser().getId();
        int page = eventObject.getUsersListForm().getPage();
        String pageKind = eventObject.getUsersListForm().getPageKind();

        if (((Button) eventObject.getSource()).getId().equals("previousButton"))
        {
            mainPageFXML.setMainPane(PanesController.getPanesController().getUserslistPane(pageKind, id, page - 1).getPane());
        }
        else if (((Button) eventObject.getSource()).getId().equals("nextButton"))
        {
            mainPageFXML.setMainPane(PanesController.getPanesController().getUserslistPane(pageKind, id, page + 1).getPane());
        }
    }
}
