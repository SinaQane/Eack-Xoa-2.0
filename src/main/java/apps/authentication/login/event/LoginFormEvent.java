package apps.authentication.login.event;

import java.util.EventObject;

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
        this.username = username;
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
