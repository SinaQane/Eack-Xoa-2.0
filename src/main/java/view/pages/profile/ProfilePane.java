package view.pages.profile;

import view.components.empty.emptytweet.EmptyTweetPane;
import view.components.tweet.TweetPane;
import view.components.tweet.TweetPaneFXML;
import listener.components.tweet.TweetPaneListener;
import controller.mainpage.MainPageController;
import controller.pages.profile.ProfileLogic;
import javafx.scene.image.Image;
import model.Profile;
import model.User;
import db.UserDB;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import util.Config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ProfilePane
{
    private static final String PROFILE_VIEWUSER = Config.getConfig("paths").getProperty(String.class, "profile");
    private static final String PROFILE_PIC = Config.getConfig("paths").getProperty(String.class, "profilePic");
    private static final String DATE_PATTERN = Config.getConfig("patterns").getProperty(String.class, "tinyDate");
    private static final String LIGHT_RED = Config.getConfig("colors").getProperty(String.class, "lightRed");
    private static final String DARK_RED = Config.getConfig("colors").getProperty(String.class, "darkRed");
    private static final String YELLOW = Config.getConfig("colors").getProperty(String.class, "yellow");
    private static final String GREEN = Config.getConfig("colors").getProperty(String.class, "green");

    private Pane profilePane;
    private final FXMLLoader loader;
    private final User user;

    public ProfilePane(User user)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(PROFILE_VIEWUSER)));
        try
        {
            profilePane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.user = user;
        ((ProfilePaneFXML) this.loader.getController()).setUser(user);
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
        boolean profile = this.user.getId().equals(MainPageController.getMainPageAgent().getUser().getId());

        TweetsPane tweetsPane = new TweetsPane();
        FXMLLoader fxmlLoader = tweetsPane.getLoader();
        TweetsPaneFXML tweetsPaneFXML = fxmlLoader.getController();

        ProfilePaneFXML fxmlController = this.loader.getController();
        tweetsPaneFXML.setListener(fxmlController.getProfileListener());
        tweetsPaneFXML.setUser(user);
        tweetsPaneFXML.setPage(page);

        ProfileLogic profileLogic = new ProfileLogic(UserDB.getUserDB().get(this.user.getId()));

        tweetsPaneFXML.getPreviousButton().setDisable(!profileLogic.hasPreviousPage(page));
        tweetsPaneFXML.getNextButton().setDisable(!profileLogic.hasNextPage(page));

        if (profileLogic.getNumberOfPages() == 0)
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
            firstTweetFXML.setTweetPane(profileLogic.getPage(page).get(0));
            tweetsPaneFXML.setFirstTweetPane(firstTweetPane.getTweetPane());

            if (!profileLogic.getPage(page).get(1)[0].equals("null"))
            {
                TweetPane secondTweetPane = new TweetPane();
                TweetPaneFXML secondTweetFXML = secondTweetPane.getLoader().getController();
                secondTweetFXML.setListener(new TweetPaneListener(secondTweetPane));
                secondTweetFXML.setTweetPane(profileLogic.getPage(page).get(1));
                tweetsPaneFXML.setSecondTweetPane(secondTweetPane.getTweetPane());
            }
            else
            {
                tweetsPaneFXML.setSecondTweetPane(new EmptyTweetPane().getTweetPane());
            }
        }

        fxmlController.setNameText(this.user.getName());
        fxmlController.setUsernameText("@" + this.user.getUsername());
        fxmlController.setStatsText("Followers: " + this.user.getProfile().getFollowers().size() + " - " +
                "Followings: " + this.user.getProfile().getFollowings().size());
        fxmlController.setBioText(this.user.getBio());
        fxmlController.setEmailText("Email: " + this.user.getEmail());
        if (this.user.getBirthDate().getTime() == -12600000) // (1970-01-01).getTime();
        {
            fxmlController.setBirthdateText("Birthdate: N/A");
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            fxmlController.setBirthdateText("Birthdate: " + dateFormat.format(this.user.getBirthDate()));
        }
        if (this.user.getPhoneNumber().equals(""))
        {
            fxmlController.setPhoneNumberText("Phone Number: N/A");
        }
        else
        {
            fxmlController.setPhoneNumberText("Phone Number: " + this.user.getPhoneNumber());
        }

        boolean correctPath;
        if (user.getProfile().getPicturePath().equals(""))
        {
            correctPath = false;
        }
        else
        {
            File file = new File(user.getProfile().getPicturePath());
            correctPath = file.exists();
        }

        String imagePath;
        if (correctPath)
        {
            imagePath = user.getProfile().getPicturePath();
        }
        else
        {
            imagePath = PROFILE_PIC;
        }

        Image image = null;
        try
        {
            image = new Image(new File(imagePath).toURI().toURL().toExternalForm(), 115, 115, false, false);
        } catch (MalformedURLException ignored) {}

        fxmlController.getProfilePicture().setImage(image);

        if (profile)
        {
            fxmlController.getViewFollowersButton().setVisible(true);
            fxmlController.getViewFollowingsButton().setVisible(true);
            fxmlController.getViewBlacklistButton().setVisible(true);

            fxmlController.getStatButton().setVisible(false);
            fxmlController.getBlockButton().setVisible(false);
            fxmlController.getMuteButton().setVisible(false);

            fxmlController.setTweetsPane(tweetsPane.getTweetsPane());
        }
        else
        {
            fxmlController.getStatButton().setVisible(true);

            Profile ourUser = MainPageController.getMainPageAgent().getUser().getProfile();

            if (ourUser.getFollowings().contains(user.getId()))
            {
                fxmlController.getStatButton().setText("Following");
                fxmlController.getStatButton().setTextFill(Paint.valueOf(GREEN));
            }
            else if (ourUser.getPending().contains(user.getId()))
            {
                fxmlController.getStatButton().setText("Pending");
                fxmlController.getStatButton().setTextFill(Paint.valueOf(YELLOW));
            }
            else if (ourUser.getBlocked().contains(user.getId()))
            {
                fxmlController.getStatButton().setText("Blocked");
                fxmlController.getStatButton().setTextFill(Paint.valueOf(DARK_RED));
            }
            else
            {
                fxmlController.getStatButton().setText("Not Following");
                fxmlController.getStatButton().setTextFill(Paint.valueOf(LIGHT_RED));
            }

            fxmlController.getViewBlacklistButton().setVisible(false);

            fxmlController.getViewFollowersButton().setVisible(true);
            fxmlController.getViewFollowingsButton().setVisible(true);
            fxmlController.getBlockButton().setVisible(true);
            fxmlController.getMuteButton().setVisible(true);
        }
    }
}
