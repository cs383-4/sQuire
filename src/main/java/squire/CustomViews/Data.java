package squire.CustomViews;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by Domn Werner on 5/3/2016.
 */
public class Data
{
    @FXML private HBox hBox;
    @FXML private Label label1;
    @FXML private Label label2;

    public Data()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListCellItem.fxml"));
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setInfo(String string)
    {
        label1.setText(string);
        label2.setText(string);
    }

    public HBox getBox()
    {
        return hBox;
    }
}
