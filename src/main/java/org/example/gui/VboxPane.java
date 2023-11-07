package org.example.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VboxPane extends VBox {

    public VboxPane() {
        setSpacing(14);
        setAlignment(Pos.TOP_CENTER);
        setPrefWidth(100);
    }

    public void newLabel(String jmeno) {
        Label lbl = new Label(jmeno);
        lbl.setPrefSize(75, 25);
        getChildren().add(lbl);
    }

    public void newButton(String jmeno, EventHandler<ActionEvent> handler) {
        Button btn = new Button(jmeno);
        btn.setOnAction(handler);
        btn.setPrefSize(75, 25);
        getChildren().add(btn);
    }
}
