package listener.login;

import controller.login.LoginLogic;
import view.login.LoginPageFXML;
import view.login.LoginPage;
import view.signup.SignUpPage;
import controller.mainpage.BackButtonMemory;
import controller.mainpage.BackButtonHandler;
import controller.mainpage.MainPageController;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

public class LoginListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;
    private final MainPage mainPage;

    public LoginListener(Stage stage)
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
            LoginLogic logic = new LoginLogic(eventObject);
            if (logic.check().equals("Invalid"))
            {
                LoginPageFXML loginPageFXML = loginPage.getLoader().getController();
                loginPageFXML.getMessageText().setText("The username or password is wrong.");
                loginPageFXML.getMessageText().setVisible(true);
            }
            else
            {
                User loggedInUser = logic.login();
                MainPageController.getMainPageController().setUser(loggedInUser);
                MainPageFXML mainPageFXML = mainPage.getLoader().getController();
                mainPageFXML.profile();
                loginPage.clear();
                stage.setScene(mainPage.getScene());

                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("profile", MainPageController.getMainPageController().getUser().getId()));

                MainPageController.getMainPageController().startTimer();
            }
        }
    }
}
