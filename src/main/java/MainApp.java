import controller.mainpage.MainPageController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainStage;

public class MainApp extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        MainPageController.getMainPageController().startTimer();
        new MainStage(stage);
    }
}