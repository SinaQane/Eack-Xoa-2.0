package listener.pages.settings;

import controller.mainpage.MainPageController;
import controller.pages.settings.SettingsLogic;
import view.pages.settings.SettingsPane;
import view.pages.settings.SettingsPaneFXML;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
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
        MainPageFXML mainPageFXML = MainPage.getMainPage().getLoader().getController();

        SettingsLogic logic = new SettingsLogic(eventObject);
        SettingsPaneFXML settingsPaneFXML = settingsPane.getLoader().getController();

        Button button = new Button();
        button.setId("logoutButton");

        switch (((Button) eventObject.getSource()).getId())
        {
            case "editButton":
                if (!logic.check().equals("Valid"))
                {
                    settingsPaneFXML.getMessageText().setText(logic.check());
                    settingsPaneFXML.getMessageText().setFill(Color.RED);
                    settingsPaneFXML.getMessageText().setVisible(true);
                }
                else
                {
                    settingsPaneFXML.getMessageText().setText("Edit successful!");
                    settingsPaneFXML.getMessageText().setFill(Color.GREEN);
                    settingsPaneFXML.getMessageText().setVisible(true);
                    logic.edit();
                }
                break;
            case "deactivationButton":
                logic.deactivate();
                mainPageFXML.getListener().eventOccurred(button);
                MainPageController.getMainPageController().setUser(null);
                break;
            case "deleteAccountButton":
                logic.deleteAccount();
                mainPageFXML.getListener().eventOccurred(button);
                MainPageController.getMainPageController().setUser(null);
                break;
        }
    }
}
