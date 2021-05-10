package apps.mainpage.listener;

import apps.authentication.login.view.LoginPage;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
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
                ProfilePane profilePane = PanesController.getPanesController().getProfilePane(0);
                fxmlController.setMainPane(profilePane.getProfilePane());
                break;
            }
            case "settingsButton":
            {
                fxmlController.setMainPane(PanesController.getPanesController().getSettingsPane().getSettingsPane());
                break;
            }
            case "homeButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("timeline", 0).getTimelinePane());
                break;
            case "bookmarksButton":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane("bookmarks", 0).getTimelinePane());
                break;
            default:
                System.out.println(((Button) source).getId());
                break;
        }
    }
}
