package view.pages.messages;

import listener.pages.messages.ChatroomListener;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Chat;

import java.util.EventObject;

public class ChatroomPaneFXML
{
    private final ChatroomListener listener = new ChatroomListener();

    private int page;
    private Chat chat;

    public TextField messageTextField;
    public TextField picPathTextField;
    public Button addMemberButton;
    public Button previousButton;
    public Button nextButton;
    public Button sendButton;
    public Pane firstMessagePane;
    public Pane secondMessagePane;
    public Pane thirdMessagePane;
    public Pane fourthMessagePane;
    public Pane fifthMessagePane;

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setChat(Chat chat)
    {
        this.chat = chat;
    }

    public void setMessagePane(int i, Pane messagePane)
    {
        if (i == 0)
        {
            this.firstMessagePane.getChildren().clear();
            this.firstMessagePane.getChildren().add(messagePane);
        }
        else if (i == 1)
        {
            this.secondMessagePane.getChildren().clear();
            this.secondMessagePane.getChildren().add(messagePane);
        }
        else if (i == 2)
        {
            this.thirdMessagePane.getChildren().clear();
            this.thirdMessagePane.getChildren().add(messagePane);
        }
        else if (i == 3)
        {
            this.fourthMessagePane.getChildren().clear();
            this.fourthMessagePane.getChildren().add(messagePane);
        }
        else if (i == 4)
        {
            this.fifthMessagePane.getChildren().clear();
            this.fifthMessagePane.getChildren().add(messagePane);
        }
    }

    public Button getPreviousButton()
    {
        return previousButton;
    }

    public Button getNextButton()
    {
        return nextButton;
    }

    public Button getAddMemberButton()
    {
        return addMemberButton;
    }

    public void addMember()
    {
        listener.eventOccurred(new EventObject(addMemberButton), chat, page, "", "");
    }

    public void previous()
    {
        listener.eventOccurred(new EventObject(previousButton), chat, page, "", "");
    }

    public void next()
    {
        listener.eventOccurred(new EventObject(nextButton), chat, page, "", "");
    }

    public void send()
    {
        listener.eventOccurred(new EventObject(sendButton), chat, page, messageTextField.getText(), picPathTextField.getText());
    }
}
