package view;

import apps.authentication.login.listener.LoginFormListener;
import apps.authentication.login.view.LoginFXML;
import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.listener.SignUpFormListener;
import apps.authentication.signup.view.SignUpFXML;
import apps.authentication.signup.view.SignUpPage;
import apps.firstpage.listener.FirstPageListener;
import apps.mainpage.listener.MainPageListener;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import apps.firstpage.view.FirstPage;
import apps.firstpage.view.FirstPageFXML;

public class MainStage
{

    public MainStage(Stage stage)
    {
        FirstPage firstPage = new FirstPage();
        LoginPage loginPage = new LoginPage();
        SignUpPage signUpPage = new SignUpPage();
        MainPage mainPage = new MainPage();

        FXMLLoader firstPageLoader = firstPage.getLoader();
        FirstPageFXML firstPageController = firstPageLoader.getController();
        firstPageController.setListener(new FirstPageListener(stage, loginPage, signUpPage));

        FXMLLoader loginPageLoader = loginPage.getLoader();
        LoginFXML loginPageController = loginPageLoader.getController();
        loginPageController.setListener(new LoginFormListener(stage, loginPage, signUpPage, mainPage));

        FXMLLoader signUpPageLoader = signUpPage.getLoader();
        SignUpFXML signUpPageController = signUpPageLoader.getController();
        signUpPageController.setListener(new SignUpFormListener(stage, loginPage, signUpPage, mainPage));

        FXMLLoader mainPageLoader = mainPage.getLoader();
        MainPageFXML mainPageController = mainPageLoader.getController();
        mainPageController.setListener(new MainPageListener(stage));

        stage.setTitle("Eack Xoa");
        stage.setScene(firstPage.getScene());
        stage.show();
    }
}
