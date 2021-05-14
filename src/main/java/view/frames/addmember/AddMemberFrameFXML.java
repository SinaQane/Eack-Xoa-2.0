package view.frames.addmember;

import listener.frames.addmember.AddMemberFrameListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Chat;

import java.util.EventObject;

public class AddMemberFrameFXML
{
    private final AddMemberFrameListener listener = new AddMemberFrameListener();

    private Chat chat;

    public TextField usernameTextField;
    public Button addButton;

    public void setChat(Chat chat)
    {
        this.chat = chat;
    }

    public void add(ActionEvent actionEvent)
    {
        listener.eventOccurred(new EventObject(addButton), chat, usernameTextField.getText());
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
