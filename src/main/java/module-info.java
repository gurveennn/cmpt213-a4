module ca.cmpt213.cmpt213a4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires javafx.graphics;

    exports ca.cmpt213.asn4.memorygame.ui;
    exports ca.cmpt213.asn4.memorygame.game;


    opens ca.cmpt213.asn4 to javafx.fxml;
    exports ca.cmpt213.asn4;
}

