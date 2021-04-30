package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    private static final Logger logger = LogManager.getLogger(UserDB.class);
    static UserDB userDB;

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
        String path = "./src/main/resources/users";
        File usersDirectory = new File(path);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        User result = null;
        for (String userName : Objects.requireNonNull(usersDirectory.list()))
        {
            try
            {
                User tempUser = gson.fromJson(Files.readString(Paths.get(path + "/" + userName)), User.class);
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
        String path = "./src/main/resources/users/" + id;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
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
    public void save(User user)
    {
        String path = "./src/main/resources/users/" + user.getId();
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
            } catch (IOException ignored) {}
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(user);

        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(path, false);
        } catch (FileNotFoundException ignored) {}

        assert fileOutputStream != null;
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(data);

        printStream.flush();
        printStream.close();
        try
        {
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException ignored) {}

        logger.debug(user.getId() + "th user's file was saved.");
    }

    @Override
    public boolean exists(String username)
    {
        File usersDirectory = new File("./src/main/resources/users");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        for (String userName : Objects.requireNonNull(usersDirectory.list()))
        {
            User tempUser;
            try
            {
                tempUser = gson.fromJson(Files.readString(Paths.get("./src/main/resources/users/" + userName)), User.class);
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
        List<String> fileContent;
        try
        {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get("./src/main/resources/database/LastUserId.txt"), StandardCharsets.UTF_8));
        } catch (IOException e)
        {
            return 0;
        }
        return Long.parseLong(fileContent.get(0));
    }

    public void setLastId(long newId)
    {
        List<String> fileContent = null;
        try
        {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get("./src/main/resources/database/LastUserId.txt"), StandardCharsets.UTF_8));
        } catch (IOException ignored) {}
        Objects.requireNonNull(fileContent).set(0, newId + "");
        try
        {
            Files.write(Paths.get("./src/main/resources/database/LastUserId.txt"), fileContent, StandardCharsets.UTF_8);
        } catch (IOException ignored) {}
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
        } catch (IOException ignored) {}
    }

    public void changeUsername(String oldUsername, String username)
    {
        replaceLine( "./src/main/resources/database/Usernames.txt", oldUsername, username);
        logger.debug("usernames list was updated.");
    }

    public void changeEmail(String oldEmail, String email)
    {
        replaceLine( "./src/main/resources/database/Emails.txt", oldEmail, email);
        logger.debug("emails list was updated.");
    }

    public void changePhoneNumber(String oldPhoneNumber, String phoneNumber)
    {
        replaceLine( "./src/main/resources/database/PhoneNumbers.txt", oldPhoneNumber, phoneNumber);
        logger.debug("phonenumbers list was updated.");
    }
}
