package apps.mainpage.pages.settings.event;

import java.util.Date;
import java.util.EventObject;
import java.util.Locale;

public class EditFormEvent extends EventObject
{
    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bio;
    private final String picPath;
    private final Date birthDate;
    private final boolean privateState;
    private final boolean infoState;
    private final int lastSeenState;

    public EditFormEvent(Object source, String username, String password, String name,
                         String email, String phoneNumber, String bio, Date birthDate,
                         boolean privateState, boolean infoState, int lastSeenState, String picPath)
    {
        super(source);
        this.username = username.toLowerCase(Locale.ROOT);
        this.password = password;
        this.name = name;
        this.email = email.toLowerCase(Locale.ROOT);
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.birthDate = birthDate;
        this.privateState = privateState;
        this.infoState = infoState;
        this.lastSeenState = lastSeenState;
        this.picPath = picPath;
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

    public String getBio()
    {
        return bio;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public boolean isPrivateState()
    {
        return privateState;
    }

    public boolean isInfoState()
    {
        return infoState;
    }

    public int getLastSeenState()
    {
        return lastSeenState;
    }

    public String getPicPath()
    {
        return picPath;
    }
}
