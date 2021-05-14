package view.pages.groups;

import view.components.empty.emptyuser.EmptyUserPane;
import view.components.group.GroupPane;
import view.components.group.GroupPaneFXML;
import controller.pages.groups.GroupsLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Group;
import util.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GroupsPane
{
    private static final String GROUPS = Config.getConfig("paths").getProperty(String.class, "groups");

    private Pane groupsPane;
    private final FXMLLoader loader;

    public GroupsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(GROUPS)));
        try
        {
            groupsPane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.groupsPane;
    }

    public void refresh(int page)
    {
        GroupsPaneFXML fxmlController = this.loader.getController();
        fxmlController.setPage(page);

        GroupsLogic logicalAgent = new GroupsLogic();

        List<Group> groups = logicalAgent.getPage(page);

        for (int i = 0; i < 5; i++)
        {
            if (groups.get(i) == null)
            {
                fxmlController.setGroupPane(i, new EmptyUserPane().getPane());
            }
            else
            {
                GroupPane groupPane = new GroupPane();
                GroupPaneFXML groupPaneFXML = groupPane.getLoader().getController();
                groupPaneFXML.setData(groups.get(i));
                fxmlController.setGroupPane(i, groupPane.getGroupPane());
            }
        }

        fxmlController.getPreviousButton().setDisable(!logicalAgent.hasPreviousPage(page));
        fxmlController.getNextButton().setDisable(!logicalAgent.hasNextPage(page));
    }
}
