package listener.components.group;

import view.frames.managegroup.ManageGroupFrame;
import view.frames.managegroup.ManageGroupFrameFXML;
import javafx.scene.control.Button;
import model.Group;

import java.util.EventObject;

public class GroupPaneListener
{
    public void eventOccurred(EventObject eventObject, Group group)
    {
        if (((Button) eventObject.getSource()).getId().equals("editGroupButton"))
        {
            ManageGroupFrame manageGroupFrame = new ManageGroupFrame();
            ((ManageGroupFrameFXML) manageGroupFrame.getLoader().getController()).setGroup(group);
        }
    }
}
