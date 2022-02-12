module com.mycompany.bmr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.mycompany.bmr to javafx.fxml;
    exports g54895.atl.BMR.view;
}
