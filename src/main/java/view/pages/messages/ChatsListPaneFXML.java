package view.pages.messages;

import listener.pages.messages.ChatsListListener;
import javafx.scene.control.Button;
import model.Chat;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

public class ChatsListPaneFXML
{
    private final ChatsListListener listener = new ChatsListListener();

    private int page;
    private Chat firstChat;
    private Chat secondChat;
    private Chat thirdChat;
    private Chat fourthChat;
    private Chat fifthChat;
    private Chat sixthChat;
    private Chat seventhChat;

    public Button newChatButton;
    public Button firstChatButton;
    public Button secondChatButton;
    public Button thirdChatButton;
    public Button fourthChatButton;
    public Button fifthChatButton;
    public Button sixthChatButton;
    public Button seventhChatButton;
    public Button previousButton;
    public Button nextButton;

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setButton(int i, Chat chat)
    {
        if (i == 0)
        {
            firstChatButton.setText(chat.getChatsName());
            firstChatButton.setVisible(true);
            firstChat = chat;
        }
        else if (i == 1)
        {
            secondChatButton.setText(chat.getChatsName());
            secondChatButton.setVisible(true);
            secondChat = chat;
        }
        else if (i == 2)
        {
            thirdChatButton.setText(chat.getChatsName());
            thirdChatButton.setVisible(true);
            thirdChat = chat;
        }
        else if (i == 3)
        {
            fourthChatButton.setText(chat.getChatsName());
            fourthChatButton.setVisible(true);
            fourthChat = chat;
        }
        else if (i == 4)
        {
            fifthChatButton.setText(chat.getChatsName());
            fifthChatButton.setVisible(true);
            fifthChat = chat;
        }
        else if (i == 5)
        {
            sixthChatButton.setText(chat.getChatsName());
            sixthChatButton.setVisible(true);
            sixthChat = chat;
        }
        else if (i == 6)
        {
            seventhChatButton.setText(chat.getChatsName());
            seventhChatButton.setVisible(true);
            seventhChat = chat;
        }
    }

    public Button getButton(int i)
    {
        List<Button> buttons = new LinkedList<>();

        buttons.add(firstChatButton);
        buttons.add(secondChatButton);
        buttons.add(thirdChatButton);
        buttons.add(fourthChatButton);
        buttons.add(fifthChatButton);
        buttons.add(sixthChatButton);
        buttons.add(seventhChatButton);

        return buttons.get(i);
    }

    public Button getPreviousButton()
    {
        return previousButton;
    }

    public Button getNextButton()
    {
        return nextButton;
    }

    public void newChat()
    {
        listener.eventOccurred(new EventObject(newChatButton), new Chat(), 0);
    }

    public void firstChat()
    {
        listener.eventOccurred(new EventObject(firstChatButton), firstChat, page);
    }

    public void secondChat()
    {
        listener.eventOccurred(new EventObject(secondChatButton), secondChat, page);
    }

    public void thirdChat()
    {
        listener.eventOccurred(new EventObject(thirdChatButton), thirdChat, page);
    }

    public void fourthChat()
    {
        listener.eventOccurred(new EventObject(fourthChatButton), fourthChat, page);
    }

    public void fifthChat()
    {
        listener.eventOccurred(new EventObject(fifthChatButton), fifthChat, page);
    }

    public void sixthChat()
    {
        listener.eventOccurred(new EventObject(sixthChatButton), sixthChat, page);
    }

    public void seventhChat()
    {
        listener.eventOccurred(new EventObject(seventhChatButton), seventhChat, page);
    }

    public void previous()
    {
        listener.eventOccurred(new EventObject(previousButton), new Chat(), page);
    }

    public void next()
    {
        listener.eventOccurred(new EventObject(nextButton), new Chat(), page);
    }
}
