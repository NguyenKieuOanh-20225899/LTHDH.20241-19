package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpScreen {

    private final Stage stage;

    public HelpScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Tạo bố cục màn hình trợ giúp
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        // Thêm nội dung hướng dẫn
        Label titleLabel = new Label("Help - Application Guide");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Label instructions = new Label(
            "Welcome to the Data Structure Demonstration App!\n\n" +
            "Here’s how you can use this application:\n" +
            "1. From the main menu, select a data structure (Stack, Queue, List).\n" +
            "2. In the selected data structure's demo, you can:\n" +
            "   - Create the data structure.\n" +
            "   - Perform operations like Insert, Delete, Find, and Sort.\n" +
            "   - View the structure after each operation.\n\n" +
            "Press 'Back' at any time to return to the main menu.\n\n" +
            "Enjoy exploring data structures!"
        );
        instructions.setWrapText(true);

        // Thêm nút Back
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.show();
        });

        // Thêm các thành phần vào layout
        layout.getChildren().addAll(titleLabel, instructions, backButton);

        // Hiển thị màn hình trợ giúp
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.show();
    }
}
