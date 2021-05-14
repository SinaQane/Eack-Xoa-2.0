package apps.mainpage.logic;

import apps.mainpage.event.PageMemory;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;

import java.util.LinkedList;
import java.util.List;

public class BackButtonAgent
{
    static BackButtonAgent backButtonAgent;

    private final List<PageMemory> stack;

    private BackButtonAgent()
    {
        this.stack = new LinkedList<>();
    }

    public static BackButtonAgent getBackButtonAgent()
    {
        if (backButtonAgent == null)
        {
            backButtonAgent = new BackButtonAgent();
        }
        return backButtonAgent;
    }

    public void add(PageMemory memory)
    {
        stack.add(memory);
    }

    public void back()
    {
        if (stack.size() == 0)
        {
            return;
        }

        stack.remove(stack.size() - 1);
        PageMemory memory = stack.get(stack.size() - 1);

        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        switch (memory.getPage())
        {
            case "explore":
                fxmlController.setMainPane(PanesController.getPanesController().getExplorePane().getPane());
                break;
            case "tweet":
                fxmlController.setMainPane(PanesController.getPanesController().getTweetsListPane(memory.getTweetId(), 0).getListPane());
                break;
            case "profile":
            case "user":
                fxmlController.setMainPane(PanesController.getPanesController().getProfilePane(memory.getUserId(), 0).getProfilePane());
                break;
            case "timeline":
            case "bookmarks":
                fxmlController.setMainPane(PanesController.getPanesController().getTimelinePane(memory.getPage(), 0).getTimelinePane());
                break;
            case "notifications":
            case "followers":
            case "followings":
            case "blacklist":
                fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane(memory.getPage(), memory.getUserId(), 0).getListPane());
                break;
        }

        switch (memory.getPage())
        {
            case "profile":
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("profile", MainPageAgent.getMainPageAgent().getUser().getId()));
                break;
            case "explore":
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("explore"));
                break;
            case "timeline":
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("timeline"));
                break;
            case "bookmarks":
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("bookmarks"));
                break;
            case "notifications":
                BackButtonAgent.getBackButtonAgent().add(new PageMemory("notifications", MainPageAgent.getMainPageAgent().getUser().getId()));
                break;
        }

        stack.remove(stack.size() - 1);
    }

    public void clear()
    {
        stack.clear();
    }
}
