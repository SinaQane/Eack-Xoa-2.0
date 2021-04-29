package view;

import apps.authentication.view.LoginPage;
import apps.authentication.view.SignUpPage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.firstpage.FirstPage;
import view.firstpage.FirstPageFXML;

import java.io.IOException;

public class MainStage
{
    private final FirstPage firstPage = new FirstPage();
    private final LoginPage loginPage = new LoginPage();
    private final SignUpPage signUpPage = new SignUpPage();

    public MainStage(Stage stage) throws IOException
    {
        stage.setTitle("Eack Xoa");
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
        stage.setScene(firstPage.getScene());
        stage.show();
    }
}
