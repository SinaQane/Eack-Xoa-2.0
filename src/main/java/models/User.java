package models;

import db.UserDB;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.*;

public class User
{
    static private final Logger logger = LogManager.getLogger(User.class);

    private final Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String bio;
    private Date birthDate;

    private boolean isActive; // "true" if the page is active, "false" if it's deactivated.

    private long permittedUntil; // 0 if the user isn't permitted
    private int reports;

    private Profile profile;

    public User(String username, String password)
    {
        UserDB.getUserDB().setLastId(UserDB.getUserDB().getLastId() + 1);
        this.id = UserDB.getUserDB().getLastId();
        this.username = username;
        this.password = password;
        this.isActive = true;
        this.permittedUntil = 0;
        this.reports = 0;
        this.profile = new Profile(this.id);
        UserDB.getUserDB().changeUsername("0", this.username);
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
        UserDB.getUserDB().changeUsername(Objects.requireNonNullElse(this.username, "0"), username);
        this.username = username;
        logger.fatal(this.id + "'s username was changed.");
        UserDB.getUserDB().save(this);
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
        logger.error(this.id + "'s password was changed.");
        UserDB.getUserDB().save(this);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
        logger.warn(this.id + "'s name was changed.");
        UserDB.getUserDB().save(this);
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        UserDB.getUserDB().changeEmail(Objects.requireNonNullElse(this.email, "0"), email);
        this.email = email;
        logger.fatal(this.id + "'s email was changed.");
        UserDB.getUserDB().save(this);
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        UserDB.getUserDB().changePhoneNumber(Objects.requireNonNullElse(this.phoneNumber, "0"), phoneNumber);
        this.phoneNumber = phoneNumber;
        logger.fatal(this.id + "'s phonenumber was changed.");
        UserDB.getUserDB().save(this);
    }

    public String getBio()
    {
        return this.bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
        logger.info(this.id + " changed their bio.");
        UserDB.getUserDB().save(this);
    }

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
        logger.warn(this.id + "'s birthdate was changed.");
        UserDB.getUserDB().save(this);
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    public void setActive(boolean active)
    {
        this.isActive = active;
    }

    public boolean isPermitted()
    {
        return this.permittedUntil == 0;
    }

    public long getPermittedUntil()
    {
        return this.permittedUntil;
    }

    public void setPermittedUntil(long permittedUntil)
    {
        this.permittedUntil = permittedUntil;
    }

    public int getReports()
    {
        return this.reports;
    }

    public void setReports(int reports)
    {
        this.reports = reports;
    }

    public Profile getProfile()
    {
        return this.profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }
}
