package apps.authentication.login.logic;

import apps.authentication.login.event.LoginFormEvent;
import db.UserDB;
import models.User;


public class LoginAgent
{
    private final LoginFormEvent loginFormEvent;

    public LoginAgent(LoginFormEvent loginFormEvent)
    {
        this.loginFormEvent = loginFormEvent;
    }

    public String check()
    {
        if (UserDB.getUserDB().exists(loginFormEvent.getUsername()))
        {
            if (UserDB.getUserDB().get(loginFormEvent.getUsername()).getPassword().equals(loginFormEvent.getPassword()))
            {
                return "Valid";
            }
            return "Invalid";
        }
        return "Invalid";
    }

    public User login()
    {
        return UserDB.getUserDB().get(loginFormEvent.getUsername());
    }
}