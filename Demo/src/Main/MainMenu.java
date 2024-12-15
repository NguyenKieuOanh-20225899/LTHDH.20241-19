package Main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    private final Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage; // Sử dụng Stage chính được truyền từ lớp Main
    }

    public void show() {
        VBox layout = new VBox(10); // Layout dạng cột, khoảng cách giữa các phần tử là 10px
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Tạo các nút trong menu chính
        Button stackButton = createButton("Stack", this::openStackDemo);
        Button queueButton = createButton("Queue", this::openQueueDemo);
        Button listButton = createButton("List", this::openListDemo);
        Button helpButton = createButton("Help", this::openHelpScreen);
        Button exitButton = createButton("Exit", this::exitApplication);

        // Thêm các nút vào layout
        layout.getChildren().addAll(stackButton, queueButton, listButton, helpButton, exitButton);

        // Tạo Scene và hiển thị nó

        Scene scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());  // Áp dụng CSS
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void openStackDemo() {
        try {
            StackDemo stackDemo = new StackDemo(stage); // Đảm bảo lớp StackDemo được định nghĩa
            stackDemo.show();
        } catch (Exception e) {
            System.out.println("Error: StackDemo not implemented. " + e.getMessage());
        }
    }

    private void openQueueDemo() {
        try {
            QueueDemo queueDemo = new QueueDemo(stage); // Đảm bảo lớp QueueDemo được định nghĩa
            queueDemo.show();
        } catch (Exception e) {
            System.out.println("Error: QueueDemo not implemented. " + e.getMessage());
        }
    }

    private void openListDemo() {
        try {
            ListDemo listDemo = new ListDemo(stage); // Đảm bảo lớp ListDemo được định nghĩa
            listDemo.show();
        } catch (Exception e) {
            System.out.println("Error: ListDemo not implemented. " + e.getMessage());
        }
    }

    private void openHelpScreen() {
        try {
            HelpScreen helpScreen = new HelpScreen(stage); // Đảm bảo lớp HelpScreen được định nghĩa
            helpScreen.show();
        } catch (Exception e) {
            System.out.println("Error: HelpScreen not implemented. " + e.getMessage());
        }
    }

    private void exitApplication() {
        try {
            boolean confirmExit = ConfirmBox.display("Exit Confirmation", "Are you sure you want to exit?");
            if (confirmExit) {
                stage.close();
            }
        } catch (Exception e) {
            System.out.println("Error: ConfirmBox not implemented. " + e.getMessage());
        }
    }
}
