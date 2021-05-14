package view.login;

import listener.login.LoginFormEvent;
import listener.login.LoginListener;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPageFXML
{
    private LoginListener listener;

    public Text messageText;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public Button enterButton;
    public Button signUpButton;

    public void setListener(LoginListener listener)
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
