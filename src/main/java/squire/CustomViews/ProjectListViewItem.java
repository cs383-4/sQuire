package squire.CustomViews;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by Domn Werner on 5/2/2016.
 */
public class ProjectListViewItem
{
    @FXML private HBox mainHBox;
    @FXML private Label projectNameLabel;
    @FXML private Label projectDescriptionLabel;
    @FXML private Button joinButton;
    
    public ProjectListViewItem()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectListViewItem.fxml"));
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
    
    public void setInfo(String projectName, String description)
    {
        projectNameLabel.setText(projectName);
        projectDescriptionLabel.setText(description);
    }

    public HBox getMainHBox()
    {
        return mainHBox;
    }
}
