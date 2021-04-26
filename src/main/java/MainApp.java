import apps.authentication.view.FXMLController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLController fxmlController = new FXMLController();
        stage.setTitle("JavaFX and Gradle");
        stage.setScene(fxmlController.getScene());
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}