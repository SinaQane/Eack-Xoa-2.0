package apps.authentication.login.listener;

import apps.authentication.login.event.LoginFormEvent;
import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.view.SignUpPage;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginFormListener
{
    private final Stage stage;
    private final LoginPage loginPage; // TODO after adding db
    private final SignUpPage signUpPage;

    public LoginFormListener(Stage stage, LoginPage loginPage, SignUpPage signUpPage)
    {
        this.stage = stage;
        this.loginPage = loginPage;
        this.signUpPage = signUpPage;
    }

    public void eventOccurred(LoginFormEvent eventObject)
    {
        if (((Button) eventObject.getSource()).getId().equals("signUpButton"))
        {
            stage.setScene(signUpPage.getScene());
        }
        else if (((Button) eventObject.getSource()).getId().equals("enterButton"))
        {
            System.out.println(eventObject.getUsername() + " " + eventObject.getPassword());
        }
    }
}
