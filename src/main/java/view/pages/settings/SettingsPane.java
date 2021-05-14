package view.pages.settings;

import listener.pages.settings.SettingsListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class SettingsPane
{
    private static final String SETTINGS = Config.getConfig("paths").getProperty(String.class, "settings");

    private Pane pane;
    private final FXMLLoader loader;

    public SettingsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SETTINGS)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return pane;
    }

    public FXMLLoader getLoader()
    {
        return loader;
    }

    public void refresh()
    {
        SettingsPaneFXML settingsPaneFXML = this.loader.getController();
        settingsPaneFXML.setListener(new SettingsListener(this));
        settingsPaneFXML.getMessageText().setVisible(false);
    }
}
