package apps.mainpage.pages.profile.view;

import apps.components.tweetpane.view.EmptyTweetPane;
import apps.components.tweetpane.view.TweetPane;
import apps.components.tweetpane.view.TweetPaneFXML;
import apps.mainpage.logic.MainPageAgent;
import apps.components.tweetpane.listener.TweetPaneListener;
import apps.mainpage.pages.profile.logic.ProfileAgent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import models.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ProfilePane
{
    private Pane profilePane;
    private final FXMLLoader loader;

    public ProfilePane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/profile/Profile.fxml")));
        try
        {
            profilePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getProfilePane()
    {
        return this.profilePane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {
        TweetsPane tweetsPane = new TweetsPane();
        FXMLLoader fxmlLoader = tweetsPane.getLoader();
        TweetsPaneFXML tweetsPaneFXML = fxmlLoader.getController();

        ProfilePaneFXML controller = this.loader.getController();
        tweetsPaneFXML.setListener(controller.getListener());
        tweetsPaneFXML.setPage(page);

        ProfileAgent profileAgent = new ProfileAgent(MainPageAgent.getMainPageAgent().getUser());

        tweetsPaneFXML.getPreviousButton().setDisable(!profileAgent.hasPreviousPage(page));
        tweetsPaneFXML.getNextButton().setDisable(!profileAgent.hasNextPage(page));

        if (profileAgent.getNumberOfPages() == 0)
        {
            tweetsPaneFXML.getMidLine().setVisible(false);
            tweetsPaneFXML.getNoTweetsText().setVisible(true);
            tweetsPaneFXML.setFirstTweetPane(new EmptyTweetPane().getTweetPane());
            tweetsPaneFXML.setSecondTweetPane(new EmptyTweetPane().getTweetPane());
        }
        else
        {
            tweetsPaneFXML.getMidLine().setVisible(true);
            tweetsPaneFXML.getNoTweetsText().setVisible(false);

            TweetPane firstTweetPane = new TweetPane();
            TweetPaneFXML firstTweetFXML = firstTweetPane.getLoader().getController();
            firstTweetFXML.setListener(new TweetPaneListener(firstTweetPane));
            firstTweetFXML.setTweetPane(profileAgent.getPage(page).get(0));
            tweetsPaneFXML.setFirstTweetPane(firstTweetPane.getTweetPane());

            if (!profileAgent.getPage(page).get(1)[0].equals("null"))
            {
                TweetPane secondTweetPane = new TweetPane();
                TweetPaneFXML secondTweetFXML = secondTweetPane.getLoader().getController();
                secondTweetFXML.setListener(new TweetPaneListener(secondTweetPane));
                secondTweetFXML.setTweetPane(profileAgent.getPage(page).get(1));
                tweetsPaneFXML.setSecondTweetPane(secondTweetPane.getTweetPane());
            }
            else
            {
                tweetsPaneFXML.setSecondTweetPane(new EmptyTweetPane().getTweetPane());
            }
        }

        ProfilePaneFXML fxmlController = this.loader.getController();
        User user = MainPageAgent.getMainPageAgent().getUser();
        fxmlController.setNameText(user.getName());
        fxmlController.setUsernameText("@" + user.getUsername());
        fxmlController.setBioText(user.getBio());
        fxmlController.setEmailText("Email: " + user.getEmail());
        if (user.getBirthDate().getTime() == -12600000) // (1970-01-01).getTime();
        {
            fxmlController.setBirthdateText("Birthdate: N/A");
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fxmlController.setBirthdateText("Birthdate: " + dateFormat.format(user.getBirthDate()));
        }
        if (user.getPhoneNumber().equals(""))
        {
            fxmlController.setPhoneNumberText("Phone Number: N/A");
        }
        else
        {
            fxmlController.setPhoneNumberText("Phone Number: " + user.getPhoneNumber());
        }

        // TODO set picture

        fxmlController.setTweetsPane(tweetsPane.getTweetsPane());
    }
}
