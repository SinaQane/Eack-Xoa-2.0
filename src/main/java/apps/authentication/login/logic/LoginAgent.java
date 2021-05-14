package apps.authentication.login.logic;

import apps.authentication.login.event.LoginFormEvent;
import db.UserDB;
import models.Notification;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class LoginAgent
{
    private static final Logger logger = LogManager.getLogger(LoginAgent.class);

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
            logger.error("Failed login to account " + UserDB.getUserDB().get(loginFormEvent.getUsername()).getId());
            return "Invalid";
        }
        return "Invalid";
    }

    public User login()
    {
        User loggedInUser = UserDB.getUserDB().get(loginFormEvent.getUsername());

        loggedInUser.getProfile().addToNotifications(new Notification(loggedInUser.getId(), "New login to your account at " + new Date()));

        if (loggedInUser.isDeactivated())
        {
            loggedInUser.reactivate();
        }

        UserDB.getUserDB().save(loggedInUser);
        logger.warn(UserDB.getUserDB().get(loginFormEvent.getUsername()).getId() + " logged in.");
        return UserDB.getUserDB().get(loginFormEvent.getUsername());
    }
}