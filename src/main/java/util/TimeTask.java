package util;

public class TimeTask
{
    private final int time;
    protected Thread thread;
    private final Runnable task;
    private volatile boolean running = false;

    public TimeTask(int time, Runnable task)
    {
        this.time = time;
        this.task = task;
        thread = new Thread(this::run);
    }

    public void update()
    {
        if (task != null) task.run();
    }

    private void run()
    {
        int helpTime = time;
        while (running)
        {
            if (0 < helpTime)
            {
                sleep(1000000000L);
                helpTime--;
            }
            while (running && 0 >= helpTime)
            {
                try
                {
                    helpTime = time;
                    update();
                }
                catch (Throwable throwable)
                {
                    throwable.printStackTrace();
                }
            }
        }
    }

    public void sleep(long time)
    {
        int milliseconds = (int) (time) / 1000000;
        int nanoseconds = (int) (time) % 1000000;
        try
        {
            Thread.sleep(milliseconds, nanoseconds);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void start()
    {
        running = true;
        thread.start();
    }
}
