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
import javafx.fxml.FXMLLoader;
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
            LoginLogic loginLogic = new LoginLogic(eventObject);
            if (loginLogic.check().equals("Invalid"))
            {
                FXMLLoader loginPageLoader = loginPage.getLoader();
                LoginPageFXML loginPageController = loginPageLoader.getController();
                loginPageController.getMessageText().setText("The username or password is wrong.");
                loginPageController.getMessageText().setVisible(true);
            }
            else
            {
                User loggedInUser = loginLogic.login();
                MainPageController.getMainPageAgent().setUser(loggedInUser);
                FXMLLoader fxmlLoader = mainPage.getLoader();
                MainPageFXML fxmlController = fxmlLoader.getController();
                fxmlController.profile();
                loginPage.clear();
                stage.setScene(mainPage.getScene());

                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("profile", MainPageController.getMainPageAgent().getUser().getId()));

                // TODO start timer for last seen update
            }
        }
    }
}
