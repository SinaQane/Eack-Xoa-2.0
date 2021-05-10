package apps.authentication.signup.event;

import java.util.Date;
import java.util.EventObject;
import java.util.Locale;

public class SignUpFormEvent extends EventObject
{
    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bio;
    private final Date birthDate;
    private final String picPath;

    public SignUpFormEvent(Object source)
    {
        super(source);
        this.username = this.password = this.name = this.email = this.phoneNumber = this.bio = this.picPath = "";
        this.birthDate = null;
    }

    public SignUpFormEvent(Object source, String username, String password, String name,
            String email, String phoneNumber, String bio, Date birthDate, String picPath)
    {
        super(source);
        this.username = username.toLowerCase(Locale.ROOT);
        this.password = password;
        this.name = name;
        this.email = email.toLowerCase(Locale.ROOT);
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.bio = bio;
        this.picPath = picPath;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public String getBio()
    {
        return this.bio;
    }

    public String getPicPath()
    {
        return this.picPath;
    }
}