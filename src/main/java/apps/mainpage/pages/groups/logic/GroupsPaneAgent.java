package apps.mainpage.pages.groups.logic;

import apps.mainpage.logic.MainPageAgent;
import models.Group;

import java.util.LinkedList;
import java.util.List;

public class GroupsPaneAgent
{
    public List<List<Group>> getGroups()
    {
        List<Group> groups = MainPageAgent.getMainPageAgent().getUser().getProfile().getGroups();
        List<List<Group>> result = new LinkedList<>();

        if (groups.size() == 0)
        {
            List<Group> temp = new LinkedList<>();
            for (int i = 0; i < 5; i++)
            {
                temp.add(null);
            }
            result.add(temp);
            return result;
        }

        for (int i = 0; i < groups.size(); i = i+5)
        {
            List<Group> temp = new LinkedList<>();
            temp.add(groups.get(i));

            for (int j = 1; j < 5; j++)
            {
                if (i + j < groups.size())
                {
                    temp.add(groups.get(i + j));
                }
                else
                {
                    temp.add(null);
                }
            }
            result.add(temp);
        }
        return result;
    }

    public List<Group> getPage(int page)
    {
        if (this.getGroups() == null)
        {
            return null;
        }
        if (page < 0 || page > this.getNumberOfPages())
        {
            return null;
        }

        return this.getGroups().get(page);
    }

    public int getNumberOfPages()
    {
        if (this.getGroups() == null)
        {
            return 0;
        }
        return this.getGroups().size();
    }

    public boolean hasNextPage(int page)
    {
        if (this.getGroups() == null)
        {
            return false;
        }
        return page != this.getNumberOfPages() - 1;
    }

    public boolean hasPreviousPage(int page)
    {
        if (this.getGroups() == null)
        {
            return false;
        }
        return page != 0;
    }
}
