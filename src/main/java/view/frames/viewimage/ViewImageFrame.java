package view.frames.viewimage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.Config;

import java.io.IOException;
import java.util.Objects;

public class ViewImageFrame
{
    private static final String IMAGE_FRAME = Config.getConfig("paths").getProperty(String.class, "viewImageFrame");

    public ViewImageFrame(Image image)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(IMAGE_FRAME)));

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("View Image");

            final ImageView selectedImage = new ImageView();

            stage.setScene(new Scene(root, image.getWidth(), image.getHeight()));

            ((ViewImageFrameFXML) loader.getController()).getImagePane().setPrefSize(image.getWidth(), image.getHeight());
            ((ViewImageFrameFXML) loader.getController()).getImageView().setFitWidth(image.getWidth());
            ((ViewImageFrameFXML) loader.getController()).getImageView().setFitHeight(image.getHeight());
            ((ViewImageFrameFXML) loader.getController()).getImageView().setLayoutX(0);
            ((ViewImageFrameFXML) loader.getController()).getImageView().setLayoutY(0);
            ((ViewImageFrameFXML) loader.getController()).getImageView().setImage(image);

            selectedImage.setImage(image);

            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}