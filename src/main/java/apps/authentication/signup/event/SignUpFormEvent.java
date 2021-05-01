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

    public SignUpFormEvent(Object source)
    {
        super(source);
        this.username = this.password = this.name = this.email = this.phoneNumber = this.bio = "";
        this.birthDate = null;
    }

    public SignUpFormEvent(Object source, String username, String password, String name,
                           String email, String phoneNumber, String bio, Date birthDate)
    {
        super(source);
        this.username = username.toLowerCase(Locale.ROOT);
        this.password = password;
        this.name = name;
        this.email = email.toLowerCase(Locale.ROOT);
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.bio = bio;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public String getBio()
    {
        return bio;
    }
}