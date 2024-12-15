package Main;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainMenu mainMenu = new MainMenu(primaryStage);
        mainMenu.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
