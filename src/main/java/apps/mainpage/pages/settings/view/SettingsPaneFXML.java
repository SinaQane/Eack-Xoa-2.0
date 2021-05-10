package apps.mainpage.pages.settings.view;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.settings.event.EditFormEvent;
import apps.mainpage.pages.settings.listener.EditFormListener;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsPaneFXML
{
    public Text messageText;

    private EditFormListener listener;

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

    public void setListener(EditFormListener listener)
    {
        this.listener = listener;
    }

    public Text getMessageText()
    {
        return messageText;
    }

    public void edit()
    {
        String username = usernameCheckBox.isSelected() ? usernameTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getUsername();
        String password = passwordCheckBox.isSelected() ? passwordTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getPassword();
        String name = nameCheckBox.isSelected() ? nameTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getName();
        String email = emailCheckBox.isSelected() ? emailTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getEmail();
        String phoneNumber = phoneNumberCheckBox.isSelected() ? phoneNumberTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getPhoneNumber();
        String bio = bioCheckBox.isSelected() ? bioTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getBio();
        String picPath = picPathCheckBox.isSelected() ? picPathTextField.getText() : MainPageAgent.getMainPageAgent().getUser().getProfile().getPicturePath();

        Date birthdate = null;
        if (birthdateCheckBox.isSelected())
        {
            try
            {
                birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("1000-01-01");
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
            birthdate = MainPageAgent.getMainPageAgent().getUser().getBirthDate();
        }

        boolean privateState = privacyCheckBox.isSelected() ?
                privacyChoiceBox.getSelectionModel().getSelectedItem().equals("private") : MainPageAgent.getMainPageAgent().getUser().getProfile().isPrivate();
        boolean infoState = infoStateCheckBox.isSelected() ?
                infoStateChoiceBox.getSelectionModel().getSelectedItem().equals("public") : MainPageAgent.getMainPageAgent().getUser().getProfile().getInfoState();

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
            lastSeenState = MainPageAgent.getMainPageAgent().getUser().getProfile().getLastSeenState();
        }

        this.listener.eventOccurred(new EditFormEvent(editButton, username, password, name, email, phoneNumber, bio, birthdate, privateState, infoState, lastSeenState, picPath));
    }

    public void deleteAccount(ActionEvent actionEvent) // TODO
    {

    }

    public void deactivate(ActionEvent actionEvent) // TODO
    {

    }
}
