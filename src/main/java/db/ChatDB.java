package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Chat;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import config.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ChatDB implements DBSet<Chat>
{
    private static final String CHATS_PATH = Config.getConfig("paths").getProperty(String.class, "chats");
    private static final String LAST_CHAT_PATH = Config.getConfig("paths").getProperty(String.class, "lastChatId");

    private static final Logger logger = LogManager.getLogger(ChatDB.class);

    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private final Gson gson = gsonBuilder.setPrettyPrinting().create();

    static ChatDB chatDB;

    private ChatDB() {}

    public static ChatDB getChatDB()
    {
        if (chatDB == null)
        {
            chatDB = new ChatDB();
        }
        return chatDB;
    }

    @Override
    public Chat get(String id)
    {
        File chatsDirectory = new File(CHATS_PATH);
        Chat result = null;

        for (String chatId : Objects.requireNonNull(chatsDirectory.list()))
        {
            try
            {
                Chat tempChat = gson.fromJson(Files.readString(Paths.get(CHATS_PATH + "/" + chatId)), Chat.class);
                if (tempChat.getId() == Long.parseLong(id))
                {
                    result = tempChat;
                    break;
                }
            } catch (IOException ignored) {}
        }

        if (result != null)
        {
            if (! result.isGroup())
            {
                User firstUser = UserDB.userDB.get(result.getUsers().get(0));
                User secondUser = UserDB.userDB.get(result.getUsers().get(1));
                result.setChatsName(firstUser.getUsername() + " - " + secondUser.getUsername());
            }
        }

        return result;
    }

    @Override
    public void save(Chat chat)
    {
        String path = CHATS_PATH + "/" + chat.getId();
        File file = new File(path);
        if(file.getParentFile().mkdirs())
        {
            logger.warn("chats directory was created.");
        }
        if (!file.exists())
        {
            try
            {
                if (file.createNewFile())
                {
                    logger.debug("chat " + chat.getId() + "'s file was created.");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        DBUtil.saveToGson(path, gson.toJson(chat));

        logger.debug("chat " + chat.getId() + "'s file was saved.");
    }

    public long getLastId()
    {
        return DBUtil.getLastId(LAST_CHAT_PATH);
    }

    public void setLastId(long newId)
    {
        DBUtil.setLastId(newId, LAST_CHAT_PATH);
        logger.info("last id was changed.");
    }

    @Override
    public List<Chat> getALl()
    {
        List<Chat> result = new LinkedList<>();
        File usersDirectory = new File(CHATS_PATH);

        for (String chatId : Objects.requireNonNull(usersDirectory.list()))
        {
            try
            {
                Chat tempChat = gson.fromJson(Files.readString(Paths.get(CHATS_PATH + "/" + chatId)), Chat.class);
                result.add(tempChat);
            } catch (IOException ignored) {}
        }

        return result;
    }

    public Chat getPv(User ourUser, long UserId)
    {
        for (Chat chat : getALl())
        {
            if (! chat.isGroup())
            {
                if (chat.getUsers().contains(ourUser.getId()) && chat.getUsers().contains(UserId))
                {
                    return chat;
                }
            }
        }

        return new Chat(ourUser, UserDB.userDB.get(UserId));
    }
}
