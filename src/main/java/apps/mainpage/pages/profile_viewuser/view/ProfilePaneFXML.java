package apps.mainpage.pages.profile_viewuser.view;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.profile_viewuser.event.UserViewEvent;
import apps.mainpage.pages.profile_viewuser.listener.ProfileListener;
import apps.mainpage.pages.profile_viewuser.listener.UserViewListener;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.User;

public class ProfilePaneFXML
{
    private UserViewListener userViewListener = null;
    private ProfileListener profileListener = null;

    private final User ourUser = MainPageAgent.getMainPageAgent().getUser();
    private User otherUser;

    public ImageView profilePicture;
    public Text nameText;
    public Text usernameText;
    public Text statsText;
    public Text bioText;
    public Text emailText;
    public Text birthdateText;
    public Text phoneNumberText;

    public Pane tweetsPane;

    public Button statButton;
    public Button profilePicButton;
    public Button viewFollowersButton;
    public Button viewFollowingsButton;
    public Button viewBlacklistButton;
    public Button sendMessageButton;
    public Button blockButton;
    public Button muteButton;

    public void setUserViewListener(UserViewListener userViewListener)
    {
        this.userViewListener = userViewListener;
    }

    public void setProfileListener(ProfileListener profileListener)
    {
        this.profileListener = profileListener;
    }

    public ProfileListener getProfileListener()
    {
        return this.profileListener;
    }

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

    public Button getSendMessageButton()
    {
        return sendMessageButton;
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
        this.userViewListener.eventOccurred(new UserViewEvent(this.statButton, this.ourUser, this.otherUser));
    }

    public void profilePic()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.profilePicButton, this.ourUser, this.otherUser));
    }

    public void block()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.blockButton, this.ourUser, this.otherUser));
    }

    public void followers()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.viewFollowersButton, this.ourUser, this.otherUser));
    }

    public void followings()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.viewFollowingsButton, this.ourUser, this.otherUser));
    }

    public void blacklist()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.viewBlacklistButton, this.ourUser, this.otherUser));
    }

    public void direct()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.sendMessageButton, this.ourUser, this.otherUser));
    }

    public void mute()
    {
        this.userViewListener.eventOccurred(new UserViewEvent(this.muteButton, this.ourUser, this.otherUser));
    }
}
