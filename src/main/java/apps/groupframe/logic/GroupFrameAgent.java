package apps.groupframe.logic;

import apps.mainpage.logic.MainPageAgent;
import db.UserDB;
import models.Group;
import models.User;

import java.util.List;

public class GroupFrameAgent
{
    public void edit(Group group, String title, List<String> toAdd, List<String> roRemove)
    {
        group.editTitle(title);
        for (String user : toAdd)
        {
            group.addToGroup(UserDB.getUserDB().get(user));
        }
        for (String user : roRemove)
        {
            group.removeFromGroup(UserDB.getUserDB().get(user));
        }
        User user = MainPageAgent.getMainPageAgent().getUser();
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
        User user = MainPageAgent.getMainPageAgent().getUser();
        user.getProfile().addToGroups(group);
        UserDB.getUserDB().save(user);
    }
}
