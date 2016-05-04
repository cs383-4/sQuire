package squire.CustomViews;

import javafx.scene.control.ListCell;
import squire.Networking.ProjectData;

/**
 * Created by Domn Werner on 5/2/2016.
 */
public class RecentListViewCell extends ListCell<ProjectData>
{
    @Override
    public void updateItem(ProjectData data, boolean empty)
    {
        super.updateItem(data, empty);
        if (data != null)
        {
            RecentListViewItem item = new RecentListViewItem();
            item.setInfo(data);
            setGraphic(item.getMainHBox());
        }
    }
}
