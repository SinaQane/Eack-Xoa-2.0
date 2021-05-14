package view.pages.explore;

import view.components.empty.emptyuser.EmptyUserPane;
import view.components.user.UserPane;
import view.components.user.UserPaneFXML;
import controller.pages.explore.UserSearchLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import config.Config;

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
        UserSearchLogic logic = new UserSearchLogic(this.searched);

        SearchResultsPaneFXML searchResultsPaneFXML = this.loader.getController();
        searchResultsPaneFXML.setSearched(this.searched);
        searchResultsPaneFXML.setPage(page);

        List<Long> users = logic.getPage(page);

        for (int i = 0; i<users.size(); i++)
        {
            if (users.get(i) == 0)
            {
                searchResultsPaneFXML.setUserPane(i, new EmptyUserPane().getPane());
            }
            else
            {
                UserPane userPane = new UserPane();
                UserPaneFXML userPaneFXML = userPane.getLoader().getController();
                userPaneFXML.setData(users.get(i));
                searchResultsPaneFXML.setUserPane(i, userPane.getPane());
            }
        }

        searchResultsPaneFXML.getPreviousButton().setDisable(!logic.hasPreviousPage(page));
        searchResultsPaneFXML.getNextButton().setDisable(!logic.hasNextPage(page));
    }
}
