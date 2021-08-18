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
            SignUpLogic logic = new SignUpLogic(eventObject);
            if (!logic.check().equals("Valid"))
            {
                SignUpPageFXML signUpPageFXML = signUpPage.getLoader().getController();
                signUpPageFXML.getMessageText().setText(logic.check());
                signUpPageFXML.getMessageText().setVisible(true);
            }
            else
            {
                User signedUpUser = logic.signUp();
                MainPageController.getMainPageController().setUser(signedUpUser);
                MainPageFXML mainPageFXML = mainPage.getLoader().getController();
                mainPageFXML.profile();
                signUpPage.clear();
                stage.setScene(mainPage.getScene());

                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("profile", MainPageController.getMainPageController().getUser().getId()));
            }
        }
    }
}
