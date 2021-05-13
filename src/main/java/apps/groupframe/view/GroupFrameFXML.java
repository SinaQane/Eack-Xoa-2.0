package apps.groupframe.view;

import apps.groupframe.event.GroupEvent;
import apps.groupframe.event.GroupForm;
import apps.groupframe.listener.GroupFrameListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Group;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GroupFrameFXML
{
    private final GroupFrameListener listener = new GroupFrameListener();

    private Group group = null;

    public TextField titleTextField;
    public TextField addedTextField;
    public TextField removedTextField;
    public Button doneButton;

    public void setGroup(Group group)
    {
        if (group == null)
        {
            return;
        }
        this.group = group;
    }

    public void done(ActionEvent actionEvent)
    {
        List<String> toAdd = new LinkedList<>(Arrays.asList(addedTextField.getText().split(" ")));
        List<String> toRemove = new LinkedList<>(Arrays.asList(removedTextField.getText().split(" ")));

        GroupForm groupForm = new GroupForm(group, titleTextField.getText(), toAdd, toRemove);
        listener.eventOccurred(new GroupEvent(doneButton, groupForm));
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
