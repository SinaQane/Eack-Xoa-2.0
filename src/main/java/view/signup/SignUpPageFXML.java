package view.signup;

import listener.signup.SignUpFormEvent;
import listener.signup.SignUpFormListener;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import config.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpPageFXML
{
    private static final String DATE_PATTERN = Config.getConfig("patterns").getProperty(String.class, "tinyDate");
    private static final String DEFAULT_DATE = Config.getConfig("patterns").getProperty(String.class, "defaultDate");

    private SignUpFormListener listener;

    public Text messageText;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public TextField nameTextField;
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public TextField bioTextField;
    public TextField picPathField;
    public DatePicker birthDatePicker;
    public Button signUpButton;
    public Button loginButton;
    public CheckBox checkBox;

    public void login()
    {
        listener.eventOccurred(new SignUpFormEvent(loginButton));
    }

    public void signUp()
    {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String bio = bioTextField.getText();
        String picPath = picPathField.getText();

        Date birthDate = null;
        try
        {
            birthDate = new SimpleDateFormat(DATE_PATTERN).parse(DEFAULT_DATE);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        if (birthDatePicker.getValue() != null)
        {
            birthDate = java.sql.Date.valueOf(birthDatePicker.getValue());
        }

        listener.eventOccurred(new SignUpFormEvent(signUpButton, username, password, name, email, phoneNumber, bio, birthDate, picPath));
    }

    public void checkbox()
    {
        signUpButton.setDisable(!checkBox.isSelected());
    }

    public void setListener(SignUpFormListener listener)
    {
        this.listener = listener;
    }

    public Text getMessageText()
    {
        return this.messageText;
    }
}
