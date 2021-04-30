package apps.authentication.signup.listener;

import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.event.SignUpFormEvent;
import apps.authentication.signup.view.SignUpPage;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignUpFormListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage; // TODO after adding db

    public SignUpFormListener(Stage stage, LoginPage loginPage, SignUpPage signUpPage)
    {
        this.stage = stage;
        this.loginPage = loginPage;
        this.signUpPage = signUpPage;
    }

    public void eventOccurred(SignUpFormEvent eventObject)
    {
        if (((Button) eventObject.getSource()).getId().equals("loginButton"))
        {
            stage.setScene(loginPage.getScene());
        }
        else if (((Button) eventObject.getSource()).getId().equals("signUpButton"))
        {
            System.out.println(eventObject.getUsername() + " " + eventObject.getPassword());
        }
    }
}
