module com.mycompany.dormmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.dormmanagement to javafx.fxml;
    exports com.mycompany.dormmanagement;
    requires se.alipsa.ymp;
    requires org.controlsfx.controls;
    requires itextpdf;
}
