package apps.components.userpane.view;

import apps.components.userpane.listener.UserPaneListener;
import apps.mainpage.logic.MainPageAgent;
import db.UserDB;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.User;

import java.util.EventObject;

public class UserPaneFXML
{
    private UserPaneListener listener;

    private long id;

    public Button viewUserButton;
    public Text usernameText;
    public Text nameText;
    public Text statusText;

    public void setListener(UserPaneListener listener)
    {
        this.listener = listener;
    }

    public void setData(long id)
    {
        User ourUser = MainPageAgent.getMainPageAgent().getUser();
        User user = UserDB.getUserDB().get(id);
        this.id = id;

        this.nameText.setText(user.getName());
        this.usernameText.setText("@" + user.getUsername());

        if (ourUser.getProfile().getFollowings().contains(user.getId()))
        {
            this.statusText.setText("Following");
            this.statusText.setFill(Paint.valueOf("#00AA00"));
        }
        else if (ourUser.getProfile().getPending().contains(user.getId()))
        {
            this.statusText.setText("Pending");
            this.statusText.setFill(Paint.valueOf("#AA9900"));
        }
        else if (ourUser.getProfile().getBlocked().contains(user.getId()))
        {
            this.statusText.setText("Blocked");
            this.statusText.setFill(Paint.valueOf("#770000"));
        }
        else
        {
            this.statusText.setText("Not Following");
            this.statusText.setFill(Paint.valueOf("#AA0000"));
        }
    }

    public void viewUser()
    {
        listener.eventOccurred(new EventObject(viewUserButton), id);
    }
}
