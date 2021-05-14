package listener.pages.settings;

import controller.pages.settings.SettingsLogic;
import view.pages.settings.SettingsPane;
import view.pages.settings.SettingsPaneFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class SettingsListener
{
    private final SettingsPane settingsPane;

    public SettingsListener(SettingsPane settingsPane)
    {
        this.settingsPane = settingsPane;
    }

    public void eventOccurred(EditFormEvent eventObject)
    {
        MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();

        SettingsLogic settingsLogic = new SettingsLogic(eventObject);
        FXMLLoader settingsPaneLoader = settingsPane.getLoader();
        SettingsPaneFXML settingsPaneController = settingsPaneLoader.getController();
        Button button = new Button();
        button.setId("logoutButton");
        switch (((Button) eventObject.getSource()).getId())
        {
            case "editButton":
                if (!settingsLogic.check().equals("Valid"))
                {
                    settingsPaneController.getMessageText().setText(settingsLogic.check());
                    settingsPaneController.getMessageText().setFill(Color.RED);
                    settingsPaneController.getMessageText().setVisible(true);
                }
                else
                {
                    settingsPaneController.getMessageText().setText("Edit successful!");
                    settingsPaneController.getMessageText().setFill(Color.GREEN);
                    settingsPaneController.getMessageText().setVisible(true);
                    settingsLogic.edit();
                }
                break;
            case "deactivationButton":
                settingsLogic.deactivate();
                mainPageController.getListener().eventOccurred(button);
                break;
            case "deleteAccountButton":
                settingsLogic.deleteAccount();
                mainPageController.getListener().eventOccurred(button);
                break;
        }
    }
}
