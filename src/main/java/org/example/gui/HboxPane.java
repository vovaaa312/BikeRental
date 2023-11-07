package org.example.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import org.example.data.BikeType;

import java.awt.*;

public class HboxPane extends HBox {


    public HboxPane() {
        setSpacing(14);
        setAlignment(Pos.CENTER);
        setPrefHeight(70);
    }

    public void newLabel(String name) {
        Label lbl = new Label(name);
        getChildren().add(lbl);
    }

    public void newButton(String name, EventHandler<ActionEvent> handler) {
        Button btn = new Button(name);
        btn.setOnAction(handler);
        getChildren().add(btn);
    }

    public void newComboBox(String name, EventHandler<ActionEvent> handler) {
        ObservableList<BikeType> lst = FXCollections.observableArrayList(BikeType.values());
        if (name == "New") lst.remove(3);
        ComboBox<BikeType> cmb = new ComboBox<>(lst);
        cmb.setPromptText(name);
        cmb.setOnAction(handler);
        getChildren().add(cmb);
    }
}
