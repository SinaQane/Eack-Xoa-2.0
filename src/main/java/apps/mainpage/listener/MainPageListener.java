package apps.mainpage.listener;

import apps.authentication.login.view.LoginPage;
import apps.mainpage.logic.PanesController;
import apps.mainpage.pages.settings.listener.EditFormListener;
import apps.mainpage.pages.settings.view.SettingsPaneFXML;
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

    public MainPageListener(Stage stage, LoginPage loginPage, MainPage mainPage)
    {
        this.stage = stage;
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void eventOccurred(Object source)
    {
        switch (((Button) source).getId())
        {
            case "logoutButton":
                // TODO save and set lastSeen and close the app...
                stage.setScene(loginPage.getScene());
                break;
            case "profileButton":
            {
                FXMLLoader fxmlLoader = mainPage.getLoader();
                MainPageFXML fxmlController = fxmlLoader.getController();
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane().getProfilePane());

                // TODO set listener
                break;
            }
            case "settingsButton":
            {
                FXMLLoader fxmlLoader = mainPage.getLoader();
                MainPageFXML fxmlController = fxmlLoader.getController();
                fxmlController.setMainPane(PanesController.getPanesController().getSettingsPane().getSettingsPane());

                FXMLLoader settingsPaneLoader = PanesController.getPanesController().getSettingsPane().getLoader();
                SettingsPaneFXML settingsPaneController = settingsPaneLoader.getController();
                settingsPaneController.setListener(new EditFormListener(PanesController.getPanesController().getSettingsPane()));
                break;
            }
            default:
                System.out.println("Not  added yet...");
                break;
        }
    }
}
