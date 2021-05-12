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
            case "explore":
            case "timeline":
            case "bookmarks":
            case "notifications":
                stack.clear();
                break;
            default:
                stack.remove(stack.size() - 1);
                break;
        }
    }
}
