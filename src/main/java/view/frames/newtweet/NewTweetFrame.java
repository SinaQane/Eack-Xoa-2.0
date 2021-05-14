package view.frames.newtweet;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class NewTweetFrame
{
    private static final String NEW_TWEET = Config.getConfig("paths").getProperty(String.class, "newTweetFrame");

    private final FXMLLoader loader;

    public NewTweetFrame(String upperTweet)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(NEW_TWEET)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            if (upperTweet.equals(""))
            {
                stage.setTitle("New Tweet");
            }
            else
            {
                stage.setTitle("Add Comment");
            }
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public FXMLLoader getLoader()
    {
        return loader;
    }
}
