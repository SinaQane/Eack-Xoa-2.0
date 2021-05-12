package apps.components.notification.view;

import apps.components.notification.listener.NotificationPaneListener;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.Notification;

import java.util.EventObject;

public class NotificationPaneFXML
{
    private NotificationPaneListener listener;

    private long otherUser = 0L;

    public Text notificationText;
    public Button acceptButton;
    public Button goodRejectButton;
    public Button badRejectButton;

    public void setListener(NotificationPaneListener notificationPaneListener)
    {
        this.listener = notificationPaneListener;
    }

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