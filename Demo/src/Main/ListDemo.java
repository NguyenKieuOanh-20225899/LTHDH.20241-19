package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    private final Stage stage;
    private final List<Integer> list;
    private final HBox listDisplay;
    private final Label outputLabel;

    public ListDemo(Stage stage) {
        this.stage = stage;
        this.list = new ArrayList<>();
        this.listDisplay = new HBox(10);
        listDisplay.setId("vbox-list");  // Thêm id để liên kết với CSS
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

        layout.setCenter(listDisplay);

        Scene scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());  // Áp dụng CSS
        stage.setScene(scene);
        stage.setTitle("List Demo");
        stage.show();
    }

    private VBox createControlPanel() {
        VBox controlPanel = new VBox(10);
        controlPanel.setStyle("-fx-alignment: center;");

        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button displayButton = new Button("Display");
        Button getButton = new Button("Get");
        Button isEmptyButton = new Button("Is Empty");
        Button sortButton = new Button("Sort");  // Thêm nút Sort
        Button backButton = new Button("Back");

        addButton.setId("button-control");  // Thêm id để liên kết với CSS
        removeButton.setId("button-control");
        displayButton.setId("button-control");
        getButton.setId("button-control");
        isEmptyButton.setId("button-control");
        sortButton.setId("button-control");  // Thêm id cho nút Sort
        backButton.setId("button-control");

        addButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add to List");
            dialog.setHeaderText("Enter a number:");
            dialog.showAndWait().ifPresent(input -> {
                try {
                    int number = Integer.parseInt(input);
                    list.add(number);
                    displayList();
                    outputLabel.setText("Added: " + number);
                } catch (NumberFormatException ex) {
                    outputLabel.setText("Invalid input.");
                }
            });
        });

        removeButton.setOnAction(e -> {
            if (!list.isEmpty()) {
                int removed = list.remove(list.size() - 1);
                displayList();
                outputLabel.setText("Removed: " + removed);
            } else {
                outputLabel.setText("List is empty.");
            }
        });

        getButton.setOnAction(e -> {
            if (!list.isEmpty()) {
                outputLabel.setText("Last element: " + list.get(list.size() - 1));
            } else {
                outputLabel.setText("List is empty.");
            }
        });

        isEmptyButton.setOnAction(e -> {
            boolean isEmpty = list.isEmpty();
            outputLabel.setText("List is empty: " + isEmpty);
        });

        displayButton.setOnAction(e -> {
            displayList();
            outputLabel.setText("List: " + list);
        });

        // Hành động cho nút Sort
        sortButton.setOnAction(e -> {
            list.sort(Integer::compareTo);  // Sắp xếp danh sách
            displayList();  // Cập nhật lại hiển thị
            outputLabel.setText("List sorted: " + list);
        });

        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.show();
        });

        VBox buttonPanel = new VBox(10);
        buttonPanel.getChildren().addAll(addButton, removeButton, getButton, isEmptyButton, displayButton, sortButton, backButton);  // Thêm nút Sort vào layout
        controlPanel.getChildren().addAll(buttonPanel, outputLabel);
        return controlPanel;
    }

    private void displayList() {
        listDisplay.getChildren().clear();
        for (Integer item : list) {
            Rectangle rect = new Rectangle(50, 50, Color.LIGHTYELLOW);
            Text text = new Text(item.toString());
            text.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            rect.setArcHeight(10);
            rect.setArcWidth(10);
            StackPane stackPane = new StackPane(rect, text);
            listDisplay.getChildren().add(stackPane);
        }
    }
}
