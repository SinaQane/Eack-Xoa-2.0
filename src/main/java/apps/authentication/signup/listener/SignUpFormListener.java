package apps.authentication.signup.listener;

import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.event.SignUpFormEvent;
import apps.authentication.signup.logic.SignUpAgent;
import apps.authentication.signup.view.SignUpFXML;
import apps.authentication.signup.view.SignUpPage;
import apps.mainpage.event.PageMemory;
import apps.mainpage.logic.BackButtonAgent;
import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;

public class SignUpFormListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;
    private final MainPage mainPage;

    public SignUpFormListener(Stage stage)
    {
        this.stage = stage;
        this.loginPage = LoginPage.getLoginPage();
        this.signUpPage = SignUpPage.getSignUpPage();
        this.mainPage = MainPage.getMainPage();
    }

    public void eventOccurred(SignUpFormEvent eventObject)
    {
        if (((Button) eventObject.getSource()).getId().equals("loginButton"))
        {
            loginPage.clear();
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
                MainPageAgent.getMainPageAgent().setUser(signedUpUser);
                FXMLLoader fxmlLoader = mainPage.getLoader();
                MainPageFXML fxmlController = fxmlLoader.getController();
                fxmlController.profile();
                signUpPage.clear();
                stage.setScene(mainPage.getScene());

                BackButtonAgent.getBackButtonAgent().add(new PageMemory("profile", MainPageAgent.getMainPageAgent().getUser().getId()));

                // TODO start timer for last seen update
            }
        }
    }
}
