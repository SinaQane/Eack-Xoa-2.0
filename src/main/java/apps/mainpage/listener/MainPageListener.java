package apps.mainpage.listener;

import apps.authentication.login.view.LoginPage;
import apps.mainpage.event.PageMemory;
import apps.mainpage.logic.BackButtonAgent;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import db.UserDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final MainPage mainPage;

    public MainPageListener(Stage stage)
    {
        this.stage = stage;
        this.loginPage = LoginPage.getLoginPage();
        this.mainPage = MainPage.getMainPage();
    }

    public void eventOccurred(Object source)
    {
        FXMLLoader fxmlLoader = mainPage.getLoader();
        MainPageFXML fxmlController = fxmlLoader.getController();
        switch (((Button) source).getId())
        {
            case "backButton":
                BackButtonAgent.getBackButtonAgent().back();
                break;
            case "logoutButton":
                UserDB.getUserDB().save(MainPageAgent.getMainPageAgent().getUser());
                stage.setScene(loginPage.getScene());
                break;
            case "profileButton":
                ProfilePane profilePane = PanesController.getPanesController().getProfilePane(0);
                fxmlController.setMainPane(profilePane.getProfilePane());
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("profile", MainPageAgent.getMainPageAgent().getUser().getId()));
                break;
            case "settingsButton":
                fxmlController.setMainPane(PanesController.getPanesController().getSettingsPane().getSettingsPane());
                break;
            case "homeButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("timeline", 0).getTimelinePane());
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("timeline"));
                break;
            case "bookmarksButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("bookmarks", 0).getTimelinePane());
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("bookmarks"));
                break;
            case "notificationsButton":
                fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane("notifications", MainPageAgent.getMainPageAgent().getUser().getId(), 0).getListPane());
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("notifications", MainPageAgent.getMainPageAgent().getUser().getId()));
                break;
            case "exploreButton":
                fxmlController.setMainPane(PanesController.getPanesController().getExplorePane().getPane());
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("explore"));
                break;
            default: // TODO direct messages
                System.out.println(((Button) source).getId());
                break;
        }
    }
}
