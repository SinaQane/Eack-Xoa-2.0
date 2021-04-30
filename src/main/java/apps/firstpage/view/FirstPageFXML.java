package apps.firstpage.view;

import javafx.fxml.Initializable;
import listeners.StringListener;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstPageFXML implements Initializable
{
    private StringListener listener;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void setListener(StringListener listener)
    {
        this.listener = listener;
    }

    public void login()
    {
        listener.listen("Login");
    }

    public void signUp()
    {
        listener.listen("SignUp");
    }
}
