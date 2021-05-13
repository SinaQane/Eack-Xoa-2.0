package apps.components.grouppane.listener;

import apps.groupframe.view.GroupFrame;
import apps.groupframe.view.GroupFrameFXML;
import javafx.scene.control.Button;
import models.Group;

import java.util.EventObject;

public class GroupPaneListener
{
    public void eventOccurred(EventObject eventObject, Group group)
    {
        if (((Button) eventObject.getSource()).getId().equals("editGroupButton"))
        {
            GroupFrame groupFrame = new GroupFrame();
            ((GroupFrameFXML) groupFrame.getLoader().getController()).setGroup(group);
        }
    }
}
