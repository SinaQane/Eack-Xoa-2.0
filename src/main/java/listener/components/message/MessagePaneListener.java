package listener.components.message;

import db.ChatDB;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import controller.mainpage.BackButtonHandler;
import controller.mainpage.BackButtonMemory;
import controller.mainpage.PanesController;
import model.Chat;
import model.Message;
import config.Config;
import view.frames.editmessage.EditMessageFrame;
import view.frames.viewimage.ViewImageFrame;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;

import java.io.File;
import java.net.MalformedURLException;
import java.util.EventObject;

public class MessagePaneListener
{
    private static final String NOT_FOUND = Config.getConfig("paths").getProperty(String.class, "notFound");

    public void eventOccurred(EventObject eventObject, Message message)
    {
        switch (((Button) eventObject.getSource()).getId())
        {
            case "viewImageButton":
                boolean correctPath;

                if (message.getPicturePath().equals(""))
                {
                    correctPath = false;
                }
                else
                {
                    File file = new File(message.getPicturePath());
                    correctPath = file.exists();
                }

                String imagePath;
                if (correctPath)
                {
                    imagePath = message.getPicturePath();
                }
                else
                {
                    imagePath = NOT_FOUND;
                }

                try
                {
                    new ViewImageFrame(new Image(new File(imagePath).toURI().toURL().toExternalForm()));
                } catch (MalformedURLException ignored) {}
                break;
            case "viewTweetButton":
                MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();
                fxmlController.setMainPane(PanesController.getPanesController().getTweetsListPane(message.getTweetId(), 0).getListPane());
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("tweet", message.getTweetId()));
                break;
            case "deleteButton":
                Chat chat = ChatDB.getChatDB().get(message.getChatId());
                chat.getMessages().get(message.getIndex()).delete();
                ChatDB.getChatDB().save(chat);
                break;
            case "editButton":
                new EditMessageFrame(message);
                break;
        }
    }
}
