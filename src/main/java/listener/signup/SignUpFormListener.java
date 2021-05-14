package listener.signup;

import view.login.LoginPage;
import controller.signup.SignUpLogic;
import view.signup.SignUpPageFXML;
import view.signup.SignUpPage;
import controller.mainpage.BackButtonMemory;
import controller.mainpage.BackButtonHandler;
import controller.mainpage.MainPageController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

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
            SignUpLogic signUpLogic = new SignUpLogic(eventObject);
            if (!signUpLogic.check().equals("Valid"))
            {
                FXMLLoader signUpPageLoader = signUpPage.getLoader();
                SignUpPageFXML signUpPageController = signUpPageLoader.getController();
                signUpPageController.getMessageText().setText(signUpLogic.check());
                signUpPageController.getMessageText().setVisible(true);
            }
            else
            {
                User signedUpUser = signUpLogic.signUp();
                MainPageController.getMainPageAgent().setUser(signedUpUser);
                FXMLLoader fxmlLoader = mainPage.getLoader();
                MainPageFXML fxmlController = fxmlLoader.getController();
                fxmlController.profile();
                signUpPage.clear();
                stage.setScene(mainPage.getScene());

                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("profile", MainPageController.getMainPageAgent().getUser().getId()));

                // TODO start timer for last seen update
            }
        }
    }
}
