package view.components.notification;

import listener.components.notification.NotificationPaneListener;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Notification;

import java.util.EventObject;

public class NotificationPaneFXML
{
    private final NotificationPaneListener listener = new NotificationPaneListener();

    private long otherUser = 0L;

    public Text notificationText;
    public Button acceptButton;
    public Button goodRejectButton;
    public Button badRejectButton;

    public void setData(Notification notification)
    {
        if (notification.getRequestFrom() == 0)
        {
            acceptButton.setVisible(false);
            goodRejectButton.setVisible(false);
            badRejectButton.setVisible(false);
        }
        else
        {
            otherUser = notification.getRequestFrom();
        }

        notificationText.setText(notification.getText());
    }

    public void accept()
    {
        listener.eventOccurred(new EventObject(acceptButton), this.otherUser);
    }

    public void goodReject()
    {
        listener.eventOccurred(new EventObject(goodRejectButton), this.otherUser);
    }

    public void badReject()
    {
        listener.eventOccurred(new EventObject(badRejectButton), this.otherUser);
    }
}
