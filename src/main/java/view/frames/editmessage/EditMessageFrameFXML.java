package view.frames.editmessage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listener.frames.editmessage.EditMessageFrameListener;
import model.Message;

import java.util.EventObject;

public class EditMessageFrameFXML
{
    private final EditMessageFrameListener listener = new EditMessageFrameListener();

    private Message message;

    public TextField messageTextField;
    public Button doneButton;

    public void setMessage(Message message)
    {
        this.message = message;
    }

    public void done(ActionEvent actionEvent)
    {
        listener.eventOccurred(new EventObject(doneButton), message, messageTextField.getText());
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
