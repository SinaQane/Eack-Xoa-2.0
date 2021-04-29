package apps.authentication.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import listeners.StringListener;

public class LoginFXML
{
    private StringListener listener;

    public TextField usernameTextField;
    public TextField passwordTextField;
    public Button enterButton;
    public Button signUpButton;

    public void setListener(StringListener listener)
    {
        this.listener = listener;
    }

    public void enter()
    {
        listener.listen("Enter");
    }

    public void signUp()
    {
        listener.listen("SignUp");
    }
}
