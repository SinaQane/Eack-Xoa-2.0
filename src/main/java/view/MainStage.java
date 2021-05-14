package view;

import listener.login.LoginListener;
import view.login.LoginPageFXML;
import view.login.LoginPage;
import listener.signup.SignUpFormListener;
import view.signup.SignUpPageFXML;
import view.signup.SignUpPage;
import listener.firstpage.FirstPageListener;
import listener.mainpage.MainPageListener;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.firstpage.FirstPage;
import view.firstpage.FirstPageFXML;
import util.Config;

public class MainStage
{
    public MainStage(Stage stage)
    {
        FirstPage firstPage = new FirstPage();
        LoginPage loginPage = LoginPage.getLoginPage();
        SignUpPage signUpPage = SignUpPage.getSignUpPage();
        MainPage mainPage = MainPage.getMainPage();

        FXMLLoader firstPageLoader = firstPage.getLoader();
        FirstPageFXML firstPageController = firstPageLoader.getController();
        firstPageController.setListener(new FirstPageListener(stage));

        FXMLLoader loginPageLoader = loginPage.getLoader();
        LoginPageFXML loginPageController = loginPageLoader.getController();
        loginPageController.setListener(new LoginListener(stage));

        FXMLLoader signUpPageLoader = signUpPage.getLoader();
        SignUpPageFXML signUpPageController = signUpPageLoader.getController();
        signUpPageController.setListener(new SignUpFormListener(stage));

        FXMLLoader mainPageLoader = mainPage.getLoader();
        MainPageFXML mainPageController = mainPageLoader.getController();
        mainPageController.setListener(new MainPageListener(stage));

        stage.setTitle(Config.getConfig("main").getProperty(String.class, "projectName"));
        stage.setScene(firstPage.getScene());
        stage.show();
    }
}
