package apps.authentication.login.listener;

import apps.authentication.login.event.LoginFormEvent;
import apps.authentication.login.logic.LoginAgent;
import apps.authentication.login.view.LoginFXML;
import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.view.SignUpPage;
import apps.mainpage.view.MainPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;

public class LoginFormListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;
    private final MainPage mainPage;

    public LoginFormListener(Stage stage, LoginPage loginPage, SignUpPage signUpPage, MainPage mainPage)
    {
        this.stage = stage;
        this.loginPage = loginPage;
        this.signUpPage = signUpPage;
        this.mainPage = mainPage;
    }

    public void eventOccurred(LoginFormEvent eventObject)
    {
        if (((Button) eventObject.getSource()).getId().equals("signUpButton"))
        {
            stage.setScene(signUpPage.getScene());
        }
        else if (((Button) eventObject.getSource()).getId().equals("enterButton"))
        {
            LoginAgent loginAgent = new LoginAgent(eventObject);
            if (loginAgent.check().equals("Invalid"))
            {
                FXMLLoader loginPageLoader = loginPage.getLoader();
                LoginFXML loginPageController = loginPageLoader.getController();
                loginPageController.getMessageText().setText("The username or password is wrong.");
                loginPageController.getMessageText().setVisible(true);
            }
            else
            {
                User loggedInUser = loginAgent.login();
                // System.out.println("Welcome " + loggedInUser.getUsername());
                stage.setScene(mainPage.getScene());
            }
        }
    }
}
