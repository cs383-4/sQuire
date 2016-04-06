package squire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        // From the 'Application' class.
        // Sets up program as a javafx application.
        launch(args);
    }

    // Called during launch().
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // This is returning null, thus the catch block is executing.
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("sQuire Home");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
