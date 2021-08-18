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
import config.Config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ProfilePane
{
    private static final Long DEFAULT_DATETIME = Config.getConfig("patterns").getProperty(Long.class, "defaultDateTime");
    private static final String PROFILE_VIEWUSER = Config.getConfig("paths").getProperty(String.class, "profile");
    private static final String PROFILE_PIC = Config.getConfig("paths").getProperty(String.class, "profilePic");
    private static final String DATE_PATTERN = Config.getConfig("patterns").getProperty(String.class, "tinyDate");
    private static final String DATETIME_PATTERN = Config.getConfig("patterns").getProperty(String.class, "longDate");
    private static final String LIGHT_RED = Config.getConfig("colors").getProperty(String.class, "lightRed");
    private static final String DARK_RED = Config.getConfig("colors").getProperty(String.class, "darkRed");
    private static final String YELLOW = Config.getConfig("colors").getProperty(String.class, "yellow");
    private static final String GREEN = Config.getConfig("colors").getProperty(String.class, "green");

    private Pane pane;
    private final FXMLLoader loader;

    private final User user;

    public ProfilePane(User user)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(PROFILE_VIEWUSER)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.user = user;
        ((ProfilePaneFXML) this.loader.getController()).setUser(user);
    }

    public Pane getPane()
    {
        return this.pane;
    }

    public void refresh(int page)
    {
        boolean profile = this.user.getId().equals(MainPageController.getMainPageController().getUser().getId());

        ProfilePaneFXML profilePaneFXML = this.loader.getController();

        if (this.user.getProfile().getBlocked().contains(MainPageController.getMainPageController().getUser().getId()))
        {
            profilePaneFXML.setTweetsPane(new BlockedPane().getPane());
        }
        else if (!profile && (this.user.getProfile().isPrivate() && !this.user.getProfile().getFollowers().contains(MainPageController.getMainPageController().getUser().getId())))
        {
            profilePaneFXML.setTweetsPane(new PrivatePane().getPane());
        }
        else
        {
            TweetsPane tweetsPane = new TweetsPane();
            FXMLLoader fxmlLoader = tweetsPane.getLoader();
            TweetsPaneFXML tweetsPaneFXML = fxmlLoader.getController();

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
                tweetsPaneFXML.setFirstTweetPane(firstTweetPane.getPane());

                if (!profileLogic.getPage(page).get(1)[0].equals("null"))
                {
                    TweetPane secondTweetPane = new TweetPane();
                    TweetPaneFXML secondTweetFXML = secondTweetPane.getLoader().getController();
                    secondTweetFXML.setListener(new TweetPaneListener(secondTweetPane));
                    secondTweetFXML.setTweetPane(profileLogic.getPage(page).get(1));
                    tweetsPaneFXML.setSecondTweetPane(secondTweetPane.getPane());
                }
                else
                {
                    tweetsPaneFXML.setSecondTweetPane(new EmptyTweetPane().getTweetPane());
                }
            }
            profilePaneFXML.setTweetsPane(tweetsPane.getPane());
        }

        profilePaneFXML.setNameText(this.user.getName());
        profilePaneFXML.setUsernameText("@" + this.user.getUsername());
        profilePaneFXML.setStatsText("Followers: " + this.user.getProfile().getFollowers().size() + " - " +
                "Followings: " + this.user.getProfile().getFollowings().size());
        profilePaneFXML.setBioText(this.user.getBio());

        if (!this.user.getProfile().getInfoState() && !this.user.getId().equals(MainPageController.getMainPageController().getUser().getId()))
        {
            profilePaneFXML.setEmailText("Email: N/A");
        }
        else
        {
            profilePaneFXML.setEmailText("Email: " + this.user.getEmail());
        }

        if (this.user.getBirthDate().getTime() == DEFAULT_DATETIME  || (!this.user.getProfile().getInfoState() && !this.user.getId().equals(MainPageController.getMainPageController().getUser().getId())))
        {
            profilePaneFXML.setBirthdateText("Birthdate: N/A");
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            profilePaneFXML.setBirthdateText("Birthdate: " + dateFormat.format(this.user.getBirthDate()));
        }

        if (this.user.getPhoneNumber().equals("") || (!this.user.getProfile().getInfoState() && !this.user.getId().equals(MainPageController.getMainPageController().getUser().getId())))
        {
            profilePaneFXML.setPhoneNumberText("Phone Number: N/A");
        }
        else
        {
            profilePaneFXML.setPhoneNumberText("Phone Number: " + this.user.getPhoneNumber());
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat(DATETIME_PATTERN);
        if (this.user.getId().equals(MainPageController.getMainPageController().getUser().getId()))
        {
            profilePaneFXML.setLastSeenText("Last Seen: " + timeFormat.format(new Date(this.user.getProfile().getLastSeen())));
        }
        else
        {
            switch (this.user.getProfile().getLastSeenState())
            {
                case 0:
                    profilePaneFXML.setLastSeenText("Last Seen: N/A");
                    break;
                case 1:
                    if (this.user.getProfile().getFollowings().contains(MainPageController.getMainPageController().getUser().getId()))
                    {
                        profilePaneFXML.setLastSeenText("Last Seen: " + timeFormat.format(new Date(this.user.getProfile().getLastSeen())));
                    }
                    else
                    {
                        profilePaneFXML.setLastSeenText("Last Seen: N/A");
                    }
                    break;
                case 2:
                    profilePaneFXML.setLastSeenText("Last Seen: " + timeFormat.format(new Date(this.user.getProfile().getLastSeen())));
                    break;
            }
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

        profilePaneFXML.getProfilePicture().setImage(image);

        if (profile)
        {
            profilePaneFXML.getViewFollowersButton().setVisible(true);
            profilePaneFXML.getViewFollowingsButton().setVisible(true);
            profilePaneFXML.getViewBlacklistButton().setVisible(true);

            profilePaneFXML.getStatButton().setVisible(false);
            profilePaneFXML.getBlockButton().setVisible(false);
            profilePaneFXML.getMuteButton().setVisible(false);
        }
        else
        {
            profilePaneFXML.getStatButton().setVisible(true);

            Profile ourUser = MainPageController.getMainPageController().getUser().getProfile();

            if (ourUser.getFollowings().contains(user.getId()))
            {
                profilePaneFXML.getStatButton().setText("Following");
                profilePaneFXML.getStatButton().setTextFill(Paint.valueOf(GREEN));
            }
            else if (ourUser.getPending().contains(user.getId()))
            {
                profilePaneFXML.getStatButton().setText("Pending");
                profilePaneFXML.getStatButton().setTextFill(Paint.valueOf(YELLOW));
            }
            else if (ourUser.getBlocked().contains(user.getId()))
            {
                profilePaneFXML.getStatButton().setText("Blocked");
                profilePaneFXML.getStatButton().setTextFill(Paint.valueOf(DARK_RED));
            }
            else
            {
                profilePaneFXML.getStatButton().setText("Not Following");
                profilePaneFXML.getStatButton().setTextFill(Paint.valueOf(LIGHT_RED));
            }

            profilePaneFXML.getViewBlacklistButton().setVisible(false);

            profilePaneFXML.getViewFollowersButton().setVisible(true);
            profilePaneFXML.getViewFollowingsButton().setVisible(true);
            profilePaneFXML.getBlockButton().setVisible(true);
            profilePaneFXML.getMuteButton().setVisible(true);
        }
    }
}
