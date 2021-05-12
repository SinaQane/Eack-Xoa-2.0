package models;

import db.UserDB;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.*;

public class User
{
    private static final Logger logger = LogManager.getLogger(User.class);

    private final Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String bio;
    private Date birthDate;

    private boolean isActive = true; // "true" if the page is active, "false" if it's deactivated.
    private boolean isDeleted = false; // "false" in default state, "true" id the user deleted this account.

    private final Profile profile;

    public User(String username, String password)
    {
        UserDB.getUserDB().setLastId(UserDB.getUserDB().getLastId() + 1);
        this.id = UserDB.getUserDB().getLastId();
        this.username = username;
        this.password = password;
        this.profile = new Profile(this.id);
        UserDB.getUserDB().changeUsername("0", this.username); // "0" is just a dummy number.
        UserDB.getUserDB().save(this);
    }

    public Long getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        if (this.username != null)
        {
            if (this.username.equals(username))
            {
                return;
            }
        }
        this.username = username;
        logger.fatal(this.id + "'s username was changed.");
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        if(this.password != null)
        {
            if (this.password.equals(password))
            {
                return;
            }
        }
        this.password = password;
        logger.error(this.id + "'s password was changed.");
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
        logger.warn(this.id + "'s name was changed.");
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        if(this.email != null)
        {
            if (this.email.equals(email))
            {
                return;
            }
        }
        this.email = email;
        logger.fatal(this.id + "'s email was changed.");
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        if (this.phoneNumber != null)
        {
            if (this.phoneNumber.equals(phoneNumber))
            {
                return;
            }
        }
        this.phoneNumber = phoneNumber;
        logger.fatal(this.id + "'s phonenumber was changed.");
    }

    public String getBio()
    {
        return this.bio;
    }

    public void setBio(String bio)
    {
        if(this.bio != null)
        {
            if (this.bio.equals(bio))
            {
                return;
            }
        }
        this.bio = bio;
        logger.info(this.id + " changed their bio.");
    }

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        if (this.birthDate != null)
        {
            if (this.birthDate.equals(birthDate))
            {
                return;
            }
        }
        this.birthDate = birthDate;
        logger.warn(this.id + "'s birthdate was changed.");
    }

    public boolean isDeactivated()
    {
        return !this.isActive;
    }

    public void deactivate()
    {
        this.isActive = false;
        logger.fatal(this.id + " deactivated their account.");
    }

    public void reactivate()
    {
        this.isActive = true;
        logger.warn(this.id + " reactivated their account.");
    }

    public boolean isDeleted()
    {
        return this.isDeleted;
    }

    public void deleteAccount()
    {
        this.isDeleted = true;
        logger.error(this.id + " deleted their account.");
    }

    public Profile getProfile()
    {
        return this.profile;
    }
}
