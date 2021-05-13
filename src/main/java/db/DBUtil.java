package db;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBUtil
{
    static void saveToGson(String path, String data)
    {
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(path, false);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        assert fileOutputStream != null;
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(data);
        printStream.flush();
        printStream.close();

        try
        {
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static long getLastId(String path)
    {
        List<String> fileContent;
        try
        {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            return 0;
        }
        return Long.parseLong(fileContent.get(0));
    }

    public static void setLastId(long newId, String path)
    {
        List<String> fileContent = null;
        try
        {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Objects.requireNonNull(fileContent).set(0, newId + "");
        try
        {
            Files.write(Paths.get(path), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
