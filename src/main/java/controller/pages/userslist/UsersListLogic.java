package controller.pages.userslist;

import model.User;

import java.util.LinkedList;
import java.util.List;

public class UsersListLogic
{
    private final User user;
    private final String listKind;

    public UsersListLogic(User user, String kind)
    {
        this.user = user;
        this.listKind = kind;
    }

    public List<List<Long>> getUsers()
    {
        List<List<Long>> result = new LinkedList<>();
        List<Long> users = new LinkedList<>();

        switch (listKind)
        {
            case "followers":
                users = user.getProfile().getFollowers();
                break;
            case "followings":
                users = user.getProfile().getFollowings();
                break;
            case "blacklist":
                users = user.getProfile().getBlocked();
                break;
        }

        if (users.size() == 0)
        {
            List<Long> temp = new LinkedList<>();
            for (int i = 0; i < 5; i++)
            {
                temp.add(0L);
            }
            result.add(temp);
        }

        for (int i = 0; i < users.size(); i = i+5)
        {
            List<Long> temp = new LinkedList<>();
            temp.add(users.get(i));

            for (int j = 1; j < 5; j++)
            {
                if (i + j < users.size())
                {
                    temp.add(users.get(i + j));
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
        if (this.getUsers() == null)
        {
            return null;
        }
        if (page < 0 || page > this.getNumberOfPages())
        {
            return null;
        }

        return this.getUsers().get(page);
    }

    public int getNumberOfPages()
    {
        if (this.getUsers() == null)
        {
            return 0;
        }
        return this.getUsers().size();
    }

    public boolean hasNextPage(int page)
    {
        if (this.getUsers() == null)
        {
            return false;
        }
        return page != this.getNumberOfPages() - 1;
    }

    public boolean hasPreviousPage(int page)
    {
        if (this.getUsers() == null)
        {
            return false;
        }
        return page != 0;
    }
}
