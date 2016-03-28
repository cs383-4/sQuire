import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Hello extends Application implements EventHandler<ActionEvent>
{
    private Button button;
    int clicked = 0;
    double rotate = 0.0;
    public static void main(String[] args)
    {
        // From the 'Application' class.
        // Sets up program as a javafx application.
        launch(args);
    }

    // Called during launch().
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("sQuire");

        button = new Button();
        button.setText("Click me!");

        // Option 1:
        // Whenever the user clicks this button, the code to handle it is in this class.
        button.setOnAction(this);

        // Option 2:
        // Anonymous inner class.
        button.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {

                if (clicked == 0) {
                    rotate += 45.0;
                    button.setText("Clicked.");
                    button.setRotate(rotate);
                    clicked = 1;
                }
                else if (clicked == 1)
                {
                    button.setText("Click Me!");
                    clicked = 0;
                }
            }
        });

        // Option 3:
        // Lambda!
//        button.setOnAction(e -> button.setText("Clicked."));

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event)
    {
        Object source = event.getSource();

        if (source == button)
        {
            button.setText("Clicked.");
        }
    }
}
