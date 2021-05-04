package apps.mainpage.logic;

import apps.mainpage.pages.profile.view.ProfilePane;

// A class to return every pane.
public class PanesController
{
    static PanesController panesController;

    ProfilePane profilePane = new ProfilePane();

    private PanesController() {}

    public static PanesController getPanesController()
    {
        if (panesController == null)
        {
            panesController = new PanesController();
        }
        return panesController;
    }

    public ProfilePane getProfilePane()
    {
        return this.profilePane;
    }
}
