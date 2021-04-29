package view;

import apps.authentication.view.LoginFXML;
import apps.authentication.view.LoginPage;
import apps.authentication.view.SignUpFXML;
import apps.authentication.view.SignUpPage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.firstpage.FirstPage;
import view.firstpage.FirstPageFXML;

import java.io.IOException;

public class MainStage
{
    private final FirstPage firstPage = new FirstPage(); // TODO After logout, user should be redirected here.
    private final LoginPage loginPage = new LoginPage();
    private final SignUpPage signUpPage = new SignUpPage();

    public MainStage(Stage stage) throws IOException
    {
        FXMLLoader firstPageLoader = firstPage.getLoader();
        FirstPageFXML firstPageController = firstPageLoader.getController();
        firstPageController.setListener(string -> {
            if (string.equals("Login"))
            {
                stage.setScene(loginPage.getScene());
            }
            else if (string.equals("SignUp"))
            {
                stage.setScene(signUpPage.getScene());
            }
        });

        FXMLLoader loginPageLoader = loginPage.getLoader();
        LoginFXML loginPageController = loginPageLoader.getController();
        loginPageController.setListener(string -> {
            if (string.equals("Enter"))
            {
                System.out.println("Enter");
            }
            else if (string.equals("SignUp"))
            {
                stage.setScene(signUpPage.getScene());
            }
        });

        FXMLLoader signUpPageLoader = signUpPage.getLoader();
        SignUpFXML signUpPageController = signUpPageLoader.getController();
        signUpPageController.setListener(string -> {
            if (string.equals("SignUp"))
            {
                System.out.println("SignUp");
            }
            else if (string.equals("Login"))
            {
                stage.setScene(loginPage.getScene());
            }
        });

        stage.setTitle("Eack Xoa");
        stage.setScene(firstPage.getScene());
        stage.show();
    }
}
