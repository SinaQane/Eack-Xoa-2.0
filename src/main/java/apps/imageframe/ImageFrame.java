package apps.imageframe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Config;

import java.io.IOException;
import java.util.Objects;

public class ImageFrame
{
    private static final String IMAGE_FRAME = Config.getConfig("paths").getProperty(String.class, "imageFrame");

    public ImageFrame(Image image)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(IMAGE_FRAME)));

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Image Viewer");

            final ImageView selectedImage = new ImageView();

            stage.setScene(new Scene(root, image.getWidth(), image.getHeight()));

            ((ImageFrameFXML) loader.getController()).getImagePane().setPrefSize(image.getWidth(), image.getHeight());
            ((ImageFrameFXML) loader.getController()).getImageView().setFitWidth(image.getWidth());
            ((ImageFrameFXML) loader.getController()).getImageView().setFitHeight(image.getHeight());
            ((ImageFrameFXML) loader.getController()).getImageView().setLayoutX(0);
            ((ImageFrameFXML) loader.getController()).getImageView().setLayoutY(0);
            ((ImageFrameFXML) loader.getController()).getImageView().setImage(image);

            selectedImage.setImage(image);

            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}