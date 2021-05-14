package view.frames.sharetweet;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Tweet;
import config.Config;

import java.io.IOException;
import java.util.Objects;

public class ShareTweetFrame
{
    private static final String SHARE_TWEET = Config.getConfig("paths").getProperty(String.class, "shareTweetFrame");

    public ShareTweetFrame(Tweet tweet)
    {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(SHARE_TWEET)));
        try
        {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Share Tweet");
            stage.setScene(new Scene(root));
            stage.show();
            ((ShareTweetFrameFXML) loader.getController()).setTweet(tweet);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
