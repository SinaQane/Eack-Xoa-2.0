package db;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
}
