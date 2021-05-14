package listener.pages.profile;

import java.util.EventObject;

public class ProfileEvent extends EventObject
{
    private final ProfileForm profileForm;

    public ProfileEvent(Object source, ProfileForm profileForm)
    {
        super(source);
        this.profileForm = profileForm;
    }

    public ProfileForm getProfileForm()
    {
        return profileForm;
    }
}
