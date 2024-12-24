module LTHDH20241{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    
    exports source.GUI;
    opens Main to javafx.fxml; 
    exports Main;
}
