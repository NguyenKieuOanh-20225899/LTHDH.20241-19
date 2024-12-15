package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StackDemo {
    private final Stage stage;
    private final StackStructure<Integer> stack;
    private final VBox stackDisplay;
    private final Label outputLabel;

    public StackDemo(Stage stage) {
        this.stage = stage;
        this.stack = new StackStructure<>();
        this.stackDisplay = new VBox(10);
        stackDisplay.setId("vbox-stack");  // Thêm id để liên kết với CSS
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

        layout.setCenter(stackDisplay);

        Scene scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());  // Áp dụng CSS
        stage.setScene(scene);
        stage.setTitle("Stack Demo");
        stage.show();
    }

    private VBox createControlPanel() {
        VBox controlPanel = new VBox(10);
        controlPanel.setStyle("-fx-alignment: center;");

        Button pushButton = new Button("Push");
        Button popButton = new Button("Pop");
        Button displayButton = new Button("Display");
        Button peekButton = new Button("Peek");
        Button isEmptyButton = new Button("Is Empty");
        Button backButton = new Button("Back");

        pushButton.setId("button-control");  // Thêm id để liên kết với CSS
        popButton.setId("button-control");
        displayButton.setId("button-control");
        peekButton.setId("button-control");
        isEmptyButton.setId("button-control");
        backButton.setId("button-control");

        pushButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Push to Stack");
            dialog.setHeaderText("Enter a number:");
            dialog.showAndWait().ifPresent(input -> {
                try {
                    int number = Integer.parseInt(input);
                    stack.push(number);
                    displayStack();
                    outputLabel.setText("Pushed: " + number);
                } catch (NumberFormatException ex) {
                    outputLabel.setText("Invalid input.");
                }
            });
        });

        popButton.setOnAction(e -> {
            if (!stack.isEmpty()) {
                int removed = stack.pop();
                displayStack();
                outputLabel.setText("Popped: " + removed);
            } else {
                outputLabel.setText("Stack is empty.");
            }
        });

        peekButton.setOnAction(e -> {
            Integer top = stack.peek();
            if (top != null) {
                outputLabel.setText("Top element: " + top);
            } else {
                outputLabel.setText("Stack is empty.");
            }
        });

        isEmptyButton.setOnAction(e -> {
            boolean isEmpty = stack.isEmpty();
            outputLabel.setText("Stack is empty: " + isEmpty);
        });

        displayButton.setOnAction(e -> {
            displayStack();
            outputLabel.setText("Stack: " + stack);
        });

        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.show();
        });

        VBox buttonPanel = new VBox(10);
        buttonPanel.getChildren().addAll(pushButton, popButton, peekButton, isEmptyButton, displayButton, backButton);
        controlPanel.getChildren().addAll(buttonPanel, outputLabel);
        return controlPanel;
    }

    private void displayStack() {
        stackDisplay.getChildren().clear();
        for (int i = stack.elements.size() - 1; i >= 0; i--) {
            Rectangle rect = new Rectangle(50, 50, Color.LIGHTBLUE);
            Text text = new Text(stack.elements.get(i).toString());
            text.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            rect.setArcHeight(10);
            rect.setArcWidth(10);
            StackPane stackPane = new StackPane(rect, text);
            stackDisplay.getChildren().add(stackPane);
        }
    }
}
