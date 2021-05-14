package listener.firstpage;

import view.login.LoginPage;
import view.signup.SignUpPage;
import javafx.stage.Stage;
import listener.StringListener;

public class FirstPageListener implements StringListener
{
    private final Stage stage;
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;

    public FirstPageListener(Stage stage)
    {
        this.stage = stage;
        this.loginPage = LoginPage.getLoginPage();
        this.signUpPage = SignUpPage.getSignUpPage();
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
