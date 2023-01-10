module com.project.createtask {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.createtask to javafx.fxml;
    exports com.project.createtask;
}