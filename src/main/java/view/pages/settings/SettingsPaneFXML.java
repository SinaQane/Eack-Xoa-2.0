package view.pages.settings;

import controller.mainpage.MainPageController;
import listener.pages.settings.EditFormEvent;
import listener.pages.settings.SettingsListener;

import javafx.scene.control.*;
import javafx.scene.text.Text;
import config.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsPaneFXML
{
    private static final String DATE_PATTERN = Config.getConfig("patterns").getProperty(String.class, "tinyDate");
    private static final String DEFAULT_DATE = Config.getConfig("patterns").getProperty(String.class, "defaultDate");

    private SettingsListener listener;

    public Text messageText;

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public TextField nameTextField;
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public DatePicker birthDatePicker;
    public TextField bioTextField;
    public TextField picPathTextField;
    public ChoiceBox<String> privacyChoiceBox;
    public ChoiceBox<String> infoStateChoiceBox;
    public ChoiceBox<String> lastSeenChoiceBox;

    public Button deleteAccountButton;
    public Button deactivationButton;
    
    public CheckBox usernameCheckBox;
    public CheckBox passwordCheckBox;
    public CheckBox nameCheckBox;
    public CheckBox emailCheckBox;
    public CheckBox birthdateCheckBox;
    public CheckBox bioCheckBox;
    public CheckBox picPathCheckBox;
    public CheckBox privacyCheckBox;
    public CheckBox infoStateCheckBox;
    public CheckBox phoneNumberCheckBox;
    public CheckBox lastSeenCheckBox;

    public Button editButton;

    public void usernameCheckBox()
    {
        usernameTextField.setDisable(!usernameCheckBox.isSelected());
    }

    public void passwordCheckBox()
    {
        passwordTextField.setDisable(!passwordCheckBox.isSelected());
    }

    public void nameCheckBox()
    {
        nameTextField.setDisable(!nameCheckBox.isSelected());
    }

    public void emailCheckBox()
    {
        emailTextField.setDisable(!emailCheckBox.isSelected());
    }

    public void phoneNumberCheckBox()
    {
        phoneNumberTextField.setDisable(!phoneNumberCheckBox.isSelected());
    }

    public void birthdateCheckBox()
    {
        birthDatePicker.setDisable(!birthdateCheckBox.isSelected());
    }

    public void bioCheckBox()
    {
        bioTextField.setDisable(!bioCheckBox.isSelected());
    }

    public void picPathCheckBox()
    {
        picPathTextField.setDisable(!picPathCheckBox.isSelected());
    }

    public void privacyCheckBox()
    {
        privacyChoiceBox.setDisable(!privacyCheckBox.isSelected());
    }

    public void infoStateCheckBox()
    {
        infoStateChoiceBox.setDisable(!infoStateCheckBox.isSelected());
    }

    public void lastSeenCheckBox()
    {
        lastSeenChoiceBox.setDisable(!lastSeenCheckBox.isSelected());
    }

    public void setListener(SettingsListener listener)
    {
        this.listener = listener;
    }

    public Text getMessageText()
    {
        return messageText;
    }

    public void edit()
    {
        String username = usernameCheckBox.isSelected() ? usernameTextField.getText() : MainPageController.getMainPageAgent().getUser().getUsername();
        String password = passwordCheckBox.isSelected() ? passwordTextField.getText() : MainPageController.getMainPageAgent().getUser().getPassword();
        String name = nameCheckBox.isSelected() ? nameTextField.getText() : MainPageController.getMainPageAgent().getUser().getName();
        String email = emailCheckBox.isSelected() ? emailTextField.getText() : MainPageController.getMainPageAgent().getUser().getEmail();
        String phoneNumber = phoneNumberCheckBox.isSelected() ? phoneNumberTextField.getText() : MainPageController.getMainPageAgent().getUser().getPhoneNumber();
        String bio = bioCheckBox.isSelected() ? bioTextField.getText() : MainPageController.getMainPageAgent().getUser().getBio();
        String picPath = picPathCheckBox.isSelected() ? picPathTextField.getText() : MainPageController.getMainPageAgent().getUser().getProfile().getPicturePath();

        Date birthdate = null;
        if (birthdateCheckBox.isSelected())
        {
            try
            {
                birthdate = new SimpleDateFormat(DATE_PATTERN).parse(DEFAULT_DATE);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            if (birthDatePicker.getValue() != null)
            {
                birthdate = java.sql.Date.valueOf(birthDatePicker.getValue());
            }
        }
        else
        {
            birthdate = MainPageController.getMainPageAgent().getUser().getBirthDate();
        }

        boolean privateState = privacyCheckBox.isSelected() ?
                privacyChoiceBox.getSelectionModel().getSelectedItem().equals("private") : MainPageController.getMainPageAgent().getUser().getProfile().isPrivate();
        boolean infoState = infoStateCheckBox.isSelected() ?
                infoStateChoiceBox.getSelectionModel().getSelectedItem().equals("public") : MainPageController.getMainPageAgent().getUser().getProfile().getInfoState();

        int lastSeenState;
        if (lastSeenCheckBox.isSelected())
        {
            switch (lastSeenChoiceBox.getSelectionModel().getSelectedItem())
            {
                case "no one":
                default:
                    lastSeenState = 0;
                    break;
                case "followings":
                    lastSeenState = 1;
                    break;
                case "everyone":
                    lastSeenState = 2;
                    break;
            }
        }
        else
        {
            lastSeenState = MainPageController.getMainPageAgent().getUser().getProfile().getLastSeenState();
        }

        this.listener.eventOccurred(new EditFormEvent(editButton, username, password, name, email, phoneNumber, bio, birthdate, privateState, infoState, lastSeenState, picPath));
    }

    public void deleteAccount()
    {
        this.listener.eventOccurred(new EditFormEvent(deleteAccountButton));
    }

    public void deactivate()
    {
        this.listener.eventOccurred(new EditFormEvent(deactivationButton));
    }
}
