package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
    private final Stage stage;
    private final Queue<Integer> queue;
    private final HBox queueDisplay;
    private final Label outputLabel;

    public QueueDemo(Stage stage) {
        this.stage = stage;
        this.queue = new LinkedList<>();
        this.queueDisplay = new HBox(10);
        queueDisplay.setId("hbox-queue");  // Thêm id để liên kết với CSS
        this.outputLabel = new Label();
        outputLabel.setPadding(new Insets(10));
        outputLabel.setId("label-output");  // Thêm id để liên kết với CSS
        outputLabel.setMaxWidth(Double.MAX_VALUE);
        outputLabel.setWrapText(true); // Cho phép xuống dòng khi văn bản quá dài
        outputLabel.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
    }

    public void show() {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));

        VBox controlPanel = createControlPanel();
        layout.setTop(controlPanel);
        BorderPane.setMargin(controlPanel, new Insets(10));

        layout.setCenter(queueDisplay);

        Scene scene = new Scene(layout, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());  // Áp dụng CSS
        stage.setScene(scene);
        stage.setTitle("Queue Demo");
        stage.show();
    }

    private VBox createControlPanel() {
        VBox controlPanel = new VBox(10);
        controlPanel.setStyle("-fx-alignment: center;");

        Button enqueueButton = new Button("Enqueue");
        Button dequeueButton = new Button("Dequeue");
        Button frontButton = new Button("Front");
        Button isEmptyButton = new Button("Is Empty?");
        Button displayButton = new Button("Display");
        Button backButton = new Button("Back");

        enqueueButton.setId("button-control");  // Thêm id để liên kết với CSS
        dequeueButton.setId("button-control");
        frontButton.setId("button-control");
        isEmptyButton.setId("button-control");
        displayButton.setId("button-control");
        backButton.setId("button-control");

        enqueueButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enqueue to Queue");
            dialog.setHeaderText("Enter a number:");
            dialog.showAndWait().ifPresent(input -> {
                try {
                    int number = Integer.parseInt(input);
                    queue.add(number);
                    displayQueue();
                    outputLabel.setText("Enqueued: " + number);
                } catch (NumberFormatException ex) {
                    outputLabel.setText("Invalid input.");
                }
            });
        });

        dequeueButton.setOnAction(e -> {
            if (!queue.isEmpty()) {
                int removed = queue.poll();
                displayQueue();
                outputLabel.setText("Dequeued: " + removed);
            } else {
                outputLabel.setText("Queue is empty.");
            }
        });

        frontButton.setOnAction(e -> {
            if (!queue.isEmpty()) {
                outputLabel.setText("Front: " + queue.peek());
            } else {
                outputLabel.setText("Queue is empty.");
            }
        });

        isEmptyButton.setOnAction(e -> {
            outputLabel.setText(queue.isEmpty() ? "Queue is empty." : "Queue is not empty.");
        });

        displayButton.setOnAction(e -> {
            displayQueue();
            outputLabel.setText("Queue: " + queue);
        });

        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.show();
        });

        VBox buttonPanel = new VBox(10);
        buttonPanel.getChildren().addAll(enqueueButton, dequeueButton, frontButton, isEmptyButton, displayButton, backButton);
        controlPanel.getChildren().addAll(buttonPanel, outputLabel);
        return controlPanel;
    }

    private void displayQueue() {
        queueDisplay.getChildren().clear();
        for (Integer item : queue) {
            Rectangle rect = new Rectangle(50, 50, Color.LIGHTGREEN);
            Text text = new Text(item.toString());
            text.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            rect.setArcHeight(10);
            rect.setArcWidth(10);
            StackPane stackPane = new StackPane(rect, text);
            queueDisplay.getChildren().add(stackPane);
        }
    }
}
