package apps.mainpage.pages.userslist.view;

import apps.components.userpane.listener.UserPaneListener;
import apps.components.userpane.view.EmptyUserPane;
import apps.components.userpane.view.UserPane;
import apps.components.userpane.view.UserPaneFXML;
import apps.mainpage.pages.userslist.logic.UsersListAgent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import models.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UsersList
{
    private Pane listPane;
    private final FXMLLoader loader;
    private final User user;
    private final String pageKind;

    public UsersList(String pageKind, User user)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../../../../graphic/mainpage/pages/userslist/UsersList.fxml")));
        try
        {
            listPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.user = user;
        this.pageKind = pageKind;
    }

    public Pane getListPane()
    {
        return this.listPane;
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public void refresh(int page)
    {
        UsersListFXML fxmlController = this.loader.getController();

        fxmlController.setPage(page);
        fxmlController.setUser(this.user);
        fxmlController.setPageKind(this.pageKind);

        UsersListAgent logicalAgent = new UsersListAgent(this.user, this.pageKind);

        List<Long> users = logicalAgent.getPage(page);

        for (int i = 0; i < 5; i++)
        {
            if (users.get(i) == 0)
            {
                fxmlController.setUserPane(i, new EmptyUserPane().getUserPane());
            }
            else
            {
                UserPane userPane = new UserPane();
                UserPaneFXML userPaneFXML =  userPane.getLoader().getController();
                userPaneFXML.setListener(new UserPaneListener());
                userPaneFXML.setData(users.get(i));
                fxmlController.setUserPane(i, userPane.getUserPane());
            }
        }

        fxmlController.getPreviousButton().setDisable(!logicalAgent.hasPreviousPage(page));
        fxmlController.getNextButton().setDisable(!logicalAgent.hasNextPage(page));
    }
}
