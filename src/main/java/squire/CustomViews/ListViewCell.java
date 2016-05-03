package squire.CustomViews;

import javafx.scene.control.ListCell;

/**
 * Created by Domn Werner on 5/2/2016.
 */
public class ListViewCell extends ListCell<String>
{
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string, empty);
        if (string != null)
        {
            ProjectListViewItem item = new ProjectListViewItem();
            item.setInfo(string, string);
            setGraphic(item.getMainHBox());
        }
    }
}
