package listener.pages.profile;

import view.frames.viewimage.ViewImageFrame;
import controller.mainpage.BackButtonMemory;
import controller.mainpage.BackButtonHandler;
import controller.mainpage.PanesController;
import controller.pages.profile.ViewUserLogic;
import view.pages.profile.ProfilePane;
import view.mainpage.MainPage;
import view.mainpage.MainPageFXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import model.User;
import util.Config;

import java.io.File;
import java.net.MalformedURLException;

public class ViewUserListener
{
    private static final String PROFILE_PIC = Config.getConfig("paths").getProperty(String.class, "profilePic");

    public void eventOccurred(ViewUserEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        User ourUser = eventObject.getOurUser();
        User otherUser = eventObject.getOtherUser();

        ViewUserLogic logicalAgent = new ViewUserLogic(ourUser, otherUser);

        ProfilePane userPane;

        switch (((Button) eventObject.getSource()).getId())
        {
            case "statButton":
                logicalAgent.changeStatus();
                userPane = PanesController.getPanesController().getProfilePane(otherUser.getId(), 0);
                fxmlController.setMainPane(userPane.getProfilePane());
                break;
            case "blockButton":
                logicalAgent.block();
                userPane = PanesController.getPanesController().getProfilePane(otherUser.getId(), 0);
                fxmlController.setMainPane(userPane.getProfilePane());
                break;
            case "muteButton":
                logicalAgent.mute();
                userPane = PanesController.getPanesController().getProfilePane(otherUser.getId(), 0);
                fxmlController.setMainPane(userPane.getProfilePane());
                break;
            case "profilePicButton":
                boolean correctPath;

                if (otherUser.getProfile().getPicturePath().equals(""))
                {
                    correctPath = false;
                }
                else
                {
                    File file = new File(otherUser.getProfile().getPicturePath());
                    correctPath = file.exists();
                }

                String imagePath;
                if (correctPath)
                {
                    imagePath = otherUser.getProfile().getPicturePath();
                }
                else
                {
                    imagePath = PROFILE_PIC;
                }

                try
                {
                    new ViewImageFrame(new Image(new File(imagePath).toURI().toURL().toExternalForm(), 512, 512, false, false));
                } catch (MalformedURLException ignored) {}

                break;
            case "viewFollowersButton":
                fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane("followers", otherUser.getId(), 0).getListPane());
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("followers", otherUser.getId()));
                break;
            case "viewFollowingsButton":
                fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane("followings", otherUser.getId(), 0).getListPane());
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("followings", otherUser.getId()));
                break;
            case "viewBlacklistButton":
                fxmlController.setMainPane(PanesController.getPanesController().getUserslistPane("blacklist", otherUser.getId(), 0).getListPane());
                BackButtonHandler.getBackButtonHandler().add(new BackButtonMemory("blacklist", otherUser.getId()));
                break;
        }
    }
}
