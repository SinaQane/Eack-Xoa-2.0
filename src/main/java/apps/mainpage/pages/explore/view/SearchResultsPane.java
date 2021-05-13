package apps.mainpage.pages.explore.view;

import apps.components.userpane.listener.UserPaneListener;
import apps.components.emptypanes.emptypane.EmptyPane;
import apps.components.userpane.view.UserPane;
import apps.components.userpane.view.UserPaneFXML;
import apps.mainpage.pages.explore.logic.SearchAgent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import utils.Config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SearchResultsPane
{
    private static final String SEARCH_RESULTS = Config.getConfig("paths").getProperty(String.class, "searchResults");

    private Pane pane;
    private final FXMLLoader loader;

    private final String searched;

    public SearchResultsPane(String searched)
    {
        this.searched = searched;

        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SEARCH_RESULTS)));
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
        SearchAgent logicalAgent = new SearchAgent(this.searched);

        SearchResultsPaneFXML fxmlController = this.loader.getController();
        fxmlController.setSearched(this.searched);
        fxmlController.setPage(page);

        List<Long> users = logicalAgent.getPage(page);

        for (int i = 0; i<users.size(); i++)
        {
            if (users.get(i) == 0)
            {
                fxmlController.setUserPane(i, new EmptyPane().getPane());
            }
            else
            {
                UserPane userPane = new UserPane();
                UserPaneFXML userPaneFXML = userPane.getLoader().getController();
                userPaneFXML.setListener(new UserPaneListener());
                userPaneFXML.setData(users.get(i));
                fxmlController.setUserPane(i, userPane.getUserPane());
            }
        }

        fxmlController.getPreviousButton().setDisable(!logicalAgent.hasPreviousPage(page));
        fxmlController.getNextButton().setDisable(!logicalAgent.hasNextPage(page));
    }
}
