package controller.pages.messages;

import controller.mainpage.MainPageController;
import db.ChatDB;
import model.Chat;
import model.Message;
import util.HashMapUtil;

import java.util.*;

public class MessagesLogic
{
    public List<List<Chat>> getChatsList()
    {
        List<Long> chatIds = MainPageController.getMainPageController().getUser().getProfile().getChats();
        List<List<Chat>> result = new LinkedList<>();
        HashMap<Chat, Long> tempChats = new HashMap<>();
        List<Chat> chats = new LinkedList<>();

        for (Long id : chatIds)
        {
            Chat chat = ChatDB.getChatDB().get(id.toString());
            if (chat != null)
            {
                tempChats.put(chat, - chat.getLastMessageTime());
            }
        }

        for (Map.Entry<Chat, Long> e : HashMapUtil.sortByValue(tempChats).entrySet())
        {
            chats.add(e.getKey());
        }

        if (chats.size() == 0)
        {
            List<Chat> temp = new LinkedList<>();
            for (int i = 0; i < 7; i++)
            {
                temp.add(null);
            }
            result.add(temp);
            return result;
        }

        for (int i = 0; i < chats.size(); i = i+7)
        {
            List<Chat> temp = new LinkedList<>();
            temp.add(chats.get(i));

            for (int j = 1; j < 7; j++)
            {
                if (i + j < chats.size())
                {
                    temp.add(chats.get(i + j));
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

    public List<Chat> getChatsListPage(int page)
    {
        if (this.getChatsList() == null)
        {
            return null;
        }
        if (page < 0 || page > this.getChatsListsNumberOfPages())
        {
            return null;
        }

        return this.getChatsList().get(page);
    }

    public int getChatsListsNumberOfPages()
    {
        if (this.getChatsList() == null)
        {
            return 0;
        }
        return this.getChatsList().size();
    }

    public boolean chatsListHasNextPage(int page)
    {
        if (this.getChatsList() == null)
        {
            return false;
        }
        return page != this.getChatsListsNumberOfPages() - 1;
    }

    public boolean chatsListHasPreviousPage(int page)
    {
        if (this.getChatsList() == null)
        {
            return false;
        }
        return page != 0;
    }

    public List<List<Message>> getChatroom(Chat chat)
    {
        List<Message> messages = chat.getMessages();
        List<List<Message>> result = new LinkedList<>();

        if (messages.size() == 0)
        {
            List<Message> temp = new LinkedList<>();
            for (int i = 0; i < 7; i++)
            {
                temp.add(null);
            }
            result.add(temp);
            return result;
        }

        for (int i = messages.size() - 1; i >= 0 ; i = i-5)
        {
            List<Message> temp = new LinkedList<>();
            temp.add(messages.get(i));

            for (int j = 1; j < 5; j++)
            {
                if (i - j >= 0)
                {
                    temp.add(messages.get(i - j));
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

    public List<Message> getChatroomPage(Chat chat, int page)
    {
        if (this.getChatroom(chat) == null)
        {
            return null;
        }
        if (page < 0 || page > this.getChatroomsNumberOfPages(chat))
        {
            return null;
        }

        return this.getChatroom(chat).get(page);
    }

    public int getChatroomsNumberOfPages(Chat chat)
    {
        if (this.getChatroom(chat) == null)
        {
            return 0;
        }
        return this.getChatroom(chat).size();
    }

    public boolean chatroomHasNextPage(Chat chat, int page)
    {
        if (this.getChatroom(chat) == null)
        {
            return false;
        }
        return page != this.getChatroomsNumberOfPages(chat) - 1;
    }

    public boolean chatroomHasPreviousPage(Chat chat, int page)
    {
        if (this.getChatroom(chat) == null)
        {
            return false;
        }
        return page != 0;
    }
}
