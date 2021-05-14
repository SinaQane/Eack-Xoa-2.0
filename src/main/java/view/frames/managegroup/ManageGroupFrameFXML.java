package view.frames.managegroup;

import listener.frames.managegroup.ManageGroupEvent;
import listener.frames.managegroup.ManageGroupForm;
import listener.frames.managegroup.ManageGroupFrameListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Group;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ManageGroupFrameFXML
{
    private final ManageGroupFrameListener listener = new ManageGroupFrameListener();

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

        ManageGroupForm manageGroupForm = new ManageGroupForm(group, titleTextField.getText(), toAdd, toRemove);
        listener.eventOccurred(new ManageGroupEvent(doneButton, manageGroupForm));
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
