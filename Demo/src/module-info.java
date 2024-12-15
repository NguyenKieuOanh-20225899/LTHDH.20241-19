module MiniProject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens Main to javafx.fxml;
    exports Main;
}

