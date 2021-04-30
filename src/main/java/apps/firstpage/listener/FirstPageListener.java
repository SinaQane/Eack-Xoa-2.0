package apps.firstpage.listener;

import apps.authentication.login.view.LoginPage;
import apps.authentication.signup.view.SignUpPage;
import javafx.stage.Stage;
import listeners.StringListener;

public class FirstPageListener implements StringListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;

    public FirstPageListener(Stage stage, LoginPage loginPage, SignUpPage signUpPage)
    {
        this.stage = stage;
        this.loginPage = loginPage;
        this.signUpPage = signUpPage;
    }

    @Override
    public void listen(String string)
    {
        if (string.equals("Login"))
        {
            stage.setScene(loginPage.getScene());
        }
        else if (string.equals("SignUp"))
        {
            stage.setScene(signUpPage.getScene());
        }
    }
}
