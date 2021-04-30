package apps.authentication.signup.listener;

import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.event.SignUpFormEvent;
import apps.authentication.signup.logic.SignUpAgent;
import apps.authentication.signup.view.SignUpFXML;
import apps.authentication.signup.view.SignUpPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;

public class SignUpFormListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;

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
            SignUpAgent signUpAgent = new SignUpAgent(eventObject);
            if (!signUpAgent.check().equals("Valid"))
            {
                FXMLLoader signUpPageLoader = signUpPage.getLoader();
                SignUpFXML signUpPageController = signUpPageLoader.getController();
                signUpPageController.getMessageText().setText(signUpAgent.check());
                signUpPageController.getMessageText().setVisible(true);
            }
            else
            {
                User signedUpUser = signUpAgent.signUp();
                System.out.println("Welcome " + signedUpUser.getUsername());
            }
        }
    }
}
