package squire.CustomViews;

import javafx.scene.control.ListCell;

/**
 * Created by Domn Werner on 5/2/2016.
 */
public class ListViewCell extends ListCell<ProjectData>
{
    @Override
    public void updateItem(ProjectData data, boolean empty)
    {
        super.updateItem(data, empty);
        if (data != null)
        {
            ProjectListViewItem item = new ProjectListViewItem();
            item.setInfo(data);
            setGraphic(item.getMainHBox());
        }
    }
}
