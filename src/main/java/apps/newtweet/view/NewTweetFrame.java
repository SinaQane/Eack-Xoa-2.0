package apps.newtweet.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewTweetFrame
{
    private static final String NEW_TWEET = "graphic/newtweet/NewTweetFrame.fxml"; // TODO config

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
