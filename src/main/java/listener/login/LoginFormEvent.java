package listener.login;

import java.util.EventObject;
import java.util.Locale;

public class LoginFormEvent extends EventObject
{
    private final String username;
    private final String password;

    public LoginFormEvent(Object source)
    {
        super(source);
        this.username = this.password = "";
    }

    public LoginFormEvent(Object source, String username, String password)
    {
        super(source);
        this.username = username.toLowerCase(Locale.ROOT);
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
