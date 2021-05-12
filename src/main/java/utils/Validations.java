package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations
{
    // Email regex
    private static final String EMAIL_STRING = Config.getConfig("patterns").getProperty(String.class, "email.regexp");
    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_STRING);

    // International phone number regex (like +989123456789)
    private static final String PHONE_NUMBER_STRING = Config.getConfig("patterns").getProperty(String.class, "phoneNumber.regexp");
    private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile(PHONE_NUMBER_STRING);

    /* Username regex with these rules:
        Only contains alphanumeric characters, underscore and dot.
        Underscore and dot can't be at the end or start of a username.
        Underscore and dot can't be next to each other.
        Underscore or dot can't be used multiple times in a row.
        Number of characters must be between 8 to 20.
     */
    private static final String USERNAME_STRING = Config.getConfig("patterns").getProperty(String.class, "username.regexp");
    private static final Pattern USERNAME_REGEX = Pattern.compile(USERNAME_STRING);

    private static final String DATABASE_PATH = Config.getConfig("paths").getProperty(String.class, "database");

    static Validations validations;

    private Validations() {}

    public static Validations getValidations()
    {
        if (validations == null)
        {
            validations = new Validations();
        }
        return validations;
    }

    // Search in file "where" for string "what". returns true if "what" is in "where".
    public boolean newLine(String where, String what)
    {
        File file = new File(DATABASE_PATH + where + ".txt");
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if(what.equals(line))
                {
                    return true;
                }
            }
            return false;
        }
        catch(FileNotFoundException e)
        {
            return true;
        }
    }

    public boolean usernameIsUnavailable(String username)
    {
        return newLine("Usernames", username);
    }

    public boolean usernameIsInvalid(String username)
    {
        Matcher matcher = USERNAME_REGEX.matcher(username);
        return !matcher.find();
    }

    public boolean emailIsUnavailable(String email)
    {
        return newLine("Emails", email);
    }

    public boolean emailIsInvalid(String email)
    {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return !matcher.find();
    }

    public boolean phoneNumberIsUnavailable(String phoneNumber)
    {
        if (phoneNumber.equals(""))
        {
            return false;
        }
        return newLine("PhoneNumbers", phoneNumber);
    }

    public boolean phoneNumberIsInvalid(String phoneNumber)
    {
        if (phoneNumber.equals(""))
        {
            return false;
        }
        Matcher matcher = PHONE_NUMBER_REGEX.matcher(phoneNumber);
        return !matcher.find();
    }
}