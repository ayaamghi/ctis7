module ctis7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens edu.guilford.ctis7.GUI to  javafx.fxml;
    exports edu.guilford.ctis7.GUI;

}