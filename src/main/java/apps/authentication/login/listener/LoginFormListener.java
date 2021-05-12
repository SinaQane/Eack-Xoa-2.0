package apps.authentication.login.listener;

import apps.authentication.login.event.LoginFormEvent;
import apps.authentication.login.logic.LoginAgent;
import apps.authentication.login.view.LoginFXML;
import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.view.SignUpPage;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
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

    public LoginFormListener(Stage stage)
    {
        this.stage = stage;
        this.loginPage = LoginPage.getLoginPage();
        this.signUpPage = SignUpPage.getSignUpPage();
        this.mainPage = MainPage.getMainPage();
    }

    public void eventOccurred(LoginFormEvent eventObject)
    {
        if (((Button) eventObject.getSource()).getId().equals("signUpButton"))
        {
            signUpPage.clear();
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
                MainPageAgent.getMainPageAgent().setUser(loggedInUser);
                FXMLLoader fxmlLoader = mainPage.getLoader();
                MainPageFXML fxmlController = fxmlLoader.getController();
                fxmlController.profile();
                loginPage.clear();
                stage.setScene(mainPage.getScene());

                // TODO start timer for last seen update
            }
        }
    }
}
