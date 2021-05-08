package apps.mainpage.pages.profile.view;

import apps.mainpage.pages.profile.listener.ProfileListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProfilePaneFXML
{
    private ProfileListener listener;

    public ImageView profilePicture;
    public Text nameText;
    public Text usernameText;
    public Text bioText;
    public Text emailText;
    public Text birthdateText;
    public Text phoneNumberText;
    public Pane tweetsPane;

    public void setListener(ProfileListener profileListener)
    {
        this.listener = profileListener;
    }

    public ProfileListener getListener()
    {
        return listener;
    }

    public void setTweetsPane(Pane tweetsPane)
    {
        this.tweetsPane.getChildren().clear();
        this.tweetsPane.getChildren().add(tweetsPane);
    }

    public void setNameText(String nameText)
    {
        this.nameText.setText(nameText);
    }

    public void setUsernameText(String usernameText)
    {
        this.usernameText.setText(usernameText);
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
}
