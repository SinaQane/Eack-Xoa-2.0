package listener.pages.tweetslist;

import java.util.EventObject;

public class TweetsListEvent extends EventObject
{
    private final TweetsListForm tweetsListForm;

    public TweetsListEvent(Object source, TweetsListForm tweetsListForm)
    {
        super(source);
        this.tweetsListForm = tweetsListForm;
    }

    public TweetsListForm getTweetsListForm()
    {
        return tweetsListForm;
    }
}
