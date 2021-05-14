package apps.authentication.login.view;

import apps.authentication.login.event.LoginFormEvent;
import apps.authentication.login.listener.LoginFormListener;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginFXML
{
    private LoginFormListener listener;
    public Text messageText;

    public TextField usernameTextField;
    public TextField passwordTextField;
    public Button enterButton;
    public Button signUpButton;

    public void setListener(LoginFormListener listener)
    {
        this.listener = listener;
    }

    public Text getMessageText()
    {
        return this.messageText;
    }

    public void enter()
    {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        listener.eventOccurred(new LoginFormEvent(enterButton, username, password));
    }

    public void signUp()
    {
        listener.eventOccurred(new LoginFormEvent(signUpButton));
    }
}
