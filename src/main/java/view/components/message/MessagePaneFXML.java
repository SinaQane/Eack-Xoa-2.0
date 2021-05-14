package view.components.message;

import listener.components.message.MessagePaneListener;
import controller.mainpage.MainPageController;
import db.TweetDB;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Message;

import java.util.EventObject;

public class MessagePaneFXML
{
    private final MessagePaneListener listener = new MessagePaneListener();

    private Message message;

    public Text ownerText;
    public Text messageText;
    public Text tweetText;
    public Button viewImageButton;
    public Button viewTweetButton;
    public Button deleteButton;
    public Button editButton;

    public void setData(Message message)
    {
        this.message = message;

        this.ownerText.setText("@" + UserDB.getUserDB().get(message.getOwnerId()).getUsername());
        this.messageText.setText(message.getText());

        if (!message.getTweetId().equals(""))
        {
            long ownerId = TweetDB.getTweetDB().get(message.getTweetId()).getOwner();
            this.tweetText.setText("A tweet from " + UserDB.getUserDB().get(ownerId).getUsername());
            tweetText.setVisible(true);
            viewTweetButton.setVisible(true);
        }
        else
        {
            tweetText.setVisible(false);
            viewTweetButton.setVisible(false);
        }

        if (message.getOwnerId() == MainPageController.getMainPageController().getUser().getId())
        {
            editButton.setVisible(true);
            deleteButton.setVisible(true);
        }
        else
        {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
    }

    public void viewImage()
    {
        listener.eventOccurred(new EventObject(viewImageButton), message);
    }

    public void viewTweet()
    {
        listener.eventOccurred(new EventObject(viewTweetButton), message);
    }

    public void delete()
    {
        listener.eventOccurred(new EventObject(deleteButton), message);
    }

    public void edit()
    {
        listener.eventOccurred(new EventObject(editButton), message);
    }
}
