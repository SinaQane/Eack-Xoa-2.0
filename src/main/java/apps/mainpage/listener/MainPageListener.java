package apps.mainpage.listener;

import apps.authentication.login.view.LoginPage;
import apps.mainpage.logic.PanesController;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final MainPage mainPage;

    public MainPageListener(Stage stage, LoginPage loginPage, MainPage mainPage)
    {
        this.stage = stage;
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void eventOccurred(Object source)
    {
        if (((Button) source).getId().equals("logoutButton"))
        {
            stage.setScene(loginPage.getScene());
        }
        else if (((Button) source).getId().equals("profileButton"))
        {
            FXMLLoader fxmlLoader = mainPage.getLoader();
            MainPageFXML fxmlController = fxmlLoader.getController();
            fxmlController.setMainPane(PanesController.getPanesController().getProfilePane().getProfilePane());
        }
        else
        {
            System.out.println("Not added yet...");
        }
    }
}
