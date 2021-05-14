package view.components.group;

import listener.components.group.GroupPaneListener;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Group;

import java.util.EventObject;

public class GroupPaneFXML
{
    private final GroupPaneListener listener = new GroupPaneListener();
    private Group group;

    public Text groupNameText;
    public Text membersText;
    public Button editGroupButton;

    public void setData(Group group)
    {
        this.group = group;

        this.groupNameText.setText(group.getTitle());

        StringBuilder members = new StringBuilder();
        for (long id : group.getMembersId())
        {
            members.append(UserDB.getUserDB().get(id).getUsername()).append(" ");
        }
        this.membersText.setText(members.toString());
    }

    public void editGroup()
    {
        listener.eventOccurred(new EventObject(editGroupButton), group);
    }
}
