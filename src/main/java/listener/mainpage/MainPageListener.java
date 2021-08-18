package listener.mainpage;

import view.login.LoginPage;
import controller.mainpage.BackButtonMemory;
import controller.mainpage.BackButtonHandler;
import controller.mainpage.MainPageController;
import controller.mainpage.PanesController;
import view.pages.profile.ProfilePane;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import db.UserDB;
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
        MainPageFXML fxmlController = mainPage.getLoader().getController();
        switch (((Button) source).getId())
        {
            case "backButton":
                BackButtonHandler.getBackButtonHandler().back();
                break;
            case "logoutButton":
                UserDB.getUserDB().save(MainPageController.getMainPageController().getUser());
                MainPageController.getMainPageController().setUser(null);
                stage.setScene(loginPage.getScene());
                break;
            case "profileButton":
                ProfilePane profilePane = PanesController.getPanesController().getProfilePane(0);
                fxmlController.setMainPane(profilePane.getPane());
                BackButtonHandler.getBackButtonHandler().clear();
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("profile", MainPageController.getMainPageController().getUser().getId()));
                break;
            case "settingsButton":
                fxmlController.setMainPane(PanesController.getPanesController().getSettingsPane().getPane());
                break;
            case "homeButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("timeline", 0).getPane());
                BackButtonHandler.getBackButtonHandler().clear();
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("timeline"));
                break;
            case "bookmarksButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("bookmarks", 0).getPane());
                BackButtonHandler.getBackButtonHandler().clear();
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("bookmarks"));
                break;
            case "notificationsButton":
                fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane("notifications", MainPageController.getMainPageController().getUser().getId(), 0).getPane());
                BackButtonHandler.getBackButtonHandler().clear();
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("notifications", MainPageController.getMainPageController().getUser().getId()));
                break;
            case "exploreButton":
                fxmlController.setMainPane(PanesController.getPanesController().getExplorePane().getPane());
                BackButtonHandler.getBackButtonHandler().clear();
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("explore"));
                break;
            case "groupsButton":
                fxmlController.setMainPane(PanesController.getPanesController().getGroupsPane(0).getPane());
                break;
            case "messagesButton":
                fxmlController.setMainPane(PanesController.getPanesController().getMessagesPane().getPane());
                BackButtonHandler.getBackButtonHandler().clear();
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("messages"));
                break;
        }
    }
}
