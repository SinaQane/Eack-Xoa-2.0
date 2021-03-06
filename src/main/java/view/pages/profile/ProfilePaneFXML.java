package view.pages.profile;

import controller.mainpage.MainPageController;
import listener.pages.profile.ViewUserEvent;
import listener.pages.profile.ViewUserListener;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.User;

public class ProfilePaneFXML
{
    private final ViewUserListener listener = new ViewUserListener();

    private final User ourUser = MainPageController.getMainPageController().getUser();
    private User otherUser;

    public ImageView profilePicture;
    public Text nameText;
    public Text usernameText;
    public Text statsText;
    public Text bioText;
    public Text emailText;
    public Text birthdateText;
    public Text lastSeenText;
    public Text phoneNumberText;

    public Pane tweetsPane;

    public Button statButton;
    public Button profilePicButton;
    public Button viewFollowersButton;
    public Button viewFollowingsButton;
    public Button viewBlacklistButton;
    public Button blockButton;
    public Button muteButton;

    public void setUser(User user)
    {
        this.otherUser = user;
    }

    public void setTweetsPane(Pane tweetsPane)
    {
        this.tweetsPane.getChildren().clear();
        this.tweetsPane.getChildren().add(tweetsPane);
    }

    public ImageView getProfilePicture()
    {
        return profilePicture;
    }

    public void setNameText(String nameText)
    {
        this.nameText.setText(nameText);
    }

    public void setUsernameText(String usernameText)
    {
        this.usernameText.setText(usernameText);
    }

    public void setStatsText(String statsText)
    {
        this.statsText.setText(statsText);
    }

    public void setBioText(String bioText)
    {
        this.bioText.setText(bioText);
    }

    public void setEmailText(String emailText)
    {
        this.emailText.setText(emailText);
    }

    public void setBirthdateText(String birthdateText)
    {
        this.birthdateText.setText(birthdateText);
    }

    public void setLastSeenText(String lastSeenText)
    {
        this.lastSeenText.setText(lastSeenText);
    }

    public void setPhoneNumberText(String phoneNumberText)
    {
        this.phoneNumberText.setText(phoneNumberText);
    }

    public Button getStatButton()
    {
        return statButton;
    }

    public Button getViewFollowersButton()
    {
        return viewFollowersButton;
    }

    public Button getViewFollowingsButton()
    {
        return viewFollowingsButton;
    }

    public Button getViewBlacklistButton()
    {
        return viewBlacklistButton;
    }

    public Button getBlockButton()
    {
        return blockButton;
    }

    public Button getMuteButton()
    {
        return muteButton;
    }

    public void changeStatus()
    {
        listener.eventOccurred(new ViewUserEvent(this.statButton, this.ourUser, this.otherUser));
    }

    public void profilePic()
    {
        listener.eventOccurred(new ViewUserEvent(this.profilePicButton, this.ourUser, this.otherUser));
    }

    public void block()
    {
        listener.eventOccurred(new ViewUserEvent(this.blockButton, this.ourUser, this.otherUser));
    }

    public void followers()
    {
        listener.eventOccurred(new ViewUserEvent(this.viewFollowersButton, this.ourUser, this.otherUser));
    }

    public void followings()
    {
        listener.eventOccurred(new ViewUserEvent(this.viewFollowingsButton, this.ourUser, this.otherUser));
    }

    public void blacklist()
    {
        listener.eventOccurred(new ViewUserEvent(this.viewBlacklistButton, this.ourUser, this.otherUser));
    }

    public void mute()
    {
        listener.eventOccurred(new ViewUserEvent(this.muteButton, this.ourUser, this.otherUser));
    }
}
