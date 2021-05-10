package apps.mainpage.pages.profile_viewuser.listener;

import apps.components.imageframe.ImageFrame;
import apps.mainpage.pages.profile_viewuser.event.UserViewEvent;
import apps.mainpage.pages.profile_viewuser.logic.UserViewAgent;
import apps.mainpage.pages.profile_viewuser.view.ProfilePane;
import apps.mainpage.pages.profile_viewuser.view.ProfilePaneFXML;
import apps.mainpage.view.MainPage;
import apps.mainpage.view.MainPageFXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.User;

import java.io.File;
import java.net.MalformedURLException;

public class UserViewListener
{

    public void eventOccurred(UserViewEvent eventObject)
    {
        MainPageFXML fxmlController = MainPage.getMainPage().getLoader().getController();

        User ourUser = eventObject.getOurUser();
        User otherUser = eventObject.getOtherUser();

        UserViewAgent logicalAgent = new UserViewAgent(ourUser, otherUser);

        ProfilePane userPane;

        switch (((Button) eventObject.getSource()).getId())
        {
            case "statButton":
                logicalAgent.changeStatus();
                userPane = new ProfilePane(otherUser);
                ((ProfilePaneFXML) userPane.getLoader().getController()).setUserViewListener(new UserViewListener());
                userPane.refresh(0);
                fxmlController.setMainPane(userPane.getProfilePane());
                break;
            case "blockButton":
                logicalAgent.block();
                userPane = new ProfilePane(otherUser);
                ((ProfilePaneFXML) userPane.getLoader().getController()).setUserViewListener(new UserViewListener());
                userPane.refresh(0);
                fxmlController.setMainPane(userPane.getProfilePane());
                break;
            case "muteButton":
                logicalAgent.mute();
                userPane = new ProfilePane(otherUser);
                ((ProfilePaneFXML) userPane.getLoader().getController()).setUserViewListener(new UserViewListener());
                userPane.refresh(0);
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
                    imagePath = "src/main/resources/defaultPic.png";
                }

                try
                {
                    new ImageFrame(new Image(new File(imagePath).toURI().toURL().toExternalForm(), 512, 512, false, false));
                } catch (MalformedURLException ignored) {}

                break;
            case "viewFollowersButton":
            case "viewFollowingsButton":
            case "viewBlacklistButton":
            case "sendMessageButton":
                System.out.println(((Button) eventObject.getSource()).getId());
                break;
        }
    }
}