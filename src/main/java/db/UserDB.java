package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import util.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserDB implements DBSet<User>
{
    private static final String DATE_FORMAT = Config.getConfig("patterns").getProperty(String.class, "shortDate");
    private static final String USERS_PATH = Config.getConfig("paths").getProperty(String.class, "users");
    private static final String LAST_TWEET_PATH = Config.getConfig("paths").getProperty(String.class, "lastUserId");
    private static final String USERNAMES_PATH = Config.getConfig("paths").getProperty(String.class, "usernames");
    private static final String EMAILS_PATH = Config.getConfig("paths").getProperty(String.class, "emails");
    private static final String PHONENUMBERS_PATH = Config.getConfig("paths").getProperty(String.class, "phoneNumbers");

    private static final Logger logger = LogManager.getLogger(UserDB.class);

    static UserDB userDB;

    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private final Gson gson = gsonBuilder.setPrettyPrinting().setDateFormat(DATE_FORMAT).create();

    private UserDB() {}

    public static UserDB getUserDB()
    {
        if (userDB == null)
        {
            userDB = new UserDB();
        }
        return userDB;
    }

    @Override
    public User get(String username)
    {
        File usersDirectory = new File(USERS_PATH);
        User result = null;
        for (String userId : Objects.requireNonNull(usersDirectory.list()))
        {
            try
            {
                User tempUser = gson.fromJson(Files.readString(Paths.get(USERS_PATH + "/" + userId)), User.class);
                if (tempUser.getUsername().equals(username))
                {
                    result = tempUser;
                    break;
                }
            } catch (IOException ignored) {}
        }
        return result;
    }

    public User get(long id)
    {
        String path = USERS_PATH + "/" + id;
        User result;
        try
        {
            result = gson.fromJson(Files.readString(Paths.get(path)), User.class);
        }
        catch (IOException e)
        {
            result = null;
        }
        return result;
    }

    @Override
    public List<User> getALl()
    {
        List<User> result = new LinkedList<>();
        File usersDirectory = new File(USERS_PATH);

        for (String userId : Objects.requireNonNull(usersDirectory.list()))
        {
            try
            {
                User tempUser = gson.fromJson(Files.readString(Paths.get(USERS_PATH + "/" + userId)), User.class);
                result.add(tempUser);
            } catch (IOException ignored) {}
        }

        return result;
    }

    @Override
    public void save(User user)
    {
        String path = USERS_PATH + "/" + user.getId();
        File file = new File(path);

        if(file.getParentFile().mkdirs())
        {
            logger.warn("users directory was created.");
        }
        if (!file.exists())
        {
            try
            {
                if (file.createNewFile())
                {
                    logger.debug(user.getId() + "th user's file was created.");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        DBUtil.saveToGson(path, gson.toJson(user));

        logger.debug(user.getId() + "th user's file was saved.");
    }

    @Override
    public boolean exists(String username)
    {
        File usersDirectory = new File(USERS_PATH);
        for (String userName : Objects.requireNonNull(usersDirectory.list()))
        {
            User tempUser;
            try
            {
                tempUser = gson.fromJson(Files.readString(Paths.get(USERS_PATH + "/" + userName)), User.class);
                if (tempUser.getUsername().equals(username))
                {
                    return true;
                }
            } catch (IOException ignored) {}
        }
        return false;
    }

    public long getLastId()
    {
        return DBUtil.getLastId(LAST_TWEET_PATH);
    }

    public void setLastId(long newId)
    {
        DBUtil.setLastId(newId, LAST_TWEET_PATH);
        logger.info("last id was changed.");
    }

    public void replaceLine(String path, String oldLine, String newLine)
    {
        List<String> fileContent = new LinkedList<>();
        try
        {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
        } catch (IOException ignored) {}

        boolean flag = true;
        for (int i = 0; i < fileContent.size(); i++)
        {
            if (fileContent.get(i).equals(oldLine))
            {
                fileContent.set(i, newLine);
                flag = false;
                break;
            }
        }
        if (flag)
        {
            fileContent.add(newLine);
        }
        try
        {
            Files.write(Paths.get(path), fileContent, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void changeUsername(String oldUsername, String username)
    {
        replaceLine(USERNAMES_PATH , oldUsername, username);
        logger.debug("usernames list was updated.");
    }

    public void changeEmail(String oldEmail, String email)
    {
        replaceLine(EMAILS_PATH , oldEmail, email);
        logger.debug("emails list was updated.");
    }

    public void changePhoneNumber(String oldPhoneNumber, String phoneNumber)
    {
        replaceLine(PHONENUMBERS_PATH , oldPhoneNumber, phoneNumber);
        logger.debug("phonenumbers list was updated.");
    }
}
