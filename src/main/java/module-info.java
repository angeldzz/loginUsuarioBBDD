module com.daw.loginv1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.daw.loginv1 to javafx.fxml;
    exports com.daw.loginv1;
    requires bcrypt;
    requires java.base;
}
