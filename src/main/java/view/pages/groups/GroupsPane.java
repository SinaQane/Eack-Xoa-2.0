package view.pages.groups;

import view.components.empty.emptyuser.EmptyUserPane;
import view.components.group.GroupPane;
import view.components.group.GroupPaneFXML;
import controller.pages.groups.GroupsLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Group;
import config.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GroupsPane
{
    private static final String GROUPS = Config.getConfig("paths").getProperty(String.class, "groups");

    private Pane pane;
    private final FXMLLoader loader;

    public GroupsPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(GROUPS)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pane getPane()
    {
        return this.pane;
    }

    public void refresh(int page)
    {
        GroupsPaneFXML groupsPaneFXML = this.loader.getController();
        groupsPaneFXML.setPage(page);

        GroupsLogic logic = new GroupsLogic();

        List<Group> groups = logic.getPage(page);

        for (int i = 0; i < 5; i++)
        {
            if (groups.get(i) == null)
            {
                groupsPaneFXML.setGroupPane(i, new EmptyUserPane().getPane());
            }
            else
            {
                GroupPane groupPane = new GroupPane();
                GroupPaneFXML groupPaneFXML = groupPane.getLoader().getController();
                groupPaneFXML.setData(groups.get(i));
                groupsPaneFXML.setGroupPane(i, groupPane.getPane());
            }
        }

        groupsPaneFXML.getPreviousButton().setDisable(!logic.hasPreviousPage(page));
        groupsPaneFXML.getNextButton().setDisable(!logic.hasNextPage(page));
    }
}
