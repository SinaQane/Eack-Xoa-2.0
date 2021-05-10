package apps.mainpage.pages.settings.view;

import apps.mainpage.pages.settings.listener.EditFormListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SettingsPane
{
    private Pane settingsPane;
    private final FXMLLoader loader;

    public SettingsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/settings/Settings.fxml")));
        try
        {
            settingsPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getSettingsPane()
    {
        return settingsPane;
    }

    public FXMLLoader getLoader()
    {
        return loader;
    }

    public void refresh()
    {
        SettingsPaneFXML fxmlController = this.loader.getController();
        fxmlController.setListener(new EditFormListener(this));
        fxmlController.getMessageText().setVisible(false);
    }
}
