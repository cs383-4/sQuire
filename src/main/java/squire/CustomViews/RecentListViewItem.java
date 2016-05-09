package squire.CustomViews;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import squire.Networking.ProjectData;

/**
 * Created by Domn Werner on 5/2/2016.
 */
public class RecentListViewItem
{
    @FXML private HBox mainHBox;
    @FXML private Label projectNameLabel;
    public String projectId;

    public RecentListViewItem()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RecentProjectItem.fxml"));
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public void setInfo(ProjectData data)
    {
        projectNameLabel.setText(data.name);
        projectId = data.projectUUID;
    }

    public HBox getMainHBox()
    {
        return mainHBox;
    }
}
