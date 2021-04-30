package view;

import apps.authentication.login.listener.LoginFormListener;
import apps.authentication.login.view.LoginFXML;
import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.listener.SignUpFormListener;
import apps.authentication.signup.view.SignUpFXML;
import apps.authentication.signup.view.SignUpPage;
import apps.firstpage.listener.FirstPageListener;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import apps.firstpage.view.FirstPage;
import apps.firstpage.view.FirstPageFXML;

import java.io.IOException;

public class MainStage
{

    public MainStage(Stage stage) throws IOException
    {
        FirstPage firstPage = new FirstPage();
        LoginPage loginPage = new LoginPage();
        SignUpPage signUpPage = new SignUpPage();

        FXMLLoader firstPageLoader = firstPage.getLoader();
        FirstPageFXML firstPageController = firstPageLoader.getController();
        firstPageController.setListener(new FirstPageListener(stage, loginPage, signUpPage));

        FXMLLoader loginPageLoader = loginPage.getLoader();
        LoginFXML loginPageController = loginPageLoader.getController();
        loginPageController.setListener(new LoginFormListener(stage, loginPage, signUpPage));

        FXMLLoader signUpPageLoader = signUpPage.getLoader();
        SignUpFXML signUpPageController = signUpPageLoader.getController();
        signUpPageController.setListener(new SignUpFormListener(stage, loginPage, signUpPage));

        stage.setTitle("Eack Xoa");
        stage.setScene(firstPage.getScene());
        stage.show();
    }
}
