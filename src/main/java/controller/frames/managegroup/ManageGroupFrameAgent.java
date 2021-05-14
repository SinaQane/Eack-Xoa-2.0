package controller.frames.managegroup;

import controller.mainpage.MainPageController;
import db.UserDB;
import model.Group;
import model.User;

import java.util.List;

public class ManageGroupFrameAgent
{
    public void edit(Group group, String title, List<String> toAdd, List<String> roRemove)
    {
        group.editTitle(title);
        for (String user : toAdd)
        {
            if (UserDB.getUserDB().get(user) != null)
            {
                group.addToGroup(UserDB.getUserDB().get(user));
            }
        }
        for (String user : roRemove)
        {
            if (UserDB.getUserDB().get(user) != null)
            {
                group.removeFromGroup(UserDB.getUserDB().get(user));
            }
        }
        User user = MainPageController.getMainPageAgent().getUser();
        user.getProfile().addToGroups(group);
        UserDB.getUserDB().save(user);
    }

    public void create(String title, List<String> toAdd)
    {
        Group group = new Group(title);
        for (String user : toAdd)
        {
            group.addToGroup(UserDB.getUserDB().get(user));
        }
        User user = MainPageController.getMainPageAgent().getUser();
        user.getProfile().addToGroups(group);
        UserDB.getUserDB().save(user);
    }
}
