package apps.mainpage.pages.explore.logic;

import db.UserDB;
import models.User;

import java.util.LinkedList;
import java.util.List;

public class SearchAgent
{
    private final String searched;

    public SearchAgent(String searched)
    {
        this.searched = searched;
    }

    public List<List<Long>> search()
    {
        List<List<Long>> result = new LinkedList<>();
        List<User> users = new LinkedList<>();

        for (User user : UserDB.getUserDB().getALl())
        {
            if (user.getUsername().contains(this.searched) || user.getName().contains(this.searched))
            {
                if (!user.isDeactivated())
                {
                    users.add(user);
                }
            }
        }

        if (users.size() == 0)
        {
            List<Long> temp = new LinkedList<>();
            for (int i = 0; i < 4; i++)
            {
                temp.add(0L);
            }
            result.add(temp);
        }

        for (int i = 0; i < users.size(); i = i+4)
        {
            List<Long> temp = new LinkedList<>();
            temp.add(users.get(i).getId());

            for (int j = 1; j < 4; j++)
            {
                if (i + j < users.size())
                {
                    temp.add(users.get(i + j).getId());
                }
                else
                {
                    temp.add(0L);
                }
            }
            result.add(temp);
        }

        return result;
    }

    public List<Long> getPage(int page)
    {
        if (this.search() == null)
        {
            return null;
        }
        if (page < 0 || page > this.getNumberOfPages())
        {
            return null;
        }

        return this.search().get(page);
    }

    public int getNumberOfPages()
    {
        if (this.search() == null)
        {
            return 0;
        }
        return this.search().size();
    }

    public boolean hasNextPage(int page)
    {
        if (this.search() == null)
        {
            return false;
        }
        return page != this.getNumberOfPages() - 1;
    }

    public boolean hasPreviousPage(int page)
    {
        if (this.search() == null)
        {
            return false;
        }
        return page != 0;
    }
}
