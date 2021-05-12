package apps.mainpage.pages.settings.listener;

import apps.mainpage.pages.settings.event.EditFormEvent;
import apps.mainpage.pages.settings.logic.EditAgent;
import apps.mainpage.pages.settings.view.SettingsPane;
import apps.mainpage.pages.settings.view.SettingsPaneFXML;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class EditFormListener
{
    private final SettingsPane settingsPane;

    public EditFormListener(SettingsPane settingsPane)
    {
        this.settingsPane = settingsPane;
    }

    public void eventOccurred(EditFormEvent eventObject)
    {
        MainPageFXML mainPageController = MainPage.getMainPage().getLoader().getController();

        EditAgent editAgent = new EditAgent(eventObject);
        FXMLLoader settingsPaneLoader = settingsPane.getLoader();
        SettingsPaneFXML settingsPaneController = settingsPaneLoader.getController();
        switch (((Button) eventObject.getSource()).getId())
        {
            case "editButton":
                if (!editAgent.check().equals("Valid"))
                {
                    settingsPaneController.getMessageText().setText(editAgent.check());
                    settingsPaneController.getMessageText().setFill(Color.RED);
                    settingsPaneController.getMessageText().setVisible(true);
                }
                else
                {
                    settingsPaneController.getMessageText().setText("Edit successful!");
                    settingsPaneController.getMessageText().setFill(Color.GREEN);
                    settingsPaneController.getMessageText().setVisible(true);
                    editAgent.edit();
                }
                break;
            case "deactivationButton":
                editAgent.deactivate();
                mainPageController.getListener().eventOccurred("logoutButton");
                break;
            case "deleteAccountButton":
                editAgent.deleteAccount();
                mainPageController.getListener().eventOccurred("logoutButton");
                break;
        }
    }
}
