package apps.mainpage.listener;

import apps.authentication.login.view.LoginPage;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.profile.listener.ProfileListener;
import apps.mainpage.pages.profile.view.ProfilePaneFXML;
import apps.mainpage.pages.settings.listener.EditFormListener;
import apps.mainpage.pages.settings.view.SettingsPaneFXML;
import apps.mainpage.pages.timeline_bookmarks.listener.TimelineListener;
import apps.mainpage.pages.timeline_bookmarks.view.TimelinePaneFXML;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
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
        MainPageAgent.getMainPageAgent().refresh();
        FXMLLoader fxmlLoader = mainPage.getLoader();
        MainPageFXML fxmlController = fxmlLoader.getController();
        switch (((Button) source).getId())
        {
            case "logoutButton":
                // TODO save and set lastSeen and close the app...
                stage.setScene(loginPage.getScene());
                break;
            case "profileButton":
            {
                FXMLLoader profilePaneLoader = PanesController.getPanesController().getProfilePane(0).getLoader();
                ProfilePaneFXML profilePaneController = profilePaneLoader.getController();
                profilePaneController.setListener(new ProfileListener(PanesController.getPanesController().getProfilePane(0)));
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(0).getProfilePane());
                break;
            }
            case "settingsButton":
            {
                FXMLLoader settingsPaneLoader = PanesController.getPanesController().getSettingsPane().getLoader();
                SettingsPaneFXML settingsPaneController = settingsPaneLoader.getController();
                settingsPaneController.setListener(new EditFormListener(PanesController.getPanesController().getSettingsPane()));
                fxmlController.setMainPane(PanesController.getPanesController().getSettingsPane().getSettingsPane());
                break;
            }
            case "homeButton":
                FXMLLoader timelinePaneLoader = PanesController.getPanesController().getTimelinePane("timeline", 0).getLoader();
                TimelinePaneFXML timelinePaneController = timelinePaneLoader.getController();
                timelinePaneController.setListener(new TimelineListener());
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("timeline", 0).getTimelinePane());
                break;
            case "bookmarksButton":
                FXMLLoader bookmarksPaneLoader = PanesController.getPanesController().getTimelinePane("bookmarks", 0).getLoader();
                TimelinePaneFXML bookmarksPaneController = bookmarksPaneLoader.getController();
                bookmarksPaneController.setListener(new TimelineListener());
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("bookmarks", 0).getTimelinePane());
                break;
            default:
                System.out.println("Not  added yet...");
                break;
        }
    }
}
