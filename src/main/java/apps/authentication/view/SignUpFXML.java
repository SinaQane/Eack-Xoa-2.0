package apps.authentication.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import listeners.StringListener;

public class SignUpFXML
{
    private StringListener listener;

    public TextField usernameTextField;
    public TextField passwordTextField;
    public TextField nameTextField;
    public TextField emailTextField;
    public TextField bioTextField;
    public DatePicker birthDate;
    public TextField phoneNumberTextField;
    public Button signUpButton;
    public Button loginButton;
    public CheckBox checkBox;

    public void setListener(StringListener listener)
    {
        this.listener = listener;
    }

    public void login()
    {
        listener.listen("Login");
    }

    public void signUp()
    {
        listener.listen("SignUp");
    }

    public void checkbox()
    {
        signUpButton.setDisable(!checkBox.isSelected());
    }
}
