package apps.mainpage.pages.userslist_notifications.logic;

import db.UserDB;
import models.Notification;
import models.User;

import java.util.LinkedList;
import java.util.List;

public class NotificationsAgent
{
    private final User user;

    public NotificationsAgent(User user)
    {
        this.user = user;
    }

    public List<List<Notification>> getNotifications()
    {
        List<List<Notification>> result = new LinkedList<>();
        List<Notification> notifications = user.getProfile().getNotifications();

        for (Long id : user.getProfile().getRequests())
        {
            Notification request = new Notification(user.getId(), id, UserDB.getUserDB().get(id).getUsername() + " wants to follow you.");
            notifications.add(request);
        }

        if (notifications.size() == 0)
        {
            List<Notification> temp = new LinkedList<>();
            for (int i = 0; i < 5; i++)
            {
                temp.add(new Notification(0L, ""));
            }
            result.add(temp);
        }

        for (int i = 0; i < notifications.size(); i = i+5)
        {
            List<Notification> temp = new LinkedList<>();
            temp.add(notifications.get(i));

            for (int j = 1; j < 5; j++)
            {
                if (i + j < notifications.size())
                {
                    temp.add(notifications.get(i + j));
                }
                else
                {
                    temp.add(new Notification(0L, ""));
                }
            }
            result.add(temp);
        }

        return result;
    }

    public List<Notification> getPage(int page)
    {
        if (this.getNotifications() == null)
        {
            return null;
        }
        if (page < 0 || page > this.getNumberOfPages())
        {
            return null;
        }

        return this.getNotifications().get(page);
    }

    public int getNumberOfPages()
    {
        if (this.getNotifications() == null)
        {
            return 0;
        }
        return this.getNotifications().size();
    }

    public boolean hasNextPage(int page)
    {
        if (this.getNotifications() == null)
        {
            return false;
        }
        return page != this.getNumberOfPages() - 1;
    }

    public boolean hasPreviousPage(int page)
    {
        if (this.getNotifications() == null)
        {
            return false;
        }
        return page != 0;
    }
}
